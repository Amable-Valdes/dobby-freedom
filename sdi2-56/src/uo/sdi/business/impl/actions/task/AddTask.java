package uo.sdi.business.impl.actions.task;

import java.sql.Timestamp;

import uo.sdi.business.util.Asserts;
import uo.sdi.business.util.BusinessException;
import uo.sdi.business.util.Command;
import uo.sdi.dto.CategoryDTO;
import uo.sdi.dto.TaskDTO;
import uo.sdi.model.Category;
import uo.sdi.model.Task;
import uo.sdi.model.User;
import uo.sdi.persistence.CategoryFinder;
import uo.sdi.persistence.UserFinder;
import uo.sdi.persistence.util.Jpa;

/**
 * Este Action nos permite añadir una nueva tarea en el sistema.
 * 
 * @author Amable y Carlos
 *
 */
public class AddTask implements Command {

	private String login;
	private CategoryDTO categoryDTO;
	private TaskDTO taskDTO;

	public AddTask(String login, CategoryDTO categoryDTO, TaskDTO taskDTO) {
		this.login = login;
		this.categoryDTO = categoryDTO;
		this.taskDTO = taskDTO;
	}

	@Override
	public Object execute() throws BusinessException {
		//Buscamos al usuario por su login
		User user = UserFinder.findByLogin(login);
		//Comprobamos que el usuario existe
		Asserts.assertUserExist(user);
		//Por defecto Null -> Inbox
		Category category = null;
		//Si tiene nombre
		if(categoryDTO != null){
			//Buscamos si existe la categoria
			category = CategoryFinder.findByUserAndName(user.getId(),
				categoryDTO.getName());
			//Comprobamos si, existiendo nombre, existe dicha categoria
			Asserts.assertCategoryExist(category);
		}
		//Creamos la tarea
		Task task;
		if(category != null)
			task = new Task(user, category, taskDTO.getTitle());
		else
			task = new Task(user, taskDTO.getTitle());
		task.setCreated(new Timestamp(System.currentTimeMillis()));
		task.setPlanned(taskDTO.getPlanned());
		task.setFinished(null);
		task.setComments(taskDTO.getComments());
		Jpa.getManager().persist(task);
		return null;
	}
}
