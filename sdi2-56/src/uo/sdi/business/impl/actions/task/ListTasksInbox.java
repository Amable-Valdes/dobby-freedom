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

/**
 * Este Action nos permite listar las tareas de un usuario por Inbox.
 * 
 * @author Amable y Carlos
 *
 */
public class ListTasksInbox implements Command{

	private String login;
	private boolean finished;

	public ListTasksInbox(String login, boolean finished) {
		this.login = login;
		this.finished = finished;
	}

	@Override
	public Object execute() throws BusinessException {
		User user = UserFinder.findByLogin(login);
		Asserts.assertUserExist(user);
		List<Task> listTasks = TaskFinder.findInbox(user.getId());
		ArrayList<TaskDTO> listDTO = new ArrayList<TaskDTO>();
		for (Task task : listTasks) {
			listDTO.add(Cloner.clone(task));
		}
		if(finished){
			listTasks = TaskFinder.findFinished(user.getId());
			for (Task task : listTasks) {
				listDTO.add(Cloner.clone(task));
			}
		}
		return listDTO;
	}
}
