package com.bidc.data;

import java.util.Date;
import java.util.Map;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class BIDCProduct extends AppEntity {

	@Id
	String id;
		
	String name;
	
	String description;
	
	String type;
	
	
	@ElementCollection
	Map<String, String> attributeMap;
	
	
	
	
	String createdBy;

	Date createdOn;

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public BIDCProduct() {
		id = "P" + System.currentTimeMillis();
		setCreatedBy("System");
		setCreatedOn(new Date());

	}

	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(String type) {
		this.type = type;
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

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the attributeMap
	 */
	public Map<String, String> getAttributeMap() {
		return attributeMap;
	}

	/**
	 * @param attributeMap the attributeMap to set
	 */
	public void setAttributeMap(Map<String, String> attributeMap) {
		this.attributeMap = attributeMap;
	}
	
	
	public void addAttribute(String key,String value){
		attributeMap.put(key, value);
	}
	
	public void removeAttribute(String key){
		attributeMap.remove(key);
	}
}
