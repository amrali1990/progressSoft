package com.progresssoft.service;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;





import javax.persistence.PersistenceException;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

import com.progresssoft.bean.Files;
import com.progresssoft.bean.InValidDeals;
import com.progresssoft.bean.ValidDeals;
import com.progresssoft.dao.DealsDao;
import com.progresssoft.dao.FilesDao;

public class FilesService {
	private String TEMP_LOCATION = "E:\\";
	private String DATE = "date";
	private String TO_CURRENCY = "toCurrency";
	private String FROM_CURRENCY = "fromCurrency";
	private String AMOUNT = "amount";
	//private String ID = "dealId";
	private String DATE_PATTERN = "dd/MM/yyyy";
	
	private FilesDao filesDao;
	private DealsDao dealsDao;

	public FilesService() {
		filesDao = new FilesDao();
		dealsDao = new DealsDao();
	}

	public Files createFile(String fileName) throws PersistenceException, IOException{
		Files file = new Files();
		try {			
			file.setFileName(fileName);
			List<CSVRecord> validList = new ArrayList<>();
			List<CSVRecord> invalidList = new ArrayList<>();
			Reader in = new FileReader(TEMP_LOCATION + fileName);
			Iterable<CSVRecord> record = CSVFormat.EXCEL.withFirstRecordAsHeader().withAllowMissingColumnNames().withIgnoreHeaderCase().parse(in);
			for (CSVRecord r : record) {
				if (r.get(DATE).isEmpty() || r.get(FROM_CURRENCY).isEmpty() || r.get(TO_CURRENCY).isEmpty() || r.get(AMOUNT).isEmpty()) {
					invalidList.add(r);
				} else {
					validList.add(r);
				}
			}
			file.setInValidDeals(setInvalidDeal(invalidList,fileName));
			file.setValidDeals(setValidDeal(validList,fileName));
			Files f = new Files();
			f = getFilesDao().getFileByName(fileName);
			if(f != null){
				throw new PersistenceException();
			}else{
			getFilesDao().Save(file);
			}
		} catch (PersistenceException e) {
			file = new Files();
			file.setFileId(0);
			return file;
		}
		return file;
	}
	
	
	
	private Set<ValidDeals> setValidDeal(List<CSVRecord> validList, String fileName) {
	    Set<ValidDeals> deals=new HashSet<ValidDeals>();
	    ValidDeals deal = null;
	    //int maxId = getDealsDao().getMaxValidDealsId();
	    for(CSVRecord r:validList){
	        deal = new ValidDeals();
	        try {
	            deal.setTimestamp(new SimpleDateFormat(DATE_PATTERN).parse(r.get(DATE)));
	        } catch (ParseException e) {
	            e.printStackTrace();
	        }
	        //deal.setValidDealId(++maxId);
	        deal.setFromCurrency(r.get(FROM_CURRENCY));
	        deal.setToCurrency(r.get(TO_CURRENCY));
	        deal.setAmount(Double.parseDouble(r.get(AMOUNT)));
	        deals.add(deal);
	    }
	    return deals;
	}
	
	private Set<InValidDeals> setInvalidDeal(List<CSVRecord> validList, String fileName) {
		Set<InValidDeals> deals=new HashSet<InValidDeals>();
	    InValidDeals deal = null;
	    //int maxId = getDealsDao().getMaxInValidDealsId();
	    for(CSVRecord r:validList){
	        deal = new InValidDeals();
	        try {
	            deal.setTimestamp(new SimpleDateFormat(DATE_PATTERN).parse(r.get(DATE)));
	        } catch (ParseException e) {
	            e.printStackTrace();
	        }
	        //deal.setInValidDealId(++maxId);
	        deal.setFromCurrency(r.get(FROM_CURRENCY));
	        deal.setToCurrency(r.get(TO_CURRENCY));
	        deal.setAmount(Double.parseDouble(r.get(AMOUNT)));
	        deals.add(deal);
	    }
	    return deals;
	}
	
	public Files getFileByName(String fileName) {
		return getFilesDao().getFileByName(fileName);
	}

	

	public FilesDao getFilesDao() {
		return filesDao;
	}

	public void setFilesDao(FilesDao filesDao) {
		this.filesDao = filesDao;
	}

	public DealsDao getDealsDao() {
		return dealsDao;
	}

	public void setDealsDao(DealsDao dealsDao) {
		this.dealsDao = dealsDao;
	}
}
