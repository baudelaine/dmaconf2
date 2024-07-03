package com.dma.frameworkmanager;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.xml.stream.XMLStreamException;

import com.dma.datamodule.DM;
import com.dma.datamodule.QueryItemDM;
import com.dma.datamodule.QuerySubjectDM;
import com.dma.datamodule.UseSpecDM;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

public class Test0 {

	public static void main(String[] args) throws IOException, XMLStreamException {
		// TODO Auto-generated method stub
		Path input;
		Path output;
//		input = Paths.get("/home/fr054721/Documents/datacc/dataSources.xml");
//		output = Paths.get("/home/fr054721/Documents/datacc/dataSources-from-pojo.xml");
		
//		input = Paths.get("/home/fr054721/Documents/datacc/local/metadata-local/model.xml");
//		output = Paths.get("/home/fr054721/Documents/datacc/local/metadata-local/model-from-pojo.xml");

		input = Paths.get("/home/fr054721/Documents/datacc/emptyModel.xml");
		output = Paths.get("/home/fr054721/Documents/datacc/emptyModel-from-pojo.xml");

		DM dm = null;
		Path json = Paths.get("/home/fr054721/Documents/datacc/local/metadata.json");
		if(Files.exists(input)) {
			dm = (DM) Tools.fromJSON(json.toFile(), new TypeReference<DM>(){});
			System.out.println(dm.getQuerySubject().size());
		}
		
		UseSpecDM usDM = dm.getUseSpec().get(0);
		String dsDM = usDM.getDataSourceOverride().getCmDataSource();
		String schemaDM = usDM.getDataSourceOverride().getSchema();
		
		if(Files.exists(input)) {
			
			XmlMapper xmlMapper = new XmlMapper();	
			xmlMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
//			 xmlMapper.setSerializationInclusion(JsonInclude.Include.NON_EMPTY);
			 xmlMapper.enable(SerializationFeature.INDENT_OUTPUT);
//			 obj = xmlMapper.readValue(input.toFile(), ProjectXML.class);
//			 System.out.println(Tools.toJSON(obj));
//			 System.out.println(xmlMapper.writeValueAsString(obj));
//			 ParameterMapXML pm = xmlMapper.readValue(input.toFile(), ParameterMapXML.class);
//			 System.out.println(Tools.toJSON(pm));
//			 xmlMapper.writeValue(output.toFile(), pm);
//			 ProjectXML prj = (ProjectXML) obj;
			 ProjectXML prj = new ProjectXML();
			 
			 DataSourceXML ds = new DataSourceXML();
			 ds.setName(dsDM);
			 ds.setCmDataSource(dsDM);
			 ds.setSchema(schemaDM);
			 prj.setDataSource(Arrays.asList(ds));
			 
			 NamespaceXML ns = new NamespaceXML();
			 ns.setName(new NamespaceNameXML());
			 
			 List<QuerySubjectXML> qss = new ArrayList<QuerySubjectXML>();
			 
			 for(QuerySubjectDM qsDM: dm.getQuerySubject()) {			 

				 QuerySubjectXML qs = new QuerySubjectXML();
				 
				 QuerySubjectNameXML qsName = new QuerySubjectNameXML();
				 qsName.setName(qsDM.getIdentifier());
				 qs.setName(qsName);
				 
				 DefinitionXML def = new DefinitionXML();
				 DbQueryXML dbQuery = new DbQueryXML();
				 List<SourcesXML> sources = new ArrayList<SourcesXML>();
				 SourcesXML source = new SourcesXML();
				 source.setDataSourceRef("[].[dataSources].[" + dsDM + "]");
				 sources.add(source);
				 dbQuery.setSources(sources);
				 
				 SqlXML sql = new SqlXML();
				 sql.setTable("[" + dsDM + "]" + ".[" + qsDM.getIdentifier() + "]");
				 dbQuery.setSql(sql);
				 
				 def.setDbQuery(dbQuery);
				 qs.setDefinition(def);
				 
				 List<QueryItemXML> qis = new ArrayList<QueryItemXML>();
				for(Map<String, QueryItemDM> qiDMMap: qsDM.getItem()) {
					QueryItemDM qiDM = qiDMMap.get("queryItem");
					 QueryItemNameXML qiName = new QueryItemNameXML();
					 qiName.setName(qiDM.getIdentifier());
					 QueryItemXML qi = new QueryItemXML();
					 qi.setName(qiName);
					 qi.setDatatype(qiDM.getDatatype());
					 qi.setUsage(qiDM.getUsage());
					 qi.setRegularAggregate(qiDM.getRegularAggregate());
					 qi.setNullable(qiDM.isNullable());
					 qis.add(qi);
				}
				 
				 qs.setQueryItem(qis);
				 qss.add(qs);
				 
				 
				 
			 }
			 
			 ns.setQuerySubject(qss);
			 
			 NamespacesXML nss = new NamespacesXML();
			 nss.setNamespace(Arrays.asList(ns));
			 prj.setNamespaces(nss);
			 
			 ParameterMapXML env = new ParameterMapXML();
			 env.setName("_env");
			 ParameterMapXML governor = new ParameterMapXML();
			 governor.setName("_governor");
			 
			 prj.setParameterMap(Arrays.asList(env, governor));
			 
			 prj.setPackages("");
			 prj.setSecurityViews("");
			 
			 
			 
//			 System.out.println(prj.getNamespace().getNamespace().get(0).getQuerySubject().size());
			 xmlMapper.writeValue(output.toFile(), prj);
			 
		}

	}

}
