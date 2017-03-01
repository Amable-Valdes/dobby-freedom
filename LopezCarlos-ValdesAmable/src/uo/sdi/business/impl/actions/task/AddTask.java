package uo.sdi.business.impl.actions.task;

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

public class AddTask implements Command {

	private String login;
	private CategoryDTO categoryDTO;
	private TaskDTO taskDTO;

	public AddTask(String login, CategoryDTO categoryDTO, TaskDTO taskDTO) {
		this.login = login;
		this.categoryDTO = categoryDTO;
	}

	@Override
	public Object execute() throws BusinessException {
		User user = UserFinder.findByLogin(login);
		assertUserExist(user);
		//Por defecto Null -> Inbox
		Category category = null;
		//Si tiene nombre
		if(categoryDTO.getName() != null){
			//Buscamos si existe la categoria
			category = CategoryFinder.findByUserAndName(user.getId(),
				categoryDTO.getName());
			//Comprobamos si, existiendo nombre, existe dicha categoria
			assertCategoryIsValid(category);
		}
		//Creamos la tarea
		Task task;
		if(category != null)
			task = new Task(user, category, taskDTO.getTitle());
		else
			task = new Task(user, taskDTO.getTitle());
		task.setPlanned(taskDTO.getPlanned());
		task.setFinished(null);
		task.setComments(taskDTO.getComments());
		Jpa.getManager().persist(task);
		return null;
	}

	private void assertUserExist(User user) throws BusinessException {
		if (user != null)
			return;
		throw new BusinessException("El usuario no existe");
	}

	private void assertCategoryIsValid(Category category)
			throws BusinessException {
		if (category == null) {
			throw new BusinessException("El nombre de la categoría "
					+ "introducida no es valido porque no existe");
		}
	}

}
