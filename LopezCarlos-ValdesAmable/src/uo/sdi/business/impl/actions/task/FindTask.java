package uo.sdi.business.impl.actions.task;

import java.util.Date;

import uo.sdi.business.util.Asserts;
import uo.sdi.business.util.BusinessException;
import uo.sdi.business.util.Command;
import uo.sdi.dto.util.Cloner;
import uo.sdi.model.Task;
import uo.sdi.model.User;
import uo.sdi.persistence.TaskFinder;
import uo.sdi.persistence.UserFinder;

public class FindTask implements Command {
	
	private String login;
	private Date created;

	public FindTask(String login, Date created) {
		this.login = login;
		this.created = created;
	}

	@Override
	public Object execute() throws BusinessException {
		//Buscamos el usuario y comprobamos que existe
		User user = UserFinder.findByLogin(login);
		Asserts.assertUserExist(user);
		//Buscamos la tarea y comprobamos que existe
		Task task = TaskFinder.findByUser_And_CreatedDate(user.getId(), created);
		Asserts.assertTaskExist(task);
		//Devolvemos la tarea
		return Cloner.clone(task);
	}

}
