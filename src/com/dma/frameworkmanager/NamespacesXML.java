package com.dma.frameworkmanager;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

@JacksonXmlRootElement(localName = "namespace")
public class NamespacesXML {

	NamespacesNameXML name = new NamespacesNameXML();
	String lastChanged;
	String lastChangedBy;
	@JacksonXmlElementWrapper(useWrapping = false)
	List<NamespaceXML> namespace = new ArrayList<NamespaceXML>();

	public NamespacesNameXML getName() {
		return name;
	}
	public void setName(NamespacesNameXML name) {
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
	public List<NamespaceXML> getNamespace() {
		return namespace;
	}
	public void setNamespace(List<NamespaceXML> namespace) {
		this.namespace = namespace;
	}
	
}
