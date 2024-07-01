package com.dma.datamodule;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;

import com.dma.web.Tools;
import com.fasterxml.jackson.core.type.TypeReference;

public class TestDM0 {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		Charset charset = StandardCharsets.UTF_8;
		Path input = Paths.get("/home/fr054721/Documents/datacc/latestDM/DataModule-AI.json");
		Path output = Paths.get("/home/fr054721/Documents/datacc/latestDM/DataModule-AI-from-pojo.json");
		
		if(Files.exists(input)) {
			
			DM dm = (DM) Tools.fromJSON(input.toFile(), new TypeReference<DM>(){});
			for(QuerySubjectDM qs: dm.getQuerySubject()) {
				int fieldCount = qs.getItem().size();
				System.out.println(qs.getLabel() + " -> " + fieldCount);
				for(Map<String, QueryItemDM> map: qs.getItem()) {
					QueryItemDM qi = map.get("queryItem");
					System.out.println("\t" + qi.getIdentifier());
				}
			}
			System.out.println(Tools.toJSON(dm.getQuerySubject().get(1).getItem().get(0)));
			System.out.println(Tools.toJSON(dm.getRelationship()));
			System.out.println(Tools.toJSON(dm.getMetadataTreeView()));
			Files.write(output, (Tools.toJSON(dm)).getBytes(charset));
		}
	}

}
