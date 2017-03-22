package uo.sdi.business.impl.actions.user;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.List;
import java.util.Date;

import alb.util.date.DateUtil;
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

/**
 * Este Action nos permite borrar todos los datos de la BBDD y añadir los datos
 * que tenía por defecto.
 * 
 * @author Amable y Carlos
 *
 */
public class ResetBBDD implements Command {

	private Date timeCreation;
	
	private int counter = 1000;
	
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
			if (!user.getIsAdmin()) {
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

		timeCreation = new Timestamp(System.currentTimeMillis());
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
			Category categoria1 = new Category(user, newTime());
			categoria1.setName("Categoria1");
			
			Category categoria2 = new Category(user, newTime());
			categoria2.setName("Categoria2");

			Category categoria3 = new Category(user, newTime());
			categoria3.setName("Categoria3");
			
			// 30 tareas
			// Tareas para dentro de 6 días
			Task task10 = new Task(user, "tarea1", newTime());
			task10.setPlanned(next6Days());
			Task task9 = new Task(user, "tarea2", newTime());
			task9.setPlanned(next6Days());
			Task task8 = new Task(user, "tarea3", newTime());
			task8.setPlanned(next6Days());
			Task task7 = new Task(user, "tarea4", newTime());
			task7.setPlanned(next6Days());
			Task task6 = new Task(user, "tarea5", newTime());
			task6.setPlanned(next6Days());
			Task task5 = new Task(user, "tarea6", newTime());
			task5.setPlanned(next6Days());
			Task task4 = new Task(user, "tarea7", newTime());
			task4.setPlanned(next6Days());
			Task task3 = new Task(user, "tarea8", newTime());
			task3.setPlanned(next6Days());
			Task task2 = new Task(user, "tarea9", newTime());
			task2.setPlanned(next6Days());
			Task task1 = new Task(user, "tarea10", newTime());
			task1.setPlanned(next6Days());
			counter = 1000;
			
			// Tareas para hoy
			Task task20 = new Task(user, "tarea11", newTime());
			task20.setPlanned(todayPlanned());
			Task task19 = new Task(user, "tarea12", newTime());
			task19.setPlanned(todayPlanned());
			Task task18 = new Task(user, "tarea13", newTime());
			task18.setPlanned(todayPlanned());
			Task task17 = new Task(user, "tarea14", newTime());
			task17.setPlanned(todayPlanned());
			Task task16 = new Task(user, "tarea15", newTime());
			task16.setPlanned(todayPlanned());
			Task task15 = new Task(user, "tarea16", newTime());
			task15.setPlanned(todayPlanned());
			Task task14 = new Task(user, "tarea17", newTime());
			task14.setPlanned(todayPlanned());
			Task task13 = new Task(user, "tarea18", newTime());
			task13.setPlanned(todayPlanned());
			Task task12 = new Task(user, "tarea19", newTime());
			task12.setPlanned(todayPlanned());
			Task task11 = new Task(user, "tarea20", newTime());
			task11.setPlanned(todayPlanned());
			counter = 1000;
			
			// Tareas retrasadas (de hace 6 días)
			Task task30 = new Task(user, "tarea21", newTime());
			task30.setPlanned(last6Days());
			Association.Classifies.link(categoria3, task30);
			Task task29 = new Task(user, "tarea22", newTime());
			task29.setPlanned(last6Days());
			Association.Classifies.link(categoria3, task29);
			Task task28 = new Task(user, "tarea23", newTime());
			task28.setPlanned(last6Days());
			Association.Classifies.link(categoria3, task28);
			Task task27 = new Task(user, "tarea24", newTime());
			task27.setPlanned(last6Days());
			Association.Classifies.link(categoria3, task27);
			Task task26 = new Task(user, "tarea25", newTime());
			task26.setPlanned(last6Days());
			Association.Classifies.link(categoria2, task26);
			Task task25 = new Task(user, "tarea26", newTime());
			task25.setPlanned(last6Days());
			Association.Classifies.link(categoria2, task25);
			Task task24 = new Task(user, "tarea27", newTime());
			task24.setPlanned(last6Days());
			Association.Classifies.link(categoria2, task24);
			Task task23 = new Task(user, "tarea28", newTime());
			task23.setPlanned(last6Days());
			Association.Classifies.link(categoria1, task23);
			Task task22 = new Task(user, "tarea29", newTime());
			task22.setPlanned(last6Days());
			Association.Classifies.link(categoria1, task22);
			Task task21 = new Task(user, "tarea30", newTime());
			task21.setPlanned(last6Days());
			Association.Classifies.link(categoria1, task21);
			counter = 1000;
			
			Jpa.getManager().persist(user);
			
			Jpa.getManager().persist(categoria1);
			Jpa.getManager().persist(categoria2);
			Jpa.getManager().persist(categoria3);
			
			Jpa.getManager().persist(task1);
			Jpa.getManager().persist(task2);
			Jpa.getManager().persist(task3);
			Jpa.getManager().persist(task4);
			Jpa.getManager().persist(task5);
			Jpa.getManager().persist(task6);
			Jpa.getManager().persist(task7);
			Jpa.getManager().persist(task8);
			Jpa.getManager().persist(task9);
			Jpa.getManager().persist(task10);
			Jpa.getManager().persist(task11);
			Jpa.getManager().persist(task12);
			Jpa.getManager().persist(task13);
			Jpa.getManager().persist(task14);
			Jpa.getManager().persist(task15);
			Jpa.getManager().persist(task16);
			Jpa.getManager().persist(task17);
			Jpa.getManager().persist(task18);
			Jpa.getManager().persist(task19);
			Jpa.getManager().persist(task20);
			Jpa.getManager().persist(task21);
			Jpa.getManager().persist(task22);
			Jpa.getManager().persist(task23);
			Jpa.getManager().persist(task24);
			Jpa.getManager().persist(task25);
			Jpa.getManager().persist(task26);
			Jpa.getManager().persist(task27);
			Jpa.getManager().persist(task28);
			Jpa.getManager().persist(task29);
			Jpa.getManager().persist(task30);
		}
		
		

		return null;
	}
	
	private Date newTime(){
		Calendar c = Calendar.getInstance();
		c.setTime(timeCreation);
		c.add(Calendar.DAY_OF_MONTH, -1);
		timeCreation = c.getTime();
		return timeCreation;
	}
	
	private Date next6Days(){
		counter = counter + 1000;
		return new Date(System.currentTimeMillis() + 518400000 - counter);
	}
	
	private Date todayPlanned(){
		counter = counter + 1000;
		return new Date(DateUtil.tomorrow().getTime() - 1000 - counter);
	}
	
	private Date last6Days(){
		counter = counter + 1000;
		return new Date(System.currentTimeMillis() - 518400000 + counter);
	}
}
