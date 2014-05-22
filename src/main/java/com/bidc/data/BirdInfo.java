package com.bidc.data;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class BirdInfo extends AppEntity {

	@Id
	String id;

	String name;

	String picUrl;

	// Options in other languages
	Map<String, String> langOptions;
	
	// Names in other languages
	
	Map<String, String> langNames;

	String createdBy;

	Date createdOn;

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public BirdInfo() {
		id = "B" + System.currentTimeMillis();
		setCreatedBy("System");
		setCreatedOn(new Date());
		langOptions = new HashMap<String, String>();
		langNames = new HashMap<String,String>();
		langOptions = new HashMap<String,String>();
		
	}

	@Override
	public String getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return the createdBy
	 */
	public String getCreatedBy() {
		return createdBy;
	}

	/**
	 * @param createdBy
	 *            the createdBy to set
	 */
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	/**
	 * @return the createdOn
	 */
	public Date getCreatedOn() {
		return createdOn;
	}

	/**
	 * @param createdOn
	 *            the createdOn to set
	 */
	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}


	public void addAlternativeName(String key, String value) {
		langOptions.put(key, value);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPicUrl() {
		return picUrl;
	}

	public void setPicUrl(String picUrl) {
		this.picUrl = picUrl;
	}

	public Map<String, String> getLangOptions() {
		return langOptions;
	}

	public void setLangOptions(Map<String, String> langOptions) {
		this.langOptions = langOptions;
	}

	public Map<String, String> getLangNames() {
		return langNames;
	}

	public void setLangNames(Map<String, String> langNames) {
		this.langNames = langNames;
	}
	
	public String getNameForLang(String code){
		String langName = "NA";
		langName = langNames.get(code);
		return langName;
	}
	
	public String getOptionsForLang(String code){
		String options = "NA";
		options = langOptions.get(code);
		return options;
	}
		
	public void addLangName(String key,String value){
		langNames.put(key, value);
	}
	
	public void addLangOptions(String key,String value){
		langOptions.put(key, value);
	}
}
