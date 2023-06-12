package com.dma.web;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.core.type.TypeReference;

public class Test33 {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub

		Path json = Paths.get("/home/fr054721/Documents/antibia/views.json");
		Path output = Paths.get("/home/fr054721/Documents/antibia/views.csv");
		String delim = ";";
		String lang = "fr";
			
		if(Files.exists(json)) {
			
			@SuppressWarnings("unchecked")
			List<QuerySubject> views = (List<QuerySubject>) Tools.fromJSON(json.toFile(), new TypeReference<List<QuerySubject>>(){});
			
			String header = "TABLE_NAME" + delim + "TABLE_TYPE" + delim + "TABLE_LABEL" + delim + "TABLE_DESCRIPTION" + delim +
					"FIELD_ID" + delim  + "FIELD_NAME" + delim + "FIELD_TYPE" + delim + "FIELD_LABEL" + delim + "FIELD_DESCRIPTION" + delim +
					"EXPRESSION" + delim + "HIDDEN" + delim + "ICON" + delim + "ALIAS" + delim + "FOLDER" + delim + "ROLE";				
			
			List<String> lines = new ArrayList<String>();
			lines.add(header);
			
			for(QuerySubject view: views) {
				StringBuffer tblBuf = new StringBuffer();
				tblBuf.append(view.getTable_name() + delim + view.getType());
				String label = "";
				if(view.getDescriptions().containsKey(lang)) {
					label = view.getLabels().get(lang);
				}
				String description = "";
				if(view.getDescriptions().containsKey(lang)) {
					description = view.getDescriptions().get(lang);
				}
				tblBuf.append(delim + label + delim + description.replaceAll(delim, " "));
				String tbl = tblBuf.toString();
				for(Field field: view.getFields()) {
					StringBuffer fldBuf = new StringBuffer();
					fldBuf.append(tbl + delim + field.get_id() + delim + field.getField_name() + delim + field.getField_type());
					String flabel = "";
					if(field.getLabels().containsKey(lang)) {
						flabel = field.getLabels().get(lang);
					}
					String fdescription = null;
					if(field.getLabels().containsKey(lang)) {
						fdescription = field.getDescriptions().get(lang);
					}
					if(fdescription == null) {
						fdescription = "";
					}
					fldBuf.append(delim + flabel + delim + fdescription.replaceAll(delim, " ") + delim + field.getExpression() + delim +
							String.valueOf(field.isHidden()) + delim + field.getIcon() + delim + 
							field.getAlias() + delim + field.getFolder() + delim + field.getRole());
					lines.add(fldBuf.toString());
					
				}
			}
			
			Files.write(output,Tools.toJSON(lines).getBytes());
			
			
			
		}
	}

}
