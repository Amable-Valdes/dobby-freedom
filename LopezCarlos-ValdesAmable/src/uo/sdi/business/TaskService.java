package uo.sdi.business;

import uo.sdi.business.util.BusinessException;
import uo.sdi.dto.TaskDTO;

public interface TaskService {

	//CRUD
	public void addTask() throws BusinessException;
	public TaskDTO findTask() throws BusinessException;
	public void updateTask() throws BusinessException;
	public void removeTask() throws BusinessException;
}
