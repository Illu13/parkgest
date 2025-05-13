package controller;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import model.Cliente;
import model.Vehiculo;

public class VehiculoController {
	private static EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("parkgest");

	public static Vehiculo findVehiculoByMatricula(String matricula) {
		

		EntityManager em = entityManagerFactory.createEntityManager();

		Query q = em.createNativeQuery("SELECT * FROM vehiculo where matricula = ?", Vehiculo.class);
		q.setParameter(1, matricula);
		Vehiculo es;
		try {
		es = (Vehiculo) q.getSingleResult();
	
		} catch (NoResultException e) {
			es = null;
		} finally {
			em.close();
		}
		return es;



		
	}
	
	public static boolean insertar(Vehiculo c) {
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

}
