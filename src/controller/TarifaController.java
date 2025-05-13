package controller;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import model.Tarifa;

public class TarifaController {
	
	private static EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("parkgest");
	
	public static List<Tarifa> findAll() {

		EntityManager em = entityManagerFactory.createEntityManager();

		Query q = em.createNativeQuery("SELECT * FROM tarifa;", Tarifa.class);

		List<Tarifa> locs = (List<Tarifa>) q.getResultList();

		em.close();

		return locs;

	}


}
