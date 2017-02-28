package uo.sdi.business.impl;

import java.util.Date;
import java.util.List;

import uo.sdi.business.TaskService;
import uo.sdi.business.impl.actions.task.FinishTask;
import uo.sdi.business.impl.actions.task.list.ListAllTasks;
import uo.sdi.business.impl.actions.task.list.ListTasksByCategory;
import uo.sdi.business.util.BusinessException;
import uo.sdi.business.util.CommandExecutor;
import uo.sdi.dto.TaskDTO;

public class TaskServiceImpl implements TaskService {

	CommandExecutor executor = new CommandExecutor();

	@Override
	public void addTask() {
		// TODO Auto-generated method stub

	}

	@Override
	public TaskDTO findTask() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updateTask() {
		// TODO Auto-generated method stub

	}

	@Override
	public void removeTask() {
		// TODO Auto-generated method stub

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<TaskDTO> listTasks(String login, String categoryName)
			throws BusinessException {
		return (List<TaskDTO>) executor.execute(new ListTasksByCategory(login,
				categoryName));
	}

	@Override
	public void finishTask(String login, String categoryName, Date created, 
			String taskName) throws BusinessException {
		executor.execute(new FinishTask(login, categoryName, created, 
				taskName));
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<TaskDTO> findAllTasks() throws BusinessException {
		return (List<TaskDTO>) executor.execute(new ListAllTasks());
	}

}
