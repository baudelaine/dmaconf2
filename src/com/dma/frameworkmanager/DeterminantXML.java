package com.dma.frameworkmanager;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

@JacksonXmlRootElement(localName = "determinant")
public class DeterminantXML {

	String name;
	@JacksonXmlElementWrapper(localName = "key")
	@JacksonXmlProperty(localName = "refobjKey")
	@JsonProperty("key")
	List<String> refobjKey;
	@JacksonXmlElementWrapper(localName = "attributes")
	@JacksonXmlProperty(localName = "refobjAttributes")
	@JsonProperty("attributes")
	List<String> refobjAttributes;
	boolean canGroup;
	boolean identifiesRow;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public boolean isCanGroup() {
		return canGroup;
	}
	public void setCanGroup(boolean canGroup) {
		this.canGroup = canGroup;
	}
	public boolean isIdentifiesRow() {
		return identifiesRow;
	}
	public void setIdentifiesRow(boolean identifiesRow) {
		this.identifiesRow = identifiesRow;
	}
	
}
