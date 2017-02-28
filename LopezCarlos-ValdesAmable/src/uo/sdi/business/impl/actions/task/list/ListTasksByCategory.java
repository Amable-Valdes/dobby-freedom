package uo.sdi.business.impl.actions.task.list;

import java.util.ArrayList;
import java.util.List;

import uo.sdi.business.util.BusinessException;
import uo.sdi.business.util.Command;
import uo.sdi.dto.TaskDTO;
import uo.sdi.model.Category;
import uo.sdi.model.Task;
import uo.sdi.model.User;
import uo.sdi.persistence.CategoryFinder;
import uo.sdi.persistence.TaskFinder;
import uo.sdi.persistence.UserFinder;

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
		assertUserExist(user);
		Category category = CategoryFinder.findByUserAndName(user.getId(),
				categoryName);
		assertCategoryExist(category);
		List<Task> listTasks = TaskFinder.findByUserAndCategoryActive(
				user.getId(),category.getId());
		ArrayList<TaskDTO> listDTO = new ArrayList<TaskDTO>();
		TaskDTO taskDTO;
		for (Task task : listTasks) {
			taskDTO = new TaskDTO();
			taskDTO.setId(task.getId());
			taskDTO.setCreated(task.getCreated());
			taskDTO.setTitle(task.getTitle());
			taskDTO.setPlanned(task.getPlanned());
			taskDTO.setFinished(task.getFinished());
			taskDTO.setComments(task.getComments());
			taskDTO.setCategoryId(task.getCategory().getId());
			taskDTO.setUserId(task.getUser().getId());
			listDTO.add(taskDTO);
		}
		return listDTO;
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
