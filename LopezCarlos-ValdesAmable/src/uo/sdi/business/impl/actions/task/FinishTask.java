package uo.sdi.business.impl.actions.task;

import java.util.Date;
import java.util.List;

import uo.sdi.business.util.BusinessException;
import uo.sdi.business.util.Command;
import uo.sdi.model.Category;
import uo.sdi.model.Task;
import uo.sdi.model.User;
import uo.sdi.persistence.CategoryFinder;
import uo.sdi.persistence.TaskFinder;
import uo.sdi.persistence.UserFinder;

public class FinishTask implements Command {
	
	private String login;
	private String categoryName;
	private Date created;
	private String taskName;
	
	public FinishTask(String login, String categoryName, Date created,
			String taskName) {
		this.login = login;
		this.categoryName = categoryName;
		this.created = created;
		this.taskName = taskName;
	}

	@Override
	public Object execute() throws BusinessException {
		User user = UserFinder.findByLogin(login);
		assertUserExist(user);
		Category category = CategoryFinder.findByUserAndName(user.getId(),
				categoryName);
		assertCategoryExist(category);
		Task task = TaskFinder.findByUser_Category_CreatedDate_TaskName(
				user.getId(),category.getId(),created,taskName);
		//TODO Lo deje aquí, crear el finder y seguir borrando la tarea si existe
		return null;
	}
	
	private void assertCategoryExist(Category category) 
			throws BusinessException {
		if (category != null) return;
		throw new BusinessException("La categoria no existe");
	}

	private void assertUserExist(User user) throws BusinessException {
		if (user != null) return;
		throw new BusinessException("El usuario no existe");
	}

}
