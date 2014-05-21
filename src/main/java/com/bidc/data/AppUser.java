/**
 * 
 */
package com.bidc.data;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.xml.bind.annotation.XmlElement;

/**
 * @author 115750
 *
 */
@Entity
public class AppUser extends AppEntity {
	
	@Id
	String id;
	
	
	String socialId;
	

	String createdBy;


	Date createdOn;
	
	/**
	 * Entire question stored as string.
	 */
	com.google.appengine.api.datastore.Text geoData;
 
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	public AppUser() {
		id = "U" + System.currentTimeMillis(); 
		setCreatedBy("System");
		setCreatedOn(new Date());
	}

	/* (non-Javadoc)
	 * @see com.bidc.data.Managed#getId()
	 */
	@Override
	public String getId() {
		return id;
	}

	
	/**
	 * @return the qXml
	 */
	public String getGeoData() {
		return geoData.getValue();
	}

	/**
	 * @param qXml the qXml to set
	 */
	public void setGeoData(String data) {
		this.geoData = new com.google.appengine.api.datastore.Text(data);
	}

	/**
	 * @return the socialId
	 */
	public String getSocialId() {
		return socialId;
	}

	/**
	 * @param socialId the socialId to set
	 */
	public void setSocialId(String socialId) {
		this.socialId = socialId;
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
	
	
}
