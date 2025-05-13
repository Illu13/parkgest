package controller;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import model.Localidad;

public class LocalidadController {
	
	private static EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("parkgest");
	
	public static List<Localidad> findAll() {

		EntityManager em = entityManagerFactory.createEntityManager();

		Query q = em.createNativeQuery("SELECT * FROM localidad;", Localidad.class);

		List<Localidad> locs = (List<Localidad>) q.getResultList();

		em.close();

		return locs;

	}

}
