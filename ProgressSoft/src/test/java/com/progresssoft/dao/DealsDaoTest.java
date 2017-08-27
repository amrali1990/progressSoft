package com.progresssoft.dao;

import static org.junit.Assert.*;

import java.text.SimpleDateFormat;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.hamcrest.CoreMatchers.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.progresssoft.bean.Files;
import com.progresssoft.bean.InValidDeals;
import com.progresssoft.bean.ValidDeals;

public class DealsDaoTest {

	private FilesDao filesDao;
	private DealsDao dealsDao;
	private String fileName ;
	

	@Before
	public void setUp() {
		filesDao = new FilesDao();
		dealsDao = new DealsDao();
		fileName = "testFile";		
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
	public void testInsertValidDeals() throws Exception {
		
		Files file = new Files();
		file.setFileName(fileName);
		assertEquals(file.getFileId(),0);
		filesDao.Save(file);
		assertTrue("Previous (" + file.getFileId() + ") should be greater than current (" + 0 + ")", file.getFileId() > 0);
		
		Set<ValidDeals> validDeals=new HashSet<ValidDeals>();
		ValidDeals deal = new ValidDeals();
		deal.setFile(file);
		deal.setAmount(1000.00);
		deal.setFromCurrency("JOD");
		deal.setToCurrency("USD");
		deal.setTimestamp(new SimpleDateFormat("dd/MM/yyyy").parse("8/2/2021"));
		validDeals.add(deal);
		file.setValidDeals(validDeals);
		dealsDao.InsertValidDeals(file);
		
		Files file2 = new Files();
		file2 = filesDao.getFileByName(fileName);
		assertEquals(file2.getFileName(), file.getFileName());
		
		List<ValidDeals> validDeals2 = dealsDao.getValidDealsByFileName(file2.getFileName());
		ValidDeals deal2 = new ValidDeals();
		deal2 = validDeals2.get(0);
			
		assertEquals(deal2.getToCurrency(), deal.getToCurrency());
		assertEquals(deal2.getFromCurrency(), deal.getFromCurrency());
		assertEquals(deal2.getAmount(), deal.getAmount());
		
	}
	
	
	@Test
	public void testInsertInValidDeals() throws Exception {
		
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
		deal.setTimestamp(new SimpleDateFormat("dd/MM/yyyy").parse("8/2/2021"));
		inValidDeals.add(deal);
		file.setInValidDeals(inValidDeals);
		dealsDao.InsertInValidDeals(file);
		
		Files file2 = new Files();
		file2 = filesDao.getFileByName(fileName);
		assertEquals(file2.getFileName(), file.getFileName());
		
		List<InValidDeals> inValidDeals2 = dealsDao.getInValidDealsByFileName(file2.getFileName());
		InValidDeals deal2 = new InValidDeals();
		deal2 = inValidDeals2.get(0);
			
		assertEquals(deal2.getToCurrency(), deal.getToCurrency());
		assertEquals(deal2.getFromCurrency(), deal.getFromCurrency());
		assertEquals(deal2.getAmount(), deal.getAmount());
		
	}

	

}
