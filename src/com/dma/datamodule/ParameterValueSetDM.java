package com.dma.datamodule;

import java.util.List;

public class ParameterValueSetDM {

	String identifier;
	List<String> propertyOverride;
	
	public String getIdentifier() {
		return identifier;
	}
	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}
	public List<String> getPropertyOverride() {
		return propertyOverride;
	}
	public void setPropertyOverride(List<String> propertyOverride) {
		this.propertyOverride = propertyOverride;
	}
	
}
