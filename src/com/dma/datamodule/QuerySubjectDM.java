package com.dma.datamodule;

import java.util.List;
import java.util.Map;

public class QuerySubjectDM {

	String label;
	String identifier;
	String idForExpression;
	int rowCount;
	List<Map<String, QueryItemDM>> item;

	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
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
	public int getRowCount() {
		return rowCount;
	}
	public void setRowCount(int rowCount) {
		this.rowCount = rowCount;
	}
	public List<Map<String, QueryItemDM>> getItem() {
		return item;
	}
	public void setItem(List<Map<String, QueryItemDM>> item) {
		this.item = item;
	}

	
}
