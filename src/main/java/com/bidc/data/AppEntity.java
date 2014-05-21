/**
 * 
 */
package com.bidc.data;

import java.util.Date;

/**
 * @author 115750
 * 
 */
@SuppressWarnings("serial")
public abstract class AppEntity implements Managed {

	boolean active;


	


	/**
	 * 
	 */
	public AppEntity() {
	}

	/**
	 * @return the active
	 */
	public boolean isActive() {
		return active;
	}

	/**
	 * @param active
	 *            the active to set
	 */
	public void setActive(boolean active) {
		this.active = active;
	}




	

}
