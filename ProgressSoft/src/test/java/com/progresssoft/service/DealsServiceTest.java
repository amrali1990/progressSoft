package com.progresssoft.service;

import static org.junit.Assert.*;

import java.text.SimpleDateFormat;
import java.util.HashSet;
import java.util.Set;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.progresssoft.bean.Files;
import com.progresssoft.bean.InValidDeals;
import com.progresssoft.bean.ValidDeals;
import com.progresssoft.dao.DealsDao;
import com.progresssoft.dao.FilesDao;

public class DealsServiceTest {

	
	private FilesDao filesDao;
	private DealsDao dealsDao;
	private String fileName ;
	private DealsService dealsService;
	

	@Before
	public void setUp() {
		filesDao = new FilesDao();
		dealsDao = new DealsDao();
		fileName = "testFile";	
		dealsService = new DealsService();
	}
	
	@After
	public void tearDown() {
		Files file = new Files();
		file = filesDao.getFileByName(fileName);
		dealsDao.deleteValidDealsByFile(file);
		dealsDao.deleteInValidDealsByFile(file);
		filesDao.Delete(file);

	}

	

	@Test
	public void testInsertDeals()  {
		Files file = new Files();
		file.setFileName(fileName);
		assertEquals(file.getFileId(),0);
		filesDao.Save(file);
		assertTrue("Previous (" + file.getFileId() + ") should be greater than current (" + 0 + ")", file.getFileId() > 0);
		
		Set<InValidDeals> inValidDeals=new HashSet<InValidDeals>();
		InValidDeals deal = new InValidDeals();
		deal.setFile(file);
		deal.setAmount(1000.00);
		deal.setFromCurrency("JOD");
		deal.setToCurrency("USD");
		inValidDeals.add(deal);
		file.setInValidDeals(inValidDeals);
		
		Set<ValidDeals> validDeals=new HashSet<ValidDeals>();
		ValidDeals validDeal = new ValidDeals();
		deal.setFile(file);
		deal.setAmount(1000.00);
		deal.setFromCurrency("JOD");
		deal.setToCurrency("USD");
		validDeals.add(validDeal);
		file.setValidDeals(validDeals);
		dealsService.InsertDeals(file);
		
	}

}
