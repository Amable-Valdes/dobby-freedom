package uo.sdi.business.impl.actions.task;

import java.util.ArrayList;
import java.util.List;

import uo.sdi.business.util.Asserts;
import uo.sdi.business.util.BusinessException;
import uo.sdi.business.util.Command;
import uo.sdi.dto.TaskDTO;
import uo.sdi.dto.util.Cloner;
import uo.sdi.model.Category;
import uo.sdi.model.Task;
import uo.sdi.model.User;
import uo.sdi.persistence.CategoryFinder;
import uo.sdi.persistence.TaskFinder;
import uo.sdi.persistence.UserFinder;

/**
 * Este Action nos permite listar las tareas de un usuario por la categoría.
 * 
 * @author Amable y Carlos
 *
 */
public class ListTasksByCategory implements Command {

	private String login;
	private String categoryName;

	public ListTasksByCategory(String login, String categoryName) {
		this.login = login;
		this.categoryName = categoryName;
	}

	@Override
	public Object execute() throws BusinessException {
		User user = UserFinder.findByLogin(login);
		Asserts.assertUserExist(user);
		Category category = CategoryFinder.findByUserAndName(user.getId(),
				categoryName);
		Asserts.assertCategoryExist(category);
		List<Task> listTasks = TaskFinder.findByUser_And_Category(
				user.getId(),category.getId());
		ArrayList<TaskDTO> listDTO = new ArrayList<TaskDTO>();
		for (Task task : listTasks) {
			listDTO.add(Cloner.clone(task));
		}
		return listDTO;
	}
}
