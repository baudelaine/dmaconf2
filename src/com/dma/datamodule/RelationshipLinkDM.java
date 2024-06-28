package com.dma.datamodule;

public class RelationshipLinkDM {
	
	String leftRef;
	String rightRef;
	String comparisonOperator;
	
	public String getLeftRef() {
		return leftRef;
	}
	public void setLeftRef(String leftRef) {
		this.leftRef = leftRef;
	}
	public String getRightRef() {
		return rightRef;
	}
	public void setRightRef(String rightRef) {
		this.rightRef = rightRef;
	}
	public String getComparisonOperator() {
		return comparisonOperator;
	}
	public void setComparisonOperator(String comparisonOperator) {
		this.comparisonOperator = comparisonOperator;
	}
	
}
