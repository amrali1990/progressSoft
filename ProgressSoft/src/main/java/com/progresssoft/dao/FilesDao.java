package com.progresssoft.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import com.progresssoft.bean.Files;

public class FilesDao {
	private static final EntityManagerFactory emFactory;
	static {
		emFactory = Persistence.createEntityManagerFactory("persistence");
	}
	@PersistenceContext
	private EntityManager entityManager = emFactory.createEntityManager();
	EntityTransaction transaction = entityManager.getTransaction();

	public void Save(Files file) {

		if (!transaction.isActive()) {
			transaction.begin();
		}
		entityManager.persist(file);
		entityManager.flush();
		entityManager.clear();
		transaction.commit();
	}
	
	public void Delete(Files file) {

		if (!transaction.isActive()) {
			transaction.begin();
		}
		Query query = entityManager.createQuery("delete FROM Files F where F.fileName = :fileName ");
		int deletedCount = query.setParameter("fileName", file.getFileName()).executeUpdate();
		entityManager.flush();
		entityManager.clear();
		transaction.commit();
	}

	public Files getFileByName(String fileName) {
		Files file = new Files();
		try {
			if (!transaction.isActive()) {
				transaction.begin();
			}
			TypedQuery<Files> query = entityManager.createQuery(
					"Select F from Files F where F.fileName = :filename",
					Files.class).setParameter("filename", fileName);
			file = query.getSingleResult();
			entityManager.flush();
			entityManager.clear();
			transaction.commit();

		} catch (Exception e) {
			file = null;
		}
		return file;
	}

}
