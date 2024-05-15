package com.dma.frameworkmanager;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import com.dma.web.Tools;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

public class Main {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		Path input = Paths.get("/home/fr054721/Documents/datacc/dataSources.xml");
		Path output = Paths.get("/home/fr054721/Documents/datacc/dataSources-from-pojo.xml");
		if(Files.exists(input)) {
			 XmlMapper xmlMapper = new XmlMapper();	
			 DataSource ds = xmlMapper.readValue(input.toFile(), DataSource.class);
			 System.out.println(Tools.toJSON(ds));
			 String xml = xmlMapper.writeValueAsString(ds);
			 System.out.println(xml);
		}

	}

}
