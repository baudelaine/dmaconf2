package com.dma.frameworkmanager;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

@JacksonXmlRootElement(localName = "definition")
public class DefinitionXML {
	
	DbQueryXML dbQuery;

	public DbQueryXML getDbQuery() {
		return dbQuery;
	}

	public void setDbQuery(DbQueryXML dbQuery) {
		this.dbQuery = dbQuery;
	}
	
}
