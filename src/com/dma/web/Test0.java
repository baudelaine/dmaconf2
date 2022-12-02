package com.dma.web;

import java.io.FileNotFoundException;
import java.math.RoundingMode;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;

import com.fasterxml.jackson.core.type.TypeReference;

public class Test0 {

	public static void main(String[] args) throws FileNotFoundException {
		// TODO Auto-generated method stub

		
		Path input = Paths.get("/home/fr054721/Downloads/BDXMET_V2_V0.8-2022-11-29-16-46-1.json");	
		
		List<QuerySubject> qss = new ArrayList<QuerySubject>();
		
		if(Files.exists(input)) {
			
			Map<String, List<QuerySubject>> model = (Map<String, List<QuerySubject>>) Tools.fromJSON(input.toFile(), new TypeReference<Map<String, List<QuerySubject>>>(){});
			
			qss = model.get("querySubjects");
			
		}
		
		
		Map<String, Map<String, Map<String, Field>>> ids = new HashMap<String, Map<String, Map<String, Field>>>();
		
		
		for(QuerySubject qs: qss) {
			String id = qs.get_id();
			String table = qs.getTable_name();
			Map<String, Map<String, Field>> tables = new HashMap<String, Map<String, Field>>();
			Map<String, Field> columns = new HashMap<String, Field>();
			for(Field field: qs.getFields()) {
				columns.put(field.getField_name(), field);
			}
			tables.put(table, columns);
			ids.put(id, tables);
		}

		System.out.println(ids.get("SERVRECTRANSRef").containsKey("SERVRECTRANS"));
		
		System.out.println(ids.get("SERVRECTRANSRef").get("SERVRECTRANS").containsKey("CHANGEDATE"));
		
		System.out.println(ids.get("SERVRECTRANSRef").get("SERVRECTRANS").entrySet().iterator().next().getValue().getField_name());
		
		System.out.println(ids.get("SERVRECTRANSRef").get("SERVRECTRANS").entrySet().contains("CHANGEDATE"));
		System.out.println(ids.entrySet().iterator().next().getValue().entrySet().iterator().next().getValue().containsKey("CHANGEDATE"));
		System.out.println(ids.get("SERVRECTRANSRef").get("SERVRECTRANS").get("CHANGEBY").getField_name());
		System.out.println(ids.entrySet().iterator().next().getKey());
		System.out.println(ids.entrySet().iterator().next().getValue().entrySet().iterator().next().getKey());
		System.out.println(ids.entrySet().iterator().next().getValue().entrySet().iterator().next().getValue().entrySet().iterator().next().getValue().getField_name());
		System.out.println(ids.entrySet().iterator().next());
		
//		String id = "0";
//		String table = "t0";
//		String column = "c0";
//		Field field = new Field();
//		
//		columns.put(column, field);
//		tables.put(table, columns);
//		ids.put(id, tables);
//		
//		System.out.println(ids.get(id).get(table).get(column));
		
		
		
		String s = "sfdsfvsefv;qsdcdcq";
    	String qs_id = s.split(";")[0];
    	String qs_table = s.split(";")[1];
		
    	System.out.println("qs_id=" + qs_id);
    	System.out.println("qs_table=" + qs_table);
		
    	System.exit(0);
    	
		long recCount = 20;
		long qs_recCount = 20;
		
		double d0 = Double.parseDouble(String.valueOf(recCount));
		double d1 = Double.parseDouble(String.valueOf(qs_recCount));
		
		double num = (d0/d1) * 100;
		NumberFormat nf = NumberFormat.getInstance(Locale.ENGLISH);
		nf.setMaximumFractionDigits(3);
//		nf.setMinimumFractionDigits(5);	    
		nf.setRoundingMode(RoundingMode.UP);
	    num = Double.parseDouble(nf.format(num));
		
		
		
	}

}
