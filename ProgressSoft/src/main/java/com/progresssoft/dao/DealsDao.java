package com.progresssoft.dao;

import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import com.progresssoft.bean.Files;
import com.progresssoft.bean.InValidDeals;
import com.progresssoft.bean.ValidDeals;

public class DealsDao {
	
	private static final EntityManagerFactory emFactory;
	static {
		emFactory = Persistence.createEntityManagerFactory("persistence");
	}
	@PersistenceContext
	private EntityManager entityManager = emFactory.createEntityManager();
	EntityTransaction transaction = entityManager.getTransaction();

	public void InsertValidDeals(Files file) {
		int count = 1;
		int batchSize = Math.min(file.getValidDeals().size(), 50);
		Set<ValidDeals> validDeals = file.getValidDeals();
		transaction.begin();
		for (ValidDeals VD : validDeals) {
			VD.setFile(file);
			entityManager.persist(VD);
			count++;
			if (count % batchSize == 0) {
				entityManager.flush();
				entityManager.clear();
			}
		}
		transaction.commit();
	}

	public void InsertInValidDeals(Files file) {
		int count = 0;
		int batchSize = Math.min(file.getInValidDeals().size(), 50);
		transaction.begin();
		for (InValidDeals VD : file.getInValidDeals()) {
			VD.setFile(file);
			entityManager.persist(VD);
			count++;
			if (count % batchSize == 0) {
				entityManager.flush();
				entityManager.clear();
			}
		}
		transaction.commit();
	}
	
	
	public List<ValidDeals> getValidDealsByFileName(String fileName) {
		transaction.begin();
		TypedQuery<ValidDeals> query = entityManager.createQuery("SELECT VD FROM Files F left join F.validDeals VD where F.fileName = :fileName ",	ValidDeals.class);
		List<ValidDeals> validDeals = query.setParameter("fileName", fileName).getResultList();
		entityManager.flush();
		entityManager.clear();
		transaction.commit();
		return validDeals;
		
	}
	
	public List<InValidDeals> getInValidDealsByFileName(String fileName) {
		transaction.begin();
		TypedQuery<InValidDeals> query = entityManager.createQuery("SELECT IVD FROM Files F left join F.inValidDeals IVD where F.fileName = :fileName ",	InValidDeals.class);
		List<InValidDeals> inValidDeals = query.setParameter("fileName", fileName).getResultList();
		entityManager.flush();
		entityManager.clear();
		transaction.commit();
		return inValidDeals;
		
	}
	
	public void deleteValidDealsByFile(Files file) {
		transaction.begin();
		Query query = entityManager.createQuery("delete FROM ValidDeals VD where VD.file = :file ");
		int deletedCount = query.setParameter("file", file).executeUpdate();
		entityManager.flush();
		entityManager.clear();
		transaction.commit();
		
	}
	
	public void deleteInValidDealsByFile(Files file) {
		transaction.begin();
		Query query = entityManager.createQuery("delete FROM InValidDeals IVD where IVD.file = :file ");
		int deletedCount = query.setParameter("file", file).executeUpdate();
		entityManager.flush();
		entityManager.clear();
		transaction.commit();
		
	}
	
	/*public int getMaxValidDealsId() {
		int max = 0;
		max = (int) entityManager.createQuery("select coalesce(max(VD.validDealId), 0) from ValidDeals VD").getSingleResult();	
		return max;
		
	}
	
	public int getMaxInValidDealsId() {
		int max = 0;
		max = (int) entityManager.createQuery("select coalesce(max(IVD.inValidDealId), 0) from InValidDeals IVD").getSingleResult();	
		return max;
		
	}*/
}
