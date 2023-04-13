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

public class Test32 {

	public static void main(String[] args) throws SQLException, ClassNotFoundException {
		// TODO Auto-generated method stub
		
    	ResultSet rst = null;
    	PreparedStatement stmt = null;
    	Connection csvCon = null;
    	
    	Path prj = Paths.get("/home/fr054721/Downloads/bdxmet");
    	
		if(Files.exists(Paths.get(prj + "/relation.csv"))) {
			Properties props = new java.util.Properties();
			props.put("separator",";");
			Class.forName("org.relique.jdbc.csv.CsvDriver");
			csvCon = DriverManager.getConnection("jdbc:relique:csv:" + prj.toString(), props);
		}

		String tableName = "WORKORDER";
//		String sql = "SELECT DISTINCT(FK_NAME) FROM relation where FKTABLE_NAME = '" + tableName + "'";
//		String sql = "SELECT FK_NAME FROM relation where FKTABLE_NAME = '" + tableName + "'";
//		String sql = "SELECT DISTINCT(FK_NAME) FROM relation where PKTABLE_NAME = '" + tableName + "'";
//		String sql = "SELECT FK_NAME FROM relation where PKTABLE_NAME = '" + tableName + "'";
		String sql = "SELECT * FROM relation where FKTABLE_NAME = '" + tableName + "'";

//		String sql = "SELECT KEY_SEQ FROM relation where PKTABLE_NAME = '" + tableName + "'";
//		String sql = "SELECT distinct(PKCOLUMN_NAME) FROM relation where PKTABLE_NAME = '" + tableName + "'";
		System.out.println("sql=" + sql);
		stmt = csvCon.prepareStatement(sql);
		rst = stmt.executeQuery();
		int count = 0;
		
    	while(rst.next()){
//    		String FKName = rst.getString("FK_NAME");
//    		System.out.println("FKName=" + FKName);
    		++count;
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
		
		System.out.println("count=" + count);
		

	}

}
