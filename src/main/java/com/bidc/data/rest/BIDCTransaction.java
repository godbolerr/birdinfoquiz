package com.bidc.data.rest;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement(name = "BIDCTransaction")
public class BIDCTransaction  {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	

	@XmlElement
	String id;
	
	@XmlElement
	double amount;
	
	@XmlElement
	String accountId;
	
	@XmlElement
	String type;
	
	@XmlElement
	String note;
	
	

	public BIDCTransaction() {
	
	}



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
	 * @return the amount
	 */
	public double getAmount() {
		return amount;
	}



	/**
	 * @param amount the amount to set
	 */
	public void setAmount(double amount) {
		this.amount = amount;
	}



	/**
	 * @return the accountId
	 */
	public String getAccountId() {
		return accountId;
	}



	/**
	 * @param accountId the accountId to set
	 */
	public void setAccountId(String accountId) {
		this.accountId = accountId;
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



	/**
	 * @return the note
	 */
	public String getNote() {
		return note;
	}



	/**
	 * @param note the note to set
	 */
	public void setNote(String note) {
		this.note = note;
	}
	
	
	

}
