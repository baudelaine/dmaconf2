package com.dma.frameworkmanager;

import java.util.ArrayList;
import java.util.List;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

@JacksonXmlRootElement(localName = "namespace")
public class NamespaceXML {

	NamespaceNameXML name;
	String lastChanged;
	String lastChangedBy;
	@JacksonXmlElementWrapper(useWrapping = false)
	List<QuerySubjectXML> querySubject = new ArrayList<QuerySubjectXML>();
	
	public NamespaceNameXML getName() {
		return name;
	}
	public void setName(NamespaceNameXML name) {
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
	public List<QuerySubjectXML> getQuerySubject() {
		return querySubject;
	}
	public void setQuerySubject(List<QuerySubjectXML> querySubject) {
		this.querySubject = querySubject;
	}
	
}
