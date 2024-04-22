package com.dma.datamodule;

import java.io.FileNotFoundException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import com.dma.web.Tools;
import com.fasterxml.jackson.core.type.TypeReference;

public class TestDM0 {

	public static void main(String[] args) throws FileNotFoundException {
		// TODO Auto-generated method stub
		Path input = Paths.get("/home/fr054721/Documents/datacc/dmphysical.json");
		if(Files.exists(input)) {
			
			DM dm = (DM) Tools.fromJSON(input.toFile(), new TypeReference<DM>(){});
			System.out.println(dm.getQuerySubject().size());
			for(QuerySubjectDM qs: dm.getQuerySubject()) {
				int fieldCount = qs.getItem().size();
				System.out.println(qs.getLabel() + " -> " + fieldCount);
			}
		}
	}

}
