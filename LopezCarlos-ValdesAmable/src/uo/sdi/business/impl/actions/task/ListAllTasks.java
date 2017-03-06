package uo.sdi.business.impl.actions.task;

import java.util.ArrayList;
import java.util.List;

import uo.sdi.business.util.BusinessException;
import uo.sdi.business.util.Command;
import uo.sdi.dto.TaskDTO;
import uo.sdi.dto.util.Cloner;
import uo.sdi.model.Task;
import uo.sdi.model.User;
import uo.sdi.persistence.UserFinder;

public class ListAllTasks implements Command{

	@Override
	public Object execute() throws BusinessException {
		List<User> lista = UserFinder.findAll();
		ArrayList<TaskDTO> listDTO = new ArrayList<TaskDTO>();
		for(User user : lista){
			for(Task task : user.getTasks()){
				listDTO.add(Cloner.clone(task));
			}
		}
		return listDTO;
	}

}
