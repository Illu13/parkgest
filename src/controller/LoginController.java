package controller;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.Query;

import model.Localidad;
import model.Usuario;

public class LoginController {

	private static EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("parkgest");

	public static Usuario findAll(Usuario us) {

		EntityManager em = entityManagerFactory.createEntityManager();

		Query q = em.createNativeQuery("SELECT * FROM usuario where nombre = ?;", Usuario.class);

		q.setParameter(1, us.getNombre());
		try {
			Usuario locs = (Usuario) q.getSingleResult();
			em.close();

			if (locs.getPass().equals(us.getPass())) {
				return locs;

			} else {
				return null;

			}
		} catch (NoResultException e) {
			return null;
		}

	}

}
