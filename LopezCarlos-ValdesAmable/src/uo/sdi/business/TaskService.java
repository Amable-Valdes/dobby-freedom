package uo.sdi.business;

import java.util.Date;
import java.util.List;

import uo.sdi.business.util.BusinessException;
import uo.sdi.dto.CategoryDTO;
import uo.sdi.dto.TaskDTO;

public interface TaskService {

	// CRUD
	public void addTask(String login, CategoryDTO categoryDTO, TaskDTO taskDTO)
			throws BusinessException;

	public TaskDTO findTask() throws BusinessException;

	public void updateTask(TaskDTO taskDTO_Old, TaskDTO taskDTO_Update) 
			throws BusinessException;

	public void finishTask(String login, String categoryName, Date created,
			String taskName) throws BusinessException;

	public List<TaskDTO> listTasks(String login, String categoryName)
			throws BusinessException;

	public List<TaskDTO> findAllTasks() throws BusinessException;

	public List<TaskDTO> listTasks(String login) throws BusinessException;
}
