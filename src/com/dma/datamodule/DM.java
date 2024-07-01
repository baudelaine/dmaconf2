package com.dma.datamodule;

import java.util.List;
import java.util.Map;

public class DM {

	String version;
	String container;
	List<String> use;
	List<UseSpecDM> useSpec;
	String expressionLocale;
	List<QuerySubjectDM> querySubject;
	List<RelationshipDM> relationship;
	List<MetadataTreeViewDM> metadataTreeView;
	List<DrillGroupDM> drillGroup;
	List<ParameterValueSetDM> parameterValueSet;
	String dataRetrievalMode;
	String refActiveParameterValueSet;
	String identifier;
	String label;
	List<Map<String, String>> property;
	List<String> propertyOverride;
	
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
	public List<MetadataTreeViewDM> getMetadataTreeView() {
		return metadataTreeView;
	}
	public void setMetadataTreeView(List<MetadataTreeViewDM> metadataTreeView) {
		this.metadataTreeView = metadataTreeView;
	}
	public List<DrillGroupDM> getDrillGroup() {
		return drillGroup;
	}
	public void setDrillGroup(List<DrillGroupDM> drillGroup) {
		this.drillGroup = drillGroup;
	}
	public List<ParameterValueSetDM> getParameterValueSet() {
		return parameterValueSet;
	}
	public void setParameterValueSet(List<ParameterValueSetDM> parameterValueSet) {
		this.parameterValueSet = parameterValueSet;
	}
	public String getDataRetrievalMode() {
		return dataRetrievalMode;
	}
	public void setDataRetrievalMode(String dataRetrievalMode) {
		this.dataRetrievalMode = dataRetrievalMode;
	}
	public String getRefActiveParameterValueSet() {
		return refActiveParameterValueSet;
	}
	public void setRefActiveParameterValueSet(String refActiveParameterValueSet) {
		this.refActiveParameterValueSet = refActiveParameterValueSet;
	}
	public String getIdentifier() {
		return identifier;
	}
	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	public List<Map<String, String>> getProperty() {
		return property;
	}
	public void setProperty(List<Map<String, String>> property) {
		this.property = property;
	}
	public List<String> getPropertyOverride() {
		return propertyOverride;
	}
	public void setPropertyOverride(List<String> propertyOverride) {
		this.propertyOverride = propertyOverride;
	}
	
}
