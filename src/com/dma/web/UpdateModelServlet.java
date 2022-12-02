package com.dma.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.core.type.TypeReference;

/**
 * Servlet implementation class AppendSelectionsServlet
 */
@WebServlet(name = "UpdateModel", urlPatterns = { "/UpdateModel" })
public class UpdateModelServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateModelServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		Map<String, Object> result = new HashMap<String, Object>();

		try {
			
			result.put("CLIENT", request.getRemoteAddr() + ":" + request.getRemotePort());
			result.put("SERVER", request.getLocalAddr() + ":" + request.getLocalPort());
			
			result.put("FROM", this.getServletName());
			
			String user = request.getUserPrincipal().getName();
			result.put("USER", user);

			result.put("JSESSIONID", request.getSession().getId());
			
			Path wks = Paths.get(getServletContext().getRealPath("/datas") + "/" + user);			
			result.put("WKS", wks.toString());
			
			Path prj = Paths.get((String) request.getSession().getAttribute("projectPath"));
			result.put("PRJ", prj.toString());
			
			Map<String, Object> parms = Tools.fromJSON(request.getInputStream());

			
			if(parms != null) {

				
				@SuppressWarnings("unchecked")
				List<String> langs = (List<String>) Tools.fromJSON((String) parms.get("langs"), new TypeReference<List<String>>(){});
				
				@SuppressWarnings("unchecked")
				List<QuerySubject> qss = (List<QuerySubject>) Tools.fromJSON((String) parms.get("model"), new TypeReference<List<QuerySubject>>(){});

				Map<String, List<Field>> fieldsToRemove = new HashMap<String, List<Field>>();
				Map<String, Set<Field>> fieldsToAdd = new HashMap<String, Set<Field>>();
				Map<String, List<Field>> fieldsToUpdate = new HashMap<String, List<Field>>();

				Map<String, List<Field>> customFields = new HashMap<String, List<Field>>();
				Map<String, Map<String, Field>> modelFields = new HashMap<String, Map<String, Field>>();

				Map<String, Map<String, Field>> dbMap = new HashMap<String, Map<String, Field>>();
				Map<String, Map<String, Field>> xmlMap = new HashMap<String, Map<String, Field>>();
				Map<String, Map<String, Field>> currentMap = new HashMap<String, Map<String, Field>>();
				
				Map<String, Map<String, Map<String, Field>>> ids = new HashMap<String, Map<String, Map<String, Field>>>();
				Set<String> tablesInModel = new HashSet<String>();

				// Start build ids and put custom fields in customFields
				for(QuerySubject qs: qss) {
					String id = qs.get_id();
					String table = qs.getTable_name();
					tablesInModel.add(table);
					Map<String, Map<String, Field>> tables = new HashMap<String, Map<String, Field>>();
					Map<String, Field> columns = new HashMap<String, Field>();
					List<Field> customFList = new ArrayList<Field>();
					for(Field field: qs.getFields()) {
						if(!field.isCustom()) {
							columns.put(field.getField_name(), field);
						}
						else {
							customFList.add(field);					
						}						
					}
					tables.put(table, columns);
					ids.put(id, tables);
					customFields.put(id, customFList);
				}
				// End build ids and put custom fields in customFields

				boolean isXML = false;
				Project project = (Project) request.getSession().getAttribute("currentProject");
				if(project != null) {
					Resource resource = project.getResource();
					if(resource.getJndiName().equalsIgnoreCase("XML")) {
						isXML = true;
					}
				}
				
				if(isXML) {
					@SuppressWarnings("unchecked")
					Map<String, QuerySubject> qssXML = (Map<String, QuerySubject>) request.getSession().getAttribute("QSFromXML");
					
					for(Entry<String, QuerySubject> qs: qssXML.entrySet()) {
						String table = qs.getKey();
						if(tablesInModel.contains(table)){
					    	System.out.println("XML " + table);
					    	Map<String, Field> fMap = new HashMap<String, Field>();
							List<Field> fields = qs.getValue().getFields();
							for(Field field: fields) {
								String column = field.getField_name();
								fMap.put(column, field);
							}
							xmlMap.put(table, fMap);
						}
					}
					currentMap = xmlMap;
				}
				else {

					// Start build dbMap
					Connection conn = (Connection) request.getSession().getAttribute("con");
					String schema = (String) request.getSession().getAttribute("schema");
					
				    DatabaseMetaData metaData = conn.getMetaData();
				    
//				    String[] types = {"TABLE", "VIEW", "SYSTEM TABLE", "GLOBAL TEMPORARY", "LOCAL TEMPORARY", "ALIAS", "SYNONYM"};
				    String[] types = {"TABLE"}; 
				    		
				    if(project != null) {
					    String tableTypes = project.getResource().getTableTypes();
					    List<String> typesList = new ArrayList<String>();
					    switch(tableTypes.toUpperCase()) {
					    	case "TABLE":
					    		typesList.add("TABLE");
					    		break;
					    	case "VIEW":
					    		typesList.add("VIEW");
					    		break;
					    	case "BOTH":
					    		typesList.add("TABLE");
					    		typesList.add("VIEW");
					    		break;
					    }
					    types = typesList.stream().toArray(String[]::new);
				    }				    
				    
				    ResultSet rstTables = metaData.getTables(conn.getCatalog(), schema, "%", types);					    
				    while (rstTables.next()) {
				    	String table = rstTables.getString("TABLE_NAME");
				    	if(tablesInModel.contains(table)) {
							ResultSet rstFields = metaData.getColumns(conn.getCatalog(), schema, table, "%");
							Map<String, Field> fMap = new HashMap<String, Field>();
							while(rstFields.next()){
								Field field = new Field();
								String column = rstFields.getString("COLUMN_NAME");
								field.set_id(rstFields.getString("COLUMN_NAME"));
								field.setField_name(rstFields.getString("COLUMN_NAME"));
								field.setField_type(rstFields.getString("TYPE_NAME"));
								field.setNullable(rstFields.getString("IS_NULLABLE"));
								field.setField_size(rstFields.getInt("COLUMN_SIZE"));
								field.setDescription(rstFields.getString("REMARKS"));
								field.setFieldPos(rstFields.getInt("ORDINAL_POSITION"));
								Map<String, String> langsMap = new HashMap<String, String>();
								for(String lang: langs) {
									langsMap.put(lang, "");
								}
								field.setLabels(langsMap);
								field.setDescriptions(langsMap);
								fMap.put(column, field);
							}
							dbMap.put(table, fMap);
							rstFields.close();
				    	}
				    }
				    rstTables.close();
					// End build dbMap
				    currentMap = dbMap;
				}
				    
			    // Start Update ORDINAL_POSITION (fieldPos) of existing field in model and put fields that does not exists in db in fieldsToRemove 
			    for(Entry<String, Map<String, Map<String, Field>>> id: ids.entrySet()) {
			    	String qs_id = id.getKey();
			    	String qs_table = id.getValue().entrySet().iterator().next().getKey();
			    	Map<String, Field> qs_columns = id.getValue().entrySet().iterator().next().getValue();
			    	
		    		List<Field> fieldsToRemoveList = new ArrayList<Field>();
		    		List<Field> fieldsToUpdateList = new ArrayList<Field>();

		    		for(Entry<String, Field> column: qs_columns.entrySet()) {
			    		String qs_column = column.getKey();
			    		Field qs_field = column.getValue();
				    	if(currentMap.get(qs_table).get(qs_column) != null) {
				    		int dbFieldPos = currentMap.get(qs_table).get(qs_column).getFieldPos();
				    		qs_field.setFieldPos(dbFieldPos);
				    		fieldsToUpdateList.add(qs_field);
				    	}
				    	else {
				    		fieldsToRemoveList.add(qs_field);
				    	}
			    	}
			    	if(!fieldsToRemoveList.isEmpty()) {
			    		fieldsToRemove.put(qs_id, fieldsToRemoveList);
			    	}
			    	if(!fieldsToUpdateList.isEmpty()) {
			    		fieldsToUpdate.put(qs_id, fieldsToUpdateList);
			    	}
			    }
			    // End Update ORDINAL_POSITION (fieldPos) of existing field in model and put fields that does not exists in db in fieldsToRemove 
			    
			    // Start Adding field from db/xml that does not exists in model in fieldsToAdd
			    for(Entry<String, Map<String, Field>> db: currentMap.entrySet()) {
			    	String dbTable = db.getKey();
			    	Map<String, Field> dbFieldsMap = db.getValue();
			    	Set<Field> fieldsToAddList = new HashSet<Field>();
			    	for(Entry<String, Field> field: dbFieldsMap.entrySet()) {
			    		String dbColumn = field.getKey();
			    		Field dbField = field.getValue();
			    		for(QuerySubject qs: qss) {
				    		if(ids.get(qs.get_id()).containsKey(dbTable)) {
				    			if(! ids.get(qs.get_id()).get(dbTable).containsKey(dbColumn)) {
				    				fieldsToAddList.add(dbField);
				    			}
				    		}
			    		}
			    	}
			    	if(!fieldsToAddList.isEmpty()) {
			    		fieldsToAdd.put(dbTable, fieldsToAddList);
			    	}
			    }
			    // End Adding field from db/xml that does not exists in model in fieldsToAdd
			    
				for(QuerySubject qs: qss) {
					List<Field> fields = new ArrayList<Field>();
					if(fieldsToUpdate.get(qs.get_id()) != null) {
						fields.addAll(fieldsToUpdate.get(qs.get_id()));
					}
					if(fieldsToAdd.get(qs.getTable_name()) != null) {
						fields.addAll(fieldsToAdd.get(qs.getTable_name()));
					}
					
					if(fieldsToRemove.get(qs.get_id()) != null) {
						fields.removeAll(fieldsToRemove.get(qs.get_id()));
					}
					
					int fieldPos = fields.size();
					if(customFields.get(qs.get_id()) != null) {
						for(Field customField: customFields.get(qs.get_id())) {
							customField.setFieldPos(++fieldPos);
							fields.add(customField);
						}
					}
					qs.setFields(fields);
				}
				
				result.put("MODEL", qss);
				result.put("REMOVED", fieldsToRemove);
				result.put("ADDED", fieldsToAdd);
				result.put("UPDATED", fieldsToUpdate);
				result.put("CUSTOMFIELDS", customFields);
				result.put("STATUS", "OK");
			}
			else {
				result.put("STATUS", "KO");
				result.put("ERROR", "Input parameters are not valid.");
				throw new Exception();
			}			
		}
		
		catch (Exception e) {
			// TODO Auto-generated catch block
			result.put("STATUS", "KO");
			result.put("EXCEPTION", e.getClass().getName());
			result.put("MESSAGE", e.getMessage());
			StringWriter sw = new StringWriter();
			e.printStackTrace(new PrintWriter(sw));
			result.put("STACKTRACE", sw.toString());
			e.printStackTrace(System.err);
		}

		finally{
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			response.getWriter().write(Tools.toJSON(result));
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}