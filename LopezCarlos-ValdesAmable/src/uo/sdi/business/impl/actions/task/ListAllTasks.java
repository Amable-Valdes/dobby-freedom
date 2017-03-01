package uo.sdi.business.impl.actions.task;

import java.util.ArrayList;
import java.util.List;

import uo.sdi.business.util.BusinessException;
import uo.sdi.business.util.Command;
import uo.sdi.dto.TaskDTO;
import uo.sdi.model.Task;
import uo.sdi.model.User;
import uo.sdi.persistence.UserFinder;

public class ListAllTasks implements Command{

	@Override
	public Object execute() throws BusinessException {
		List<User> lista = UserFinder.findAll();
		ArrayList<TaskDTO> listDTO = new ArrayList<TaskDTO>();
		TaskDTO taskDTO;
		for(User user : lista){
			for(Task task : user.getTasks()){
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
		}
		return listDTO;
	}

}
