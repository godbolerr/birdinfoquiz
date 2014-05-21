/**
 * 
 */
package com.bidc.data;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * @author ravi
 *
 */
@Entity
public class QRecord  extends AppEntity {
	
	
	
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	public QRecord() {
		// TODO Auto-generated constructor stub
	}
	
	
	@Id
	String id;

	/**
	 * Is this template active
	 */
	boolean active;

	/**
	 * Person who created the template.
	 */
	String createdBy;

	/**
	 * Date on which template was created.
	 * 
	 */
	Date createdOn;

	/**
	 * For which standard 5,6,7,8,9,10,etc
	 */
	String level;

	/**
	 * Medium,High,Low
	 */
	String complexity;

	/**
	 * Various areas within mathematics
	 */
	String category;

	/**
	 * Entire question stored as string.
	 */
	com.google.appengine.api.datastore.Text qXml;

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return the active
	 */
	public boolean isActive() {
		return active;
	}

	/**
	 * @param active the active to set
	 */
	public void setActive(boolean active) {
		this.active = active;
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
	 * @return the level
	 */
	public String getLevel() {
		return level;
	}

	/**
	 * @param level the level to set
	 */
	public void setLevel(String level) {
		this.level = level;
	}

	/**
	 * @return the complexity
	 */
	public String getComplexity() {
		return complexity;
	}

	/**
	 * @param complexity the complexity to set
	 */
	public void setComplexity(String complexity) {
		this.complexity = complexity;
	}

	/**
	 * @return the category
	 */
	public String getCategory() {
		return category;
	}

	/**
	 * @param category the category to set
	 */
	public void setCategory(String category) {
		this.category = category;
	}

	/**
	 * @return the qXml
	 */
	public String getqXml() {
		return qXml.getValue();
	}

	/**
	 * @param qXml the qXml to set
	 */
	public void setqXml(String qXml) {
		this.qXml = new com.google.appengine.api.datastore.Text(qXml);
	}

}
