package com.dma.datamodule;

import java.util.ArrayList;
import java.util.List;

public class DM {

	String version;
	String container;
	List<String> use;
	List<UseSpecDM> useSpec = new ArrayList<UseSpecDM>();
	String expressionLocale;
	List<QuerySubjectDM> querySubject = new ArrayList<QuerySubjectDM>();
	List<RelationshipDM> relationship = new ArrayList<RelationshipDM>();
	
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	public String getContainer() {
		return container;
	}
	public void setContainer(String container) {
		this.container = container;
	}
	public List<String> getUse() {
		return use;
	}
	public void setUse(List<String> use) {
		this.use = use;
	}
	public String getExpressionLocale() {
		return expressionLocale;
	}
	public void setExpressionLocale(String expressionLocale) {
		this.expressionLocale = expressionLocale;
	}
	public List<UseSpecDM> getUseSpec() {
		return useSpec;
	}
	public void setUseSpec(List<UseSpecDM> useSpec) {
		this.useSpec = useSpec;
	}
	public List<QuerySubjectDM> getQuerySubject() {
		return querySubject;
	}
	public void setQuerySubject(List<QuerySubjectDM> querySubject) {
		this.querySubject = querySubject;
	}
	public List<RelationshipDM> getRelationship() {
		return relationship;
	}
	public void setRelationship(List<RelationshipDM> relationship) {
		this.relationship = relationship;
	}
}
