package com.dma.frameworkmanager;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

@JacksonXmlRootElement(localName = "sources")
public class SourcesXML {

	String dataSourceRef = "";

	public String getDataSourceRef() {
		return dataSourceRef;
	}

	public void setDataSourceRef(String dataSourceRef) {
		this.dataSourceRef = dataSourceRef;
	}
	
}
