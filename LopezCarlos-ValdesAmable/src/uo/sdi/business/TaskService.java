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

	public void updateTask(TaskDTO taskDTO_Old, TaskDTO taskDTO_Update) 
			throws BusinessException;

	public void finishTask(String login, String categoryName, Date created,
			String taskName) throws BusinessException;

	List<TaskDTO> listTasksInbox(String login) throws BusinessException;

	List<TaskDTO> listTasksToday(String login) throws BusinessException;

	List<TaskDTO> listTasksWeek(String login) throws BusinessException;

	List<TaskDTO> listTasksByCategory(String login, String categoryName)
			throws BusinessException;
}
