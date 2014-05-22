package com.bidc.service;

import java.util.List;
import java.util.Map;

import com.bidc.data.BirdInfo;
import com.bidc.data.Customer;

public interface AppService {
	

	/**
	 * Check if user from this provider is registered with system
	 * 
	 * @param providerId
	 * @param userId
	 * @return
	 */
	public boolean isUserRegistered(String providerId, String userId)
			throws BIDCException;

	/**
	 * Register new user with the system
	 * 
	 * Customer id and account is created within the system
	 * 
	 * @param bUser
	 * @param context
	 * @return
	 */
	public Customer registerUser(Customer bUser,
			Map<String, String> context) throws BIDCException;

	public Customer getCustomer(String providerId, String socialId)
			throws BIDCException;

	BirdInfo getBirdInfo(String id);
	
	List<BirdInfo> getBirds(int size);
	
	List<BirdInfo> getAllBirds();
	
	public void addBird(BirdInfo info);
	
	public void updateBirdInfo(BirdInfo info);
	
	public void deleteBird(BirdInfo info);

}
