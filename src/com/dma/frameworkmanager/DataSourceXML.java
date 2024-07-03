package com.dma.frameworkmanager;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

@JacksonXmlRootElement(localName = "dataSource")
public class DataSourceXML {

	String name= "";
	String cmDataSource = "";
	String catalog = "";
	String schema = "";
	DataSourceTypeXML type = new DataSourceTypeXML();
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCmDataSource() {
		return cmDataSource;
	}
	public void setCmDataSource(String cmDataSource) {
		this.cmDataSource = cmDataSource;
	}
	public String getCatalog() {
		return catalog;
	}
	public void setCatalog(String catalog) {
		this.catalog = catalog;
	}
	public String getSchema() {
		return schema;
	}
	public void setSchema(String schema) {
		this.schema = schema;
	}
	public DataSourceTypeXML getType() {
		return type;
	}
	public void setType(DataSourceTypeXML type) {
		this.type = type;
	}
	
}
