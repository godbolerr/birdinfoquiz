/**
 * 
 */
package com.bidc.data;

import java.io.Serializable;
import java.util.Date;

/**
 * All managed objects have following attributes
 * @author Ravi
 *
 */
public interface Managed extends Serializable ,Cloneable{
	
	public String getId();
	
	public boolean isActive();
	
	public String getCreatedBy();
	
	public Date getCreatedOn();
	

}
