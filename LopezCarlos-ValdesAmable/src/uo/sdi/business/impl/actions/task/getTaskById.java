package uo.sdi.business.impl.actions.task;

import java.util.List;

import uo.sdi.business.util.BusinessException;
import uo.sdi.business.util.Command;
import uo.sdi.dto.TaskDTO;
import uo.sdi.model.Task;
import uo.sdi.persistence.TaskFinder;

public class getTaskById implements Command {

	private Long id;
	
	public getTaskById(Long id){
		this.id = id;
	}
	
	
	@Override
	public Object execute() throws BusinessException {
		TaskDTO taskDTO = null;
		List<Task> listTasks = TaskFinder.findAll();
		for(Task task : listTasks){
			if(task.getId().equals(id)){
				taskDTO = new TaskDTO();
				taskDTO.setId(task.getId());
				taskDTO.setCreated(task.getCreated());
				taskDTO.setTitle(task.getTitle());
				taskDTO.setPlanned(task.getPlanned());
				taskDTO.setFinished(task.getFinished());
				taskDTO.setComments(task.getComments());
				if(task.getCategory() == null){
					taskDTO.setCategoryId(null);
				}
				else{
					taskDTO.setCategoryId(task.getCategory().getId());
				}
				taskDTO.setUserId(task.getUser().getId());
			}
		}
		return taskDTO;
	}

}
