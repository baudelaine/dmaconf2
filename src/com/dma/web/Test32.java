package com.dma.web;

import java.nio.file.Path;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Properties;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

import org.apache.commons.lang3.StringUtils;

public class Test32 {

	public static void main(String[] args) throws SQLException, ClassNotFoundException {
		// TODO Auto-generated method stub
		
    	ResultSet rst = null;
    	PreparedStatement stmt = null;
    	Connection csvCon = null;
    	
    	Path prj = Paths.get("/home/fr054721/Downloads/antibia");
    	
		if(Files.exists(Paths.get(prj + "/relationExp.csv"))) {
			Properties props = new java.util.Properties();
			props.put("separator",";");
			Class.forName("org.relique.jdbc.csv.CsvDriver");
			csvCon = DriverManager.getConnection("jdbc:relique:csv:" + prj.toString(), props);
		}

		String tableName = "Tababs";
		String type = "Final";
		String alias = "Pompabs_ALIAS";
//		String sql = "SELECT DISTINCT(FK_NAME) FROM relation where FKTABLE_NAME = '" + tableName + "'";
//		String sql = "SELECT FK_NAME FROM relation where FKTABLE_NAME = '" + tableName + "'";
//		String sql = "SELECT DISTINCT(FK_NAME) FROM relation where PKTABLE_NAME = '" + tableName + "'";
//		String sql = "SELECT FK_NAME FROM relation where PKTABLE_NAME = '" + tableName + "'";
		String sql = "SELECT * FROM relationExp where FKTABLE_NAME = '" + tableName + "'";

//		String sql = "SELECT KEY_SEQ FROM relation where PKTABLE_NAME = '" + tableName + "'";
//		String sql = "SELECT distinct(PKCOLUMN_NAME) FROM relation where PKTABLE_NAME = '" + tableName + "'";
		System.out.println("sql=" + sql);
		stmt = csvCon.prepareStatement(sql);
		rst = stmt.executeQuery();
		
    	while(rst.next()){
//    		String FKName = rst.getString("FK_NAME");
//    		System.out.println("FKName=" + FKName);
//    		JOIN_NAME;FKTABLE_NAME;PKTABLE_NAME;RELATION_EXPRESSION
    		String relExp = rst.getString("RELATION_EXPRESSION");
    		String fkTable = rst.getString("FKTABLE_NAME").trim();
    		String pkTable = rst.getString("PKTABLE_NAME").trim();
    		System.out.println("Before update=" + relExp);
    		
    		try {
	    		relExp = relExp.replaceAll(fkTable, "[" + type.toUpperCase() + "].[" + alias + "]");
	    		relExp = relExp.replaceAll(pkTable, "[" + pkTable + "]");
	    		Pattern p = Pattern.compile("([^\\]]+]\\.)(\\w+)");
	    		Matcher m = p.matcher(relExp);
	    		while (m.find()) {
//	    		    System.out.println("m.group(1)=" + m.group(1));
//	    		    System.out.println("m.group(2)=" + m.group(2));
	    		    String field = m.group(2);
	    		    relExp = relExp.replaceAll(field, "[" + field + "]");
	    		}
	    		relExp = relExp.replaceAll("\\[\\[", "[");
	    		relExp = relExp.replaceAll("\\]\\]", "]");
	    		
	    		p = Pattern.compile("\\[([^\\]]+)\\]");
	    		m = p.matcher(relExp);
	    	    while (m.find()) {
//	    		    System.out.println("m.group(1)=" + m.group(1));
//	    		    System.out.println("m.group(2)=" + m.group(2));
	    		    String field = m.group(1);
	    		    String fixedField = StringUtils.remove(field, "[");
//	    		    System.out.println("fixedField=" + fixedField);
	    		    relExp = StringUtils.replace(relExp, field, fixedField);
	    		}
	    		
    		}
    		catch(PatternSyntaxException e) {
    			continue;
    		}
    		System.out.println(relExp);
    	}
        if(rst != null) {
        	rst.close();
        	rst = null;
        }
		if(stmt != null) {
			stmt.close();
			stmt = null;
		}
		
		if(csvCon != null) {
			csvCon.close();
			csvCon = null;
		}
		
//		String s = "[FINAL].[Pompabs].[A_[MAT]=[Pompabc].[MAT] and [FINAL].[Pompabs].[A_[MOTIF]=[Pompabc].[MOTIF] and [FINAL].[Pompabs].[A_[ANNEE]=[Pompabc].[ANNEE]";
//		Pattern p = Pattern.compile("\\[([^\\]]+)\\]");
//		Matcher m = p.matcher(s);
//	    System.out.println("s=" + s);
//	    while (m.find()) {
//		    System.out.println("m.group(1)=" + m.group(1));
////		    System.out.println("m.group(2)=" + m.group(2));
//		    String field = m.group(1);
//		    String fixedField = StringUtils.remove(field, "[");
//		    System.out.println("fixedField=" + fixedField);
//		    s = StringUtils.replace(s, field, fixedField);
//		}
//	    System.out.println("s=" + s);
		
		
	}

}
