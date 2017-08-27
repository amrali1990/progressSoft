package com.progresssoft.service;

import java.util.List;

import com.progresssoft.bean.Files;
import com.progresssoft.bean.InValidDeals;
import com.progresssoft.bean.ValidDeals;
import com.progresssoft.dao.DealsDao;

public class DealsService {
	
	private DealsDao dealsDao;
	
	public DealsService(){
		dealsDao = new DealsDao();
	}
	
	public void InsertDeals(Files file) {
		getDealsDao().InsertValidDeals(file);
		getDealsDao().InsertInValidDeals(file);
	}
	
	public DealsDao getDealsDao() {
		return dealsDao;
	}
	public void setDealsDao(DealsDao dealsDao) {
		this.dealsDao = dealsDao;
	}
	
	public List<ValidDeals> getValidDealsByFileName(String fileName) {
		return dealsDao.getValidDealsByFileName(fileName);
	}
	
	public List<InValidDeals> getInValidDealsByFileName(String fileName) {
		return dealsDao.getInValidDealsByFileName(fileName);
	}
	

}
