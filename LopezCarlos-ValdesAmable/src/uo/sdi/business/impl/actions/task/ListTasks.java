package uo.sdi.business.impl.actions.task;

import java.util.ArrayList;

import uo.sdi.business.util.BusinessException;
import uo.sdi.business.util.Command;
import uo.sdi.dto.TaskDTO;
import uo.sdi.dto.UserDTO;
import uo.sdi.model.Task;
import uo.sdi.model.User;
import uo.sdi.persistence.UserFinder;

public class ListTasks implements Command{

	String login;
	
	public ListTasks(String login){
		this.login = login;
	}
	
	@Override
	public Object execute() throws BusinessException {
		User user = UserFinder.findByLogin(login);
		assertUserExist(user);
		ArrayList<TaskDTO> list = new ArrayList<TaskDTO>();
		TaskDTO taskDTO;
		for(Task task : user.getTasks()){
			taskDTO = new TaskDTO();
			taskDTO.setId(task.getId());
			taskDTO.setCreated(task.getCreated());
			taskDTO.setTitle(task.getTitle());
			//TODO Faltan todavia sets por poner
			taskDTO.setCategoryId(task.getCategory().getId());
			taskDTO.setUserId(task.getUser().getId());
		}
		return list;
	}
	private void assertUserExist(User user) throws BusinessException {
		if (user != null)
			return;
		throw new BusinessException("El usuario no existe");
	}
}
