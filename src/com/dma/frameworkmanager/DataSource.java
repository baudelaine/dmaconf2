package com.dma.frameworkmanager;

public class DataSource {

	String name= "";
	String cmDataSource = "";
	String catalog = "";
	String schema = "";
	DataSourceType type = new DataSourceType();
	
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
	public DataSourceType getType() {
		return type;
	}
	public void setType(DataSourceType type) {
		this.type = type;
	}
	
}
