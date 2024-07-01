package com.dma.datamodule;

import java.util.List;

public class RelationshipDM {
	
	String joinFilterType;
	String identifier;
	String label;
	List<String> propertyOverride;
	String idForExpression;
	
	RelationshipLeftDM left;
	RelationshipRightDM right;
	List<RelationshipLinkDM> link;
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
	public String getIdForExpression() {
		return idForExpression;
	}
	public void setIdForExpression(String idForExpression) {
		this.idForExpression = idForExpression;
	}
	public RelationshipLeftDM getLeft() {
		return left;
	}
	public void setLeft(RelationshipLeftDM left) {
		this.left = left;
	}
	public RelationshipRightDM getRight() {
		return right;
	}
	public void setRight(RelationshipRightDM right) {
		this.right = right;
	}
	public List<RelationshipLinkDM> getLink() {
		return link;
	}
	public void setLink(List<RelationshipLinkDM> link) {
		this.link = link;
	}
	public String getJoinFilterType() {
		return joinFilterType;
	}
	public void setJoinFilterType(String joinFilterType) {
		this.joinFilterType = joinFilterType;
	}
	public List<String> getPropertyOverride() {
		return propertyOverride;
	}
	public void setPropertyOverride(List<String> propertyOverride) {
		this.propertyOverride = propertyOverride;
	}

}
