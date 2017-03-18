package uo.sdi.business.impl.actions.task;

import java.util.List;

import uo.sdi.business.util.BusinessException;
import uo.sdi.business.util.Command;
import uo.sdi.dto.TaskDTO;
import uo.sdi.dto.UserDTO;
import uo.sdi.model.Association;
import uo.sdi.model.Category;
import uo.sdi.model.Task;
import uo.sdi.model.User;
import uo.sdi.persistence.CategoryFinder;
import uo.sdi.persistence.TaskFinder;
import uo.sdi.persistence.UserFinder;
import uo.sdi.persistence.util.Jpa;

public class UpdateTask implements Command {

	private TaskDTO task;
	private String login;

	public UpdateTask(TaskDTO task, String login) {
		this.task = task;
		this.login = login;
	}

	@Override
	public Object execute() throws BusinessException {
		List<User> l = UserFinder.findAll();
		boolean loEs = false;
		Task t = null;
		for(User u : l){
			if(u.getLogin().equals(login) && u.getId().equals(task.getUserId())){
				loEs = true;
				t = new Task(u);
				t.setId(task.getId());
				t.setTitle(task.getTitle());
				t.setCreated(task.getCreated());
				t.setComments(task.getComments());
				t.setPlanned(task.getPlanned());
				t.setFinished(task.getFinished());
			}
		}
		if(loEs){
			Jpa.getManager().merge(t);
		}
		else{
			throw new BusinessException("El usuario que intenta editar la tarea, "
					+ "no tiene permisos para editarla");
		}
		
		return null;
	}

}
