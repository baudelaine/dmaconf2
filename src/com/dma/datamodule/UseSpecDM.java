package com.dma.datamodule;

import java.util.ArrayList;
import java.util.List;

public class UseSpecDM {

	String identifier;
	String type;
	String storeID;
	String searchPath;
	List<AncestorDM> ancestors = new ArrayList<AncestorDM>();
	
	public String getIdentifier() {
		return identifier;
	}
	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getStoreID() {
		return storeID;
	}
	public void setStoreID(String storeID) {
		this.storeID = storeID;
	}
	public String getSearchPath() {
		return searchPath;
	}
	public void setSearchPath(String searchPath) {
		this.searchPath = searchPath;
	}
	public List<AncestorDM> getAncestors() {
		return ancestors;
	}
	public void setAncestors(List<AncestorDM> ancestors) {
		this.ancestors = ancestors;
	}
}
