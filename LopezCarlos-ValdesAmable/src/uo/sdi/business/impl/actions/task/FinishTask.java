package uo.sdi.business.impl.actions.task;

import java.util.Date;

import uo.sdi.business.util.Asserts;
import uo.sdi.business.util.BusinessException;
import uo.sdi.business.util.Command;
import uo.sdi.dto.TaskDTO;
import uo.sdi.model.Task;
import uo.sdi.model.User;
import uo.sdi.persistence.TaskFinder;
import uo.sdi.persistence.UserFinder;

public class FinishTask implements Command {
	
	private String login;
	private TaskDTO taskDTO;
	
	public FinishTask(String login, TaskDTO taskDTO) {
		this.login = login;
		this.taskDTO = taskDTO;
	}

	@Override
	public Object execute() throws BusinessException {
		//Buscamos el usuario por su login.
		User user = UserFinder.findByLogin(login);
		//Comprobamos que existe.
		Asserts.assertUserExist(user);
		//Buscamos la tarea.
		Task task = TaskFinder.findByUser_And_CreatedDate(taskDTO.getUserId(), 
				taskDTO.getCreated());
		//Comprobamos que la tarea existe.
		Asserts.assertTaskExist(task);
		//Comprobamos que la tarea no está ya finalizada
		Asserts.assertTaskIsNotFinished(task);
		//Comprobamos que el usuario es el mismo que el de la tarea
		Asserts.assertSameUsers(user, task.getUser());
		//Finalizamos la tarea
		task.setFinished(new Date());
		return null;
	}
}
