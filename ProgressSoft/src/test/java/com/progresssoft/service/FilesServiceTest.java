package com.progresssoft.service;

import static org.junit.Assert.*;

import java.io.IOException;

import javax.persistence.PersistenceException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.progresssoft.bean.Files;
import com.progresssoft.dao.DealsDao;
import com.progresssoft.dao.FilesDao;

public class FilesServiceTest {

	private FilesDao filesDao;
	private DealsDao dealsDao;
	private String fileName ;
	private FilesService filesService;
	
	@Before
	public void setUp() {
		filesDao = new FilesDao();
		dealsDao = new DealsDao();
		fileName = "fortestcase.csv";	
		filesService = new FilesService();
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
	public void testCreateFile() throws PersistenceException, IOException {
		Files file = new Files();
		file = filesService.createFile(fileName);
		
		Files file2 = new Files();
		file2.setFileName(fileName);
		
		assertEquals(file2.getFileName(), file.getFileName());
		
	}

	@Test
	public void testGetFileByName() {
		Files file = new Files();
		file.setFileName(fileName);
		assertEquals(file.getFileId(),0);
		filesDao.Save(file);
		assertTrue("Previous (" + file.getFileId() + ") should be greater than current (" + 0 + ")", file.getFileId() > 0);		
		
		Files file2 = new Files();
		file2 = filesService.getFileByName(fileName);
		assertEquals(file2.getFileName(), file.getFileName());	
	}

}
