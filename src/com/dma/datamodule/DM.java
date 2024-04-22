package com.dma.datamodule;

import java.util.ArrayList;
import java.util.List;

public class DM {

	String version;
	String container;
	String expressionLocale;
	List<UseSpecDM> useSpec = new ArrayList<UseSpecDM>();
	List<QuerySubjectDM> querySubject = new ArrayList<QuerySubjectDM>();
	
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
}
