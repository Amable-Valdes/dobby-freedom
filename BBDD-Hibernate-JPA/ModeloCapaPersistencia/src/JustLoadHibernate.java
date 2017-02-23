import java.security.NoSuchAlgorithmException;
import java.util.Random;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import model.Category;
import model.Task;
import model.User;
import model.types.UserStatus;
import persistence.util.Jpa;

public class JustLoadHibernate {

	public static void main(String[] args) {

		EntityManagerFactory emf = Persistence
				.createEntityManagerFactory("taskmanager");
		EntityManager em = Jpa.createEntityManager();
		EntityTransaction trx = em.getTransaction();
		trx.begin();
		try {
			Random r = new Random();
			
			User admin = new User("Admin");
			admin.setEmail("admin@email.com");
			admin.setIsAdmin(true);
			admin.setPassword(Encriptator.encrypt("admin"));
			admin.setStatus(UserStatus.ENABLED);

			Thread.sleep(r.nextInt(500) + 750);

			User u1 = new User("usuario1");
			u1.setEmail("usuario1@email.com");
			u1.setIsAdmin(false);
			u1.setPassword(Encriptator.encrypt("usuario1"));
			u1.setStatus(UserStatus.ENABLED);

			Thread.sleep(r.nextInt(500) + 750);

			User u2 = new User("usuario2");
			u2.setEmail("usuario2@email.com");
			u2.setIsAdmin(false);
			u2.setPassword(Encriptator.encrypt("usuario2"));
			u2.setStatus(UserStatus.ENABLED);

			Thread.sleep(r.nextInt(500) + 750);

			User u3 = new User("usuario3");
			u3.setEmail("usuario3@email.com");
			u3.setIsAdmin(false);
			u3.setPassword(Encriptator.encrypt("usuario3"));
			u3.setStatus(UserStatus.ENABLED);

			Thread.sleep(r.nextInt(500) + 750);

			User u4 = new User("usuario4");
			u4.setEmail("usuario4@email.com");
			u4.setIsAdmin(false);
			u4.setPassword(Encriptator.encrypt("usuario4"));
			u4.setStatus(UserStatus.ENABLED);

			Thread.sleep(r.nextInt(500) + 750);

			User u5 = new User("usuario5");
			u5.setEmail("usuario5@email.com");
			u5.setIsAdmin(false);
			u5.setPassword(Encriptator.encrypt("usuario5"));
			u5.setStatus(UserStatus.DISABLED);

			Thread.sleep(r.nextInt(500) + 750);

			Category cadmin = new Category(admin, "Categoria Admin");
			Thread.sleep(r.nextInt(500) + 750);
			Category u1c1 = new Category(u1, "Categoria 1");
			Thread.sleep(r.nextInt(500) + 750);
			Category u1c2 = new Category(u1, "Categoria 2");
			Thread.sleep(r.nextInt(500) + 750);
			Category u2c1 = new Category(u2, "Categoria 1");
			Thread.sleep(r.nextInt(500) + 750);
			Category u2c2 = new Category(u2, "Categoria 2");
			Thread.sleep(r.nextInt(500) + 750);
			Category u2c3 = new Category(u2, "Categoria 3");
			Thread.sleep(r.nextInt(500) + 750);
			Category u3c1 = new Category(u3, "Categoria 1");
			Thread.sleep(r.nextInt(500) + 750);
			Category u3c2 = new Category(u3, "Categoria 2");
			Thread.sleep(r.nextInt(500) + 750);
			Category u4c1 = new Category(u4, "Categoria 1");
			Thread.sleep(r.nextInt(500) + 750);
			Category u4c2 = new Category(u4, "Categoria 2");
			Thread.sleep(r.nextInt(500) + 750);
			Category u4c3 = new Category(u4, "Categoria 3");
			Thread.sleep(r.nextInt(500) + 750);
			Category u5c1 = new Category(u5, "Categoria 1");
			Thread.sleep(r.nextInt(500) + 750);
			Category u5c2 = new Category(u5, "Categoria 2");
			Thread.sleep(r.nextInt(500) + 750);

			Task tadmin = new Task(admin, cadmin, "Tarea admin");
			Thread.sleep(r.nextInt(500) + 750);

			Task u1c1t1 = new Task(u1, u1c1, "Categoria 1 Tarea 1");
			Thread.sleep(r.nextInt(500) + 750);
			Task u1c1t2 = new Task(u1, u1c1, "Categoria 1 Tarea 2");
			Thread.sleep(r.nextInt(500) + 750);
			Task u1c1t3 = new Task(u1, u1c1, "Categoria 1 Tarea 3");
			Thread.sleep(r.nextInt(500) + 750);
			Task u1c2t1 = new Task(u1, u1c2, "Categoria 2 Tarea 1");
			Thread.sleep(r.nextInt(500) + 750);
			Task u1c2t2 = new Task(u1, u2c2, "Categoria 2 Tarea 2");
			Thread.sleep(r.nextInt(500) + 750);

			Task u2c1t1 = new Task(u2, u2c1, "Categoria 1 Tarea 1");
			Thread.sleep(r.nextInt(500) + 750);
			Task u2c2t1 = new Task(u2, u2c2, "Categoria 2 Tarea 2");
			Thread.sleep(r.nextInt(500) + 750);
			Task u2c2t2 = new Task(u2, u2c2, "Categoria 2 Tarea 2");
			Thread.sleep(r.nextInt(500) + 750);
			Task u2c2t3 = new Task(u2, u2c2, "Categoria 2 Tarea 2");
			Thread.sleep(r.nextInt(500) + 750);
			Task u2c3t1 = new Task(u2, u2c3, "Categoria 3 Tarea 1");
			Thread.sleep(r.nextInt(500) + 750);
			Task u2c3t2 = new Task(u2, u2c3, "Categoria 3 Tarea 1");
			Thread.sleep(r.nextInt(500) + 750);

			Task u3c1t1 = new Task(u3, u3c1, "Categoria 1 Tarea 1");
			Thread.sleep(r.nextInt(500) + 750);
			Task u3c1t2 = new Task(u3, u3c1, "Categoria 1 Tarea 2");
			Thread.sleep(r.nextInt(500) + 750);
			Task u3c1t3 = new Task(u3, u3c1, "Categoria 1 Tarea 3");
			Thread.sleep(r.nextInt(500) + 750);
			Task u3c2t1 = new Task(u3, u3c2, "Categoria 2 Tarea 1");
			Thread.sleep(r.nextInt(500) + 750);
			Task u3c2t2 = new Task(u3, u3c2, "Categoria 2 Tarea 2");
			Thread.sleep(r.nextInt(500) + 750);
			Task u3c2t3 = new Task(u3, u3c2, "Categoria 2 Tarea 3");
			Thread.sleep(r.nextInt(500) + 750);

			Task u4c1t1 = new Task(u4, u4c1, "Categoria 1 Tarea 1");
			Thread.sleep(r.nextInt(500) + 750);
			Task u4c1t2 = new Task(u4, u4c1, "Categoria 1 Tarea 2");
			Thread.sleep(r.nextInt(500) + 750);
			Task u4c1t3 = new Task(u4, u4c1, "Categoria 1 Tarea 3");
			Thread.sleep(r.nextInt(500) + 750);
			Task u4c1t4 = new Task(u4, u4c1, "Categoria 1 Tarea 4");
			Thread.sleep(r.nextInt(500) + 750);
			Task u4c2t1 = new Task(u4, u4c2, "Categoria 2 Tarea 1");
			Thread.sleep(r.nextInt(500) + 750);
			Task u4c2t2 = new Task(u4, u4c2, "Categoria 2 Tarea 2");
			Thread.sleep(r.nextInt(500) + 750);
			Task u4c2t3 = new Task(u4, u4c2, "Categoria 2 Tarea 3");
			Thread.sleep(r.nextInt(500) + 750);
			Task u4c3t1 = new Task(u4, u4c3, "Categoria 3 Tarea 1");
			Thread.sleep(r.nextInt(500) + 750);
			Task u4c3t2 = new Task(u4, u4c3, "Categoria 3 Tarea 2");
			Thread.sleep(r.nextInt(500) + 750);
			Task u4c3t3 = new Task(u4, u4c3, "Categoria 3 Tarea 3");
			Thread.sleep(r.nextInt(500) + 750);
			Task u4c3t4 = new Task(u4, u4c3, "Categoria 3 Tarea 4");
			Thread.sleep(r.nextInt(500) + 750);
			Task u4c3t5 = new Task(u4, u4c3, "Categoria 3 Tarea 5");
			Thread.sleep(r.nextInt(500) + 750);

			Task u5c1t1 = new Task(u5, u5c1, "Categoria 1 Tarea 1");
			Thread.sleep(r.nextInt(500) + 750);
			Task u5c1t2 = new Task(u5, u5c1, "Categoria 1 Tarea 2");
			Thread.sleep(r.nextInt(500) + 750);
			Task u5c1t3 = new Task(u5, u5c1, "Categoria 1 Tarea 3");
			Thread.sleep(r.nextInt(500) + 750);
			Task u5c2t1 = new Task(u5, u5c2, "Categoria 2 Tarea 1");
			Thread.sleep(r.nextInt(500) + 750);
			Task u5c2t2 = new Task(u5, u5c2, "Categoria 2 Tarea 2");
			Thread.sleep(r.nextInt(500) + 750);
			Task u5c2t3 = new Task(u5, u5c2, "Categoria 2 Tarea 3");
			Thread.sleep(r.nextInt(500) + 750);

			em.persist(admin);
			em.persist(u1);
			em.persist(u2);
			em.persist(u3);
			em.persist(u4);
			em.persist(u5);

			em.persist(cadmin);
			em.persist(u1c1);
			em.persist(u1c2);
			em.persist(u2c1);
			em.persist(u2c2);
			em.persist(u2c3);
			em.persist(u3c1);
			em.persist(u3c2);
			em.persist(u4c1);
			em.persist(u4c2);
			em.persist(u4c3);
			em.persist(u5c1);
			em.persist(u5c2);

			em.persist(tadmin);
			em.persist(u1c1t1);
			em.persist(u1c1t2);
			em.persist(u1c1t3);
			em.persist(u1c2t1);
			em.persist(u1c2t2);
			em.persist(u2c1t1);
			em.persist(u2c2t1);
			em.persist(u2c2t2);
			em.persist(u2c2t3);
			em.persist(u2c3t1);
			em.persist(u2c3t2);
			em.persist(u3c1t1);
			em.persist(u3c1t2);
			em.persist(u3c1t3);
			em.persist(u3c2t1);
			em.persist(u3c2t2);
			em.persist(u3c2t3);
			em.persist(u4c1t1);
			em.persist(u4c1t2);
			em.persist(u4c1t3);
			em.persist(u4c1t4);
			em.persist(u4c2t1);
			em.persist(u4c2t2);
			em.persist(u4c2t3);
			em.persist(u4c3t1);
			em.persist(u4c3t2);
			em.persist(u4c3t3);
			em.persist(u4c3t4);
			em.persist(u4c3t5);
			em.persist(u5c1t1);
			em.persist(u5c1t2);
			em.persist(u5c1t3);
			em.persist(u5c2t1);
			em.persist(u5c2t2);
			em.persist(u5c2t3);

		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}

		trx.commit();
		em.close();
		emf.close();

		System.out.println("--> Si no hay excepciones todo va bien");
		System.out.println("\n\t (O no hay ninguna clase mapeada)");
	}

}
