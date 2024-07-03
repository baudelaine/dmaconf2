package com.dma.frameworkmanager;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

@JacksonXmlRootElement(localName = "type")
public class DataSourceTypeXML {

	String queryType = "relational";
	@JacksonXmlProperty(localName = "interface")
	@JsonProperty("interface")
	String dbEngine = "D2";
	
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
