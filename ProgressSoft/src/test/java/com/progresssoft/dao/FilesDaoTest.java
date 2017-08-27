package com.progresssoft.dao;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.progresssoft.bean.Files;

public class FilesDaoTest {
	
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
	public void testSave() {
		Files file = new Files();
		file.setFileName(fileName);
		assertEquals(file.getFileId(),0);
		filesDao.Save(file);
		assertTrue("Previous (" + file.getFileId() + ") should be greater than current (" + 0 + ")", file.getFileId() > 0);		
	}
	

	@Test
	public void testGetFileByName() {
		Files file = new Files();
		file.setFileName(fileName);
		assertEquals(file.getFileId(),0);
		filesDao.Save(file);
		assertTrue("Previous (" + file.getFileId() + ") should be greater than current (" + 0 + ")", file.getFileId() > 0);		
		
		Files file2 = new Files();
		file2 = filesDao.getFileByName(fileName);
		assertEquals(file2.getFileName(), file.getFileName());		
	}

}
