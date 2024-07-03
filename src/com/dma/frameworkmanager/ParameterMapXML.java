package com.dma.frameworkmanager;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

@JacksonXmlRootElement(localName = "parameterMap")
public class ParameterMapXML {

	@JacksonXmlProperty(isAttribute = true)
	boolean hidden;
	String name = "";
	@JacksonXmlElementWrapper(useWrapping = false)
	List<ParameterMapEntryXML> parameterMapEntry = new ArrayList<>();
	
	public boolean isHidden() {
		return hidden;
	}
	public void setHidden(boolean hidden) {
		this.hidden = hidden;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<ParameterMapEntryXML> getParameterMapEntry() {
		return parameterMapEntry;
	}
	public void setParameterMapEntry(List<ParameterMapEntryXML> parameterMapEntry) {
		this.parameterMapEntry = parameterMapEntry;
	}
	
	
}
