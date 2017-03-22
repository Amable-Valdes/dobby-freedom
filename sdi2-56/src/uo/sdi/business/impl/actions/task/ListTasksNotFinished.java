package uo.sdi.business.impl.actions.task;

import java.util.ArrayList;
import java.util.List;

import uo.sdi.business.util.Asserts;
import uo.sdi.business.util.BusinessException;
import uo.sdi.business.util.Command;
import uo.sdi.dto.TaskDTO;
import uo.sdi.dto.util.Cloner;
import uo.sdi.model.Task;
import uo.sdi.model.User;
import uo.sdi.persistence.TaskFinder;
import uo.sdi.persistence.UserFinder;

public class ListTasksNotFinished implements Command {

	private String login;
	
	public ListTasksNotFinished(String login) {
		this.login = login;
	}

	@Override
	public Object execute() throws BusinessException {
		User user = UserFinder.findByLogin(login);
		Asserts.assertUserExist(user);
		List<Task> listTasks = TaskFinder.findByLogin(login);
		ArrayList<TaskDTO> listDTO = new ArrayList<TaskDTO>();
		for (Task task : listTasks) {
			listDTO.add(Cloner.clone(task));
		}
		return listDTO;
	}

}
