package com.dma.frameworkmanager;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

@JacksonXmlRootElement(localName = "querySubject")
public class QuerySubjectXML {

	@JacksonXmlProperty(isAttribute = true)
	String status = "valid";
	QuerySubjectNameXML name;
	String lastChanged;
	String lastChangedBy;
	DefinitionXML definition;
	List<DeterminantXML> determinants;
	@JacksonXmlElementWrapper(useWrapping = false)
	List<QueryItemXML> queryItem = new ArrayList<QueryItemXML>();
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public QuerySubjectNameXML getName() {
		return name;
	}
	public void setName(QuerySubjectNameXML name) {
		this.name = name;
	}
	public String getLastChanged() {
		return lastChanged;
	}
	public void setLastChanged(String lastChanged) {
		this.lastChanged = lastChanged;
	}
	public String getLastChangedBy() {
		return lastChangedBy;
	}
	public void setLastChangedBy(String lastChangedBy) {
		this.lastChangedBy = lastChangedBy;
	}
	public DefinitionXML getDefinition() {
		return definition;
	}
	public void setDefinition(DefinitionXML definition) {
		this.definition = definition;
	}
	public List<DeterminantXML> getDeterminants() {
		return determinants;
	}
	public void setDeterminants(List<DeterminantXML> determinants) {
		this.determinants = determinants;
	}
	public List<QueryItemXML> getQueryItem() {
		return queryItem;
	}
	public void setQueryItem(List<QueryItemXML> queryItem) {
		this.queryItem = queryItem;
	}

}
