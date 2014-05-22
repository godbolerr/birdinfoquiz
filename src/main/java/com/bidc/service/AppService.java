package com.bidc.service;

import java.util.List;

import com.bidc.data.BirdInfo;

public interface AppService {
	
	
	BirdInfo getBirdInfo(String id);
	
	List<BirdInfo> getBirds(int size);
	
	List<BirdInfo> getAllBirds();
	
	public void addBird(BirdInfo info);
	
	public void updateBirdInfo(BirdInfo info);
	
	public void deleteBird(BirdInfo info);

}
