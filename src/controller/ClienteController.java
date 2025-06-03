package controller;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import model.Cliente;

public class ClienteController {

	private static EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("parkgest");

	public static List<Cliente> findAll() {

		EntityManager em = entityManagerFactory.createEntityManager();

		Query q = em.createNativeQuery("SELECT * FROM cliente order by nombre;", Cliente.class);

		List<Cliente> locs = (List<Cliente>) q.getResultList();

		em.close();

		return locs;

	}

	public static boolean insertar(Cliente c) {
		EntityManager em = entityManagerFactory.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		try {
			tx.begin();
			em.persist(c);
			tx.commit(); // Mover commit dentro del try
			return true;
		} catch (Exception e) {
			if (tx != null && tx.isActive()) {
				tx.rollback(); // Rollback si la transacción está activa
			}
			return false;
		} finally {
			em.close(); // Cerrar EntityManager siempre
		}
	}

	public static boolean update(Cliente c) {

		EntityManager em = entityManagerFactory.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		try {
			tx.begin();
			em.merge(c);
			tx.commit(); 
			return true;
		} catch (Exception e) {
			if (tx != null && tx.isActive()) {
				tx.rollback(); // Rollback si la transacción está activa
			}
			return false;
		} finally {
			em.close(); // Cerrar EntityManager siempre
		}
	}

	public static boolean eliminar(Cliente c) {
		EntityManager em = entityManagerFactory.createEntityManager();

		try {
			em.getTransaction().begin();
			c = em.merge(c);
			em.remove(c);
			em.getTransaction().commit();
			return true;
		} catch (Exception e) {
			return false;
		} finally {
			em.close();
		}
	}

}
