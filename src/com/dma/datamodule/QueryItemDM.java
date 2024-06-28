package com.dma.datamodule;

public class QueryItemDM {

	String identifier;
	String idForExpression;
	String label;
	String datatype;
	String usage;
	boolean nullable;
	String regularAggregate;
	
	public String getIdentifier() {
		return identifier;
	}
	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}
	public String getIdForExpression() {
		return idForExpression;
	}
	public void setIdForExpression(String idForExpression) {
		this.idForExpression = idForExpression;
	}
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	public String getDatatype() {
		return datatype;
	}
	public void setDatatype(String datatype) {
		this.datatype = datatype;
	}
	public String getUsage() {
		return usage;
	}
	public void setUsage(String usage) {
		this.usage = usage;
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
	
}
