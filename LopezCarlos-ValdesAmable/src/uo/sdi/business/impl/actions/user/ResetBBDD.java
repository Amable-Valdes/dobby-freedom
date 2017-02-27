package uo.sdi.business.impl.actions.user;

import java.util.List;
import java.util.Date;

import uo.sdi.business.util.BusinessException;
import uo.sdi.business.util.Command;
import uo.sdi.business.util.Encriptator;
import uo.sdi.model.Association;
import uo.sdi.model.Category;
import uo.sdi.model.Task;
import uo.sdi.model.User;
import uo.sdi.model.types.UserStatus;
import uo.sdi.persistence.CategoryFinder;
import uo.sdi.persistence.TaskFinder;
import uo.sdi.persistence.UserFinder;
import uo.sdi.persistence.util.Jpa;

public class ResetBBDD implements Command {

	@Override
	public Object execute() throws BusinessException {
		// Borramos todos los datos de la base de datos
		// Borramos las tareas
		List<Task> tasks = TaskFinder.findAll();
		for (Task task : tasks) {
			Jpa.getManager().remove(task);
		}
		// Borramos las Categorias
		List<Category> categories = CategoryFinder.findAll();
		for (Category category : categories) {
			Jpa.getManager().remove(category);
		}
		// Borramos los Usuarios (excepto Admin)
		List<User> users = UserFinder.findAll();
		for (User user : users) {
			if (user.getLogin() != "admin1") {
				Jpa.getManager().remove(user);
			}
		}

		// Añadimos los nuevos datos
		// 3 usuarios
		User user1 = new User("user1");
		user1.setEmail("user1@email.com");
		user1.setIsAdmin(false);
		user1.setPassword(Encriptator.encrypt("user1"));
		user1.setStatus(UserStatus.ENABLED);

		User user2 = new User("user2");
		user2.setEmail("user2@email.com");
		user2.setIsAdmin(false);
		user2.setPassword(Encriptator.encrypt("user2"));
		user2.setStatus(UserStatus.ENABLED);

		User user3 = new User("user3");
		user3.setEmail("user3@email.com");
		user3.setIsAdmin(false);
		user3.setPassword(Encriptator.encrypt("user3"));
		user3.setStatus(UserStatus.ENABLED);

		//Para cada usuario
		for (int i = 1; i <= 3; i++) {
			User user = null;
			//Seleccionamos el usuario que vamos a rellenar
			switch (i) {
			case 1:
				user = user1;
				break;
			case 2:
				user = user2;
				break;
			case 3:
				user = user3;
				break;
			}
			// 3 Categorias
			Category categoria1 = new Category(user);
			categoria1.setName("Categoria1");

			Category categoria2 = new Category(user);
			categoria2.setName("Categoria2");

			Category categoria3 = new Category(user);
			categoria3.setName("Categoria3");
			
			// 30 tareas
			// Tareas para dentro de 6 días
			Date next6Days = new Date(System.currentTimeMillis() + 518400000);
			Task task1 = new Task(user, "tarea1");
			task1.setPlanned(next6Days);
			Task task2 = new Task(user, "tarea2");
			task2.setPlanned(next6Days);
			Task task3 = new Task(user, "tarea3");
			task3.setPlanned(next6Days);
			Task task4 = new Task(user, "tarea4");
			task4.setPlanned(next6Days);
			Task task5 = new Task(user, "tarea5");
			task5.setPlanned(next6Days);
			Task task6 = new Task(user, "tarea6");
			task6.setPlanned(next6Days);
			Task task7 = new Task(user, "tarea7");
			task7.setPlanned(next6Days);
			Task task8 = new Task(user, "tarea8");
			task8.setPlanned(next6Days);
			Task task9 = new Task(user, "tarea9");
			task9.setPlanned(next6Days);
			Task task10 = new Task(user, "tarea10");
			task10.setPlanned(next6Days);

			// Tareas para hoy
			Date today = new Date();
			Task task11 = new Task(user, "tarea11");
			task11.setPlanned(today);
			Task task12 = new Task(user, "tarea12");
			task12.setPlanned(today);
			Task task13 = new Task(user, "tarea13");
			task13.setPlanned(today);
			Task task14 = new Task(user, "tarea14");
			task14.setPlanned(today);
			Task task15 = new Task(user, "tarea15");
			task15.setPlanned(today);
			Task task16 = new Task(user, "tarea16");
			task16.setPlanned(today);
			Task task17 = new Task(user, "tarea17");
			task17.setPlanned(today);
			Task task18 = new Task(user, "tarea18");
			task18.setPlanned(today);
			Task task19 = new Task(user, "tarea19");
			task19.setPlanned(today);
			Task task20 = new Task(user, "tarea20");
			task20.setPlanned(today);

			// Tareas retrasadas (de hace 6 días)
			Date last6Days = new Date(System.currentTimeMillis() - 518400000);
			Task task21 = new Task(user, "tarea21");
			task21.setPlanned(last6Days);
			Association.Classifies.link(categoria1, task21);
			Task task22 = new Task(user, "tarea22");
			task22.setPlanned(last6Days);
			Association.Classifies.link(categoria1, task22);
			Task task23 = new Task(user, "tarea23");
			task23.setPlanned(last6Days);
			Association.Classifies.link(categoria1, task23);
			Task task24 = new Task(user, "tarea24");
			task24.setPlanned(last6Days);
			Association.Classifies.link(categoria2, task24);
			Task task25 = new Task(user, "tarea25");
			task25.setPlanned(last6Days);
			Association.Classifies.link(categoria2, task25);
			Task task26 = new Task(user, "tarea26");
			task26.setPlanned(last6Days);
			Association.Classifies.link(categoria2, task26);
			Task task27 = new Task(user, "tarea27");
			task27.setPlanned(last6Days);
			Association.Classifies.link(categoria3, task27);
			Task task28 = new Task(user, "tarea28");
			task28.setPlanned(last6Days);
			Association.Classifies.link(categoria3, task28);
			Task task29 = new Task(user, "tarea29");
			task29.setPlanned(last6Days);
			Association.Classifies.link(categoria3, task29);
			Task task30 = new Task(user, "tarea30");
			task30.setPlanned(last6Days);
			Association.Classifies.link(categoria3, task30);
		}
		
		Jpa.getManager().persist(user1);
		Jpa.getManager().persist(user2);
		Jpa.getManager().persist(user3);

		return null;
	}
}
