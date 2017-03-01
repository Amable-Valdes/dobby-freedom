package uo.sdi.business.impl.actions.task;

import java.util.ArrayList;
import java.util.List;

import uo.sdi.business.util.BusinessException;
import uo.sdi.business.util.Command;
import uo.sdi.dto.TaskDTO;
import uo.sdi.model.Task;
import uo.sdi.model.User;
import uo.sdi.persistence.TaskFinder;
import uo.sdi.persistence.UserFinder;

public class ListTasksByLogin implements Command {

	private String login;

	public ListTasksByLogin(String login) {
		this.login = login;
	}

	@Override
	public Object execute() throws BusinessException {
		User user = UserFinder.findByLogin(login);
		assertUserExist(user);
		List<Task> listTasks = TaskFinder.findByLogin(
				user.getLogin());
		ArrayList<TaskDTO> list = new ArrayList<TaskDTO>();
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
		}
		return list;
	}

	private void assertUserExist(User user) throws BusinessException {
		if (user != null) return;
		throw new BusinessException("El usuario no existe");
	}

}
