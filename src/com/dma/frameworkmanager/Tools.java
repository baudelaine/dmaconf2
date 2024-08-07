package com.dma.frameworkmanager;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringWriter;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

public class Tools {
	
	
	public final static String toJSON(Object o){
		try{
		ObjectMapper mapper = new ObjectMapper();
		mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
		mapper.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
//		mapper.enable(SerializationFeature.INDENT_OUTPUT);
		mapper.setSerializationInclusion(Include.NON_NULL);
		StringWriter sw = new StringWriter();
		String jsonResult = null;
		mapper.writeValue(sw, o);
		sw.flush();
		jsonResult = sw.toString();
		sw.close();
		return jsonResult;
		}
		catch(Exception e){
			e.printStackTrace(System.err);
		}
		return null;
	}
	
	public final static List<Map<String, Object>> fromJSON2ML(InputStream is){
		List<Map<String, Object>> mapList = new ArrayList<Map<String, Object>>();
		
		try{
			InputStreamReader isr = new InputStreamReader(is);
			BufferedReader br = new BufferedReader(isr);
	        ObjectMapper mapper = new ObjectMapper();
	        mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
			mapList = mapper.readValue(br, new TypeReference<List<Map<String, Object>>>(){});
			return mapList;
		}
		catch(Exception e){
			e.printStackTrace(System.err);
		}
		
        return null;
	}	
	
	public final static List<Map<String, Object>> fromJSON2ML(File file) throws FileNotFoundException{
		return fromJSON2ML(new FileInputStream(file));
	}
	
	public final static List<Map<String, Object>> fromJSON2ML(String string) throws FileNotFoundException{
		return fromJSON2ML(new ByteArrayInputStream(string.getBytes(StandardCharsets.UTF_8)));
	}
	
	public final static Map<String, Object> fromJSON(InputStream is){
		Map<String, Object>	map = new HashMap<String, Object>();
		
		try{
			InputStreamReader isr = new InputStreamReader(is);
			BufferedReader br = new BufferedReader(isr);
	        ObjectMapper mapper = new ObjectMapper();
	        mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
			map = mapper.readValue(br, new TypeReference<Map<String, Object>>(){});
			return map;
		}
		catch(Exception e){
			e.printStackTrace(System.err);
		}
		
        return null;
	}
	
	public final static Map<String, Object> fromJSON(File file) throws FileNotFoundException{
		return fromJSON(new FileInputStream(file));
	}

	public final static Map<String, Object> fromJSON(String string){
		return fromJSON(new ByteArrayInputStream(string.getBytes(StandardCharsets.UTF_8)));
	}
	
	public final static <T> Object fromJSON(InputStream is, TypeReference<T> t){
		
		try{
			InputStreamReader isr = new InputStreamReader(is);
			BufferedReader br = new BufferedReader(isr);
			ObjectMapper mapper = new ObjectMapper();
	        mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
			return mapper.readValue(br, t);		
		}
		catch(Exception e){
			e.printStackTrace(System.err);
		}
		
        return null;
	}

	public final static <T> Object fromJSON(File file, TypeReference<T> t) throws FileNotFoundException{
		return fromJSON(new FileInputStream(file), t);
	}
	
	public final static <T> Object fromJSON(String string, TypeReference<T> t) throws FileNotFoundException{
		return fromJSON(new ByteArrayInputStream(string.getBytes(StandardCharsets.UTF_8)), t);
	}		
	
	
	public final static String getCurrentTimestamp() {
		
		Calendar c = Calendar.getInstance();
		
		java.text.SimpleDateFormat mois = new java.text.SimpleDateFormat("MM");
		
		String date = c.get(Calendar.YEAR) + "-" + c.get(Calendar.MONTH) + "-" + c.get(Calendar.DAY_OF_MONTH);
		date = c.get(Calendar.YEAR) + "-" + mois.format(c.getTime()) + "-" + c.get(Calendar.DAY_OF_MONTH);
		String time = c.get(Calendar.HOUR_OF_DAY) + "-" + c.get(Calendar.MINUTE) + "-" + c.get(Calendar.SECOND);
		return date + "-" + time;
		
	}

	
	
}
