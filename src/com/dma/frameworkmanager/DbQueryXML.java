package com.dma.frameworkmanager;

import java.util.List;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

@JacksonXmlRootElement(localName = "dbQuery")
public class DbQueryXML {

	@JacksonXmlElementWrapper(useWrapping = false)
	List<SourcesXML> sources;
	SqlXML sql;
	String tableType = "table";
	String procParameters = "";
	
	public List<SourcesXML> getSources() {
		return sources;
	}
	public void setSources(List<SourcesXML> sources) {
		this.sources = sources;
	}
	public SqlXML getSql() {
		return sql;
	}
	public void setSql(SqlXML sql) {
		this.sql = sql;
	}
	public String getTableType() {
		return tableType;
	}
	public void setTableType(String tableType) {
		this.tableType = tableType;
	}
	public String getProcParameters() {
		return procParameters;
	}
	public void setProcParameters(String procParameters) {
		this.procParameters = procParameters;
	}
	
}
