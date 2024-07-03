package com.dma.frameworkmanager;

import java.util.Arrays;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

@JsonPropertyOrder({"name", "locales", "defaultLocale", "namespaces", "dataSource", "parameterMap", "packages", "securityViews"})
@JacksonXmlRootElement(localName = "project")
public class ProjectXML {

//	@JacksonXmlProperty(isAttribute = true, namespace = "http://www.developer.cognos.com/schemas/bmt/60/12")
	@JacksonXmlProperty(isAttribute = true)
	String queryMode = "dynamic";
	String name = "metadata";
	@JacksonXmlElementWrapper(localName = "locales")
	@JacksonXmlProperty(localName = "locale")
	@JsonProperty("locale")
	List<String> locales = Arrays.asList("en-gb");
	String defaultLocale = "en-gb";
	@JacksonXmlProperty(localName = "namespace")
	NamespacesXML namespaces;
	@JacksonXmlElementWrapper(localName = "dataSources")
	List<DataSourceXML> dataSource;
	@JacksonXmlElementWrapper(localName = "parameterMaps")
	@JacksonXmlProperty(localName = "parameterMap")
	@JsonProperty("parameterMap")
	List<ParameterMapXML> parameterMap;
	String packages;
	String securityViews;
	
	public String getQueryMode() {
		return queryMode;
	}
	public void setQueryMode(String queryMode) {
		this.queryMode = queryMode;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<String> getLocales() {
		return locales;
	}
	public void setLocales(List<String> locales) {
		this.locales = locales;
	}
	public String getDefaultLocale() {
		return defaultLocale;
	}
	public void setDefaultLocale(String defaultLocale) {
		this.defaultLocale = defaultLocale;
	}
	public NamespacesXML getNamespaces() {
		return namespaces;
	}
	public void setNamespaces(NamespacesXML namespaces) {
		this.namespaces = namespaces;
	}
	public List<ParameterMapXML> getParameterMap() {
		return parameterMap;
	}
	public List<DataSourceXML> getDataSource() {
		return dataSource;
	}
	public void setDataSource(List<DataSourceXML> dataSource) {
		this.dataSource = dataSource;
	}
	public void setParameterMap(List<ParameterMapXML> parameterMap) {
		this.parameterMap = parameterMap;
	}
	public String getPackages() {
		return packages;
	}
	public void setPackages(String packages) {
		this.packages = packages;
	}
	public String getSecurityViews() {
		return securityViews;
	}
	public void setSecurityViews(String securityViews) {
		this.securityViews = securityViews;
	}
	
}
