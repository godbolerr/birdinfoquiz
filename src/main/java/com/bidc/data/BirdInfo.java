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
	
	Map<String,String> names;

	Map<String,String> alternativeNames;

	String englishNames;
	
	
	String createdBy;

	Date createdOn;

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public BirdInfo() {
		id = "C" + System.currentTimeMillis();
		setCreatedBy("System");
		setCreatedOn(new Date());
		alternativeNames = new HashMap<String,String>();
		names = new HashMap<String,String>();

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
	 * @param createdBy the createdBy to set
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
	 * @param createdOn the createdOn to set
	 */
	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}

	public Map<String, String> getAlternativeNames() {
		return alternativeNames;
	}

	public void setAlternativeNames(Map<String, String> alternativeNames) {
		this.alternativeNames = alternativeNames;
	}

	public Map<String, String> getNames() {
		return names;
	}

	public void setNames(Map<String, String> names) {
		this.names = names;
	}
	
	
	public void addAlternativeName(String key,String value){
		alternativeNames.put(key, value);
	}
	
	public void addName(String key,String value){
		names.put(key, value);
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
	
	public String getEnglishNames(){
		
		if ( names != null ){
			return names.get("en");
		} else {
			return "NA";
		}
		
	}

}
