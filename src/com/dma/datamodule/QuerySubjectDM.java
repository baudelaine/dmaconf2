package com.dma.datamodule;

import java.util.ArrayList;
import java.util.List;

public class QuerySubjectDM {

	String label;
	int rowCount;
	List<QueryItemDM> item = new ArrayList<QueryItemDM>();

	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	public int getRowCount() {
		return rowCount;
	}
	public void setRowCount(int rowCount) {
		this.rowCount = rowCount;
	}
	public List<QueryItemDM> getItem() {
		return item;
	}
	public void setItem(List<QueryItemDM> item) {
		this.item = item;
	}
	
}
