package com.dma.frameworkmanager;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

@JacksonXmlRootElement(localName = "queryItem")
public class QueryItemXML {

	QueryItemNameXML name;
	String lastChanged;
	String externalName;
	// usage values can be identifier, attribute or fact
	String usage = "attribute";
	String datatype;
	String precision;
	int size;
	boolean nullable;
	String regularAggregate;
	String semiAggregate;

	public QueryItemNameXML getName() {
		return name;
	}
	public void setName(QueryItemNameXML name) {
		this.name = name;
	}
	public String getLastChanged() {
		return lastChanged;
	}
	public void setLastChanged(String lastChanged) {
		this.lastChanged = lastChanged;
	}
	public String getExternalName() {
		return externalName;
	}
	public void setExternalName(String externalName) {
		this.externalName = externalName;
	}
	public String getUsage() {
		return usage;
	}
	public void setUsage(String usage) {
		this.usage = usage;
	}
	public String getDatatype() {
		return datatype;
	}
	public void setDatatype(String datatype) {
		this.datatype = datatype;
	}
	public String getPrecision() {
		return precision;
	}
	public void setPrecision(String precision) {
		this.precision = precision;
	}
	public int getSize() {
		return size;
	}
	public void setSize(int size) {
		this.size = size;
	}
	public boolean isNullable() {
		return nullable;
	}
	public void setNullable(boolean nullable) {
		this.nullable = nullable;
	}
	public String getRegularAggregate() {
		return regularAggregate;
	}
	public void setRegularAggregate(String regularAggregate) {
		this.regularAggregate = regularAggregate;
	}
	public String getSemiAggregate() {
		return semiAggregate;
	}
	public void setSemiAggregate(String semiAggregate) {
		this.semiAggregate = semiAggregate;
	}

}
