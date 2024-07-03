package com.dma.frameworkmanager;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.apache.commons.lang3.StringUtils;

import com.dma.datamodule.DM;
import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

public class Test1 {

	public static void main(String[] args) throws StreamReadException, DatabindException, IOException {
		// TODO Auto-generated method stub

		Charset charset = StandardCharsets.UTF_8;
		Path input = Paths.get("/home/fr054721/Documents/datacc/metadata-local/model.xml");
		Path output = Paths.get("/home/fr054721/Documents/datacc/metadata-local/model-from-pojo.xml");

		DM dm = null;
		Path json = Paths.get("/home/fr054721/Documents/datacc/local/metadata.json");
		if(Files.exists(json)) {
			dm = (DM) Tools.fromJSON(json.toFile(), new TypeReference<DM>(){});
			System.out.println(dm.getQuerySubject().size());
		}
		
		if(Files.exists(input) && dm != null) {
			XmlMapper xmlMapper = new XmlMapper();	
			xmlMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
//			 xmlMapper.setSerializationInclusion(JsonInclude.Include.NON_EMPTY);
//			 xmlMapper.enable(SerializationFeature.INDENT_OUTPUT);
			 ProjectXML prj = xmlMapper.readValue(input.toFile(), ProjectXML.class);
			 System.out.println(prj.getNamespaces().getNamespace().get(0).getQuerySubject().size());
			 for(QuerySubjectXML qs: prj.getNamespaces().getNamespace().get(0).getQuerySubject()) {
				 qs.setDeterminants(null);
			 }
			 xmlMapper.writeValue(output.toFile(), prj);
			
		}
		
		// Find and replace words/lines in a file

		String content = new String(Files.readAllBytes(output), charset);
		
		String header = "<?xml version=\"1.0\" encoding=\"UTF-8\" ?>";
		String projectTagBefore = "<project queryMode=\"dynamic\">";
		String projectTagAfter = header + "\n<project queryMode=\"dynamic\" xmlns=\"http://www.developer.cognos.com/schemas/bmt/60/12\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:schemaLocation=\"http://www.developer.cognos.com/schemas/bmt/60/12 BMTModelSpecification.xsd\">";
		String selectFromBefore = "from<column>*</column>";
		String selectFromAfter = "Select <column>*</column>from";
		
		content = StringUtils.replace(content, projectTagBefore, projectTagAfter);
		content = StringUtils.replace(content, selectFromBefore, selectFromAfter);

		Files.write(output, content.getBytes(charset));		
	}

}
