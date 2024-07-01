package com.dma.datamodule;

import java.util.List;

public class DrillGroupDM {

	List<SegmentDM> segment;
	String identifier;
	String label;
	List<String> propertyOverride;
	String idForExpression;
	
	public List<SegmentDM> getSegment() {
		return segment;
	}
	public void setSegment(List<SegmentDM> segment) {
		this.segment = segment;
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
	public List<String> getPropertyOverride() {
		return propertyOverride;
	}
	public void setPropertyOverride(List<String> propertyOverride) {
		this.propertyOverride = propertyOverride;
	}
	public String getIdForExpression() {
		return idForExpression;
	}
	public void setIdForExpression(String idForExpression) {
		this.idForExpression = idForExpression;
	}
	
}
