package com.dma.frameworkmanager;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

public class DataSourceType {

	String queryType = "";
	@JacksonXmlProperty(localName = "interface")
	String dbEngine = "";
	
	public String getQueryType() {
		return queryType;
	}
	public void setQueryType(String queryType) {
		this.queryType = queryType;
	}
	public String getDbEngine() {
		return dbEngine;
	}
	public void setDbEngine(String dbEngine) {
		this.dbEngine = dbEngine;
	}
}
