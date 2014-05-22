/**
 * 
 */
package com.bidc.service.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Logger;

import com.bidc.data.AppDao;
import com.bidc.data.BirdInfo;
import com.bidc.data.EmfDao;
import com.bidc.service.AppService;

/**
 * @author Ravi
 *
 */
public class AppServiceImpl implements AppService {
	
	private final Logger logger = Logger.getLogger(AppServiceImpl.class
			.getName());

	AppDao dao = null;

	/**
	 * 
	 */
	public AppServiceImpl() {
		dao = EmfDao.INSTANCE;
	}

	/* (non-Javadoc)
	 * @see com.bidc.service.AppService#getBirdInfo(java.lang.String)
	 */
	@Override
	public BirdInfo getBirdInfo(String id) {		
		return dao.get(BirdInfo.class, id);

	}

	/* (non-Javadoc)
	 * @see com.bidc.service.AppService#getBirds(int)
	 */
	@Override
	public List<BirdInfo> getBirds(int size) {
		
		List<BirdInfo> bList = new ArrayList<BirdInfo>();
		
		List<BirdInfo> tempList = dao.getAll(BirdInfo.class);
		
		int count = 0;
		for (Iterator<BirdInfo> iterator = tempList.iterator(); iterator.hasNext();) {
			BirdInfo birdInfo = (BirdInfo) iterator.next();
			bList.add(birdInfo);
			if ( count++ > size ){
				break;
			}
		}
		
		
		return bList;
		
	}

	/* (non-Javadoc)
	 * @see com.bidc.service.AppService#getAllBirds()
	 */
	@Override
	public List<BirdInfo> getAllBirds() {
		return dao.getAll(BirdInfo.class);
	}

	/* (non-Javadoc)
	 * @see com.bidc.service.AppService#addBird(com.bidc.data.BirdInfo)
	 */
	@Override
	public void addBird(BirdInfo info) {		
		dao.add(BirdInfo.class, info);
	}

	/* (non-Javadoc)
	 * @see com.bidc.service.AppService#updateBirdInfo(com.bidc.data.BirdInfo)
	 */
	@Override
	public void updateBirdInfo(BirdInfo info) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see com.bidc.service.AppService#deleteBird(com.bidc.data.BirdInfo)
	 */
	@Override
	public void deleteBird(BirdInfo info) {
		dao.delete(BirdInfo.class, info.getId());
	}

}
