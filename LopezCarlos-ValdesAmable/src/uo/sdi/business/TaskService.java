package uo.sdi.business;

import java.util.Date;
import java.util.List;

import uo.sdi.business.util.BusinessException;
import uo.sdi.dto.CategoryDTO;
import uo.sdi.dto.TaskDTO;

public interface TaskService {

	// CRUD
	void addTask(String login, CategoryDTO categoryDTO, TaskDTO taskDTO)
			throws BusinessException;

	void updateTask(TaskDTO task, String login) throws BusinessException;

	void finishTask(String login, String categoryName, Date created,
			String taskName) throws BusinessException;

	List<TaskDTO> listTasksInbox(String login, boolean finished) 
			throws BusinessException;

	List<TaskDTO> listTasksToday(String login) throws BusinessException;

	List<TaskDTO> listTasksWeek(String login) throws BusinessException;

	List<TaskDTO> listTasksByCategory(String login, String categoryName)
			throws BusinessException;

	List<TaskDTO> listUserTasks(String login) throws BusinessException;

	TaskDTO getTaskById(Long id) throws BusinessException;
}
