package com.dma.frameworkmanager;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlText;

@JsonPropertyOrder({"select", "column", "from", "table"})
@JacksonXmlRootElement(localName = "sql")
public class SqlXML {

	@JacksonXmlProperty(isAttribute = true)
	String type = "cognos";
	@JacksonXmlText
	String select;
	String column;
//	@JacksonXmlProperty(localName = "sql")
//	String from;
	String table = "";
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getSelect() {
		return select;
	}
	public void setSelect(String select) {
		this.select = select;
	}
	public String getColumn() {
		return column;
	}
	public void setColumn(String column) {
		this.column = column;
	}
	public String getTable() {
		return table;
	}
	public void setTable(String table) {
		this.table = table;
	}
	
}
