package uo.sdi.business.impl;

import java.util.Date;
import java.util.List;

import uo.sdi.business.TaskService;
import uo.sdi.business.impl.actions.task.AddTask;
import uo.sdi.business.impl.actions.task.FinishTask;
import uo.sdi.business.impl.actions.task.ListTasksByCategory;
import uo.sdi.business.impl.actions.task.ListTasksByLogin;
import uo.sdi.business.impl.actions.task.ListTasksInbox;
import uo.sdi.business.impl.actions.task.ListTasksToday;
import uo.sdi.business.impl.actions.task.ListTasksWeek;
import uo.sdi.business.impl.actions.task.UpdateTask;
import uo.sdi.business.util.BusinessException;
import uo.sdi.business.util.CommandExecutor;
import uo.sdi.dto.CategoryDTO;
import uo.sdi.dto.TaskDTO;

public class TaskServiceImpl implements TaskService {

	private CommandExecutor executor = new CommandExecutor();

	@Override
	public void addTask(String login,CategoryDTO categoryDTO, TaskDTO taskDTO) 
			throws BusinessException {
		executor.execute(new AddTask(login,categoryDTO,taskDTO));
	}

	@Override
	public void updateTask(TaskDTO taskDTO_Old, TaskDTO taskDTO_Update) 
			throws BusinessException{
		executor.execute(new UpdateTask(taskDTO_Old, taskDTO_Update));
	}

	@Override
	public void finishTask(String login, String categoryName, Date created, 
			String taskName) throws BusinessException {
		executor.execute(new FinishTask(login, categoryName, created, 
				taskName));
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<TaskDTO> listUserTasks(String login) throws BusinessException {
		return (List<TaskDTO>) executor.execute(new ListTasksByLogin(login));
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<TaskDTO> listTasksInbox(String login, boolean finished) 
			throws BusinessException {
		return (List<TaskDTO>) executor.execute(new ListTasksInbox(login,
				finished));
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<TaskDTO> listTasksToday(String login) throws BusinessException {
		return (List<TaskDTO>) executor.execute(new ListTasksToday(login));
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<TaskDTO> listTasksWeek(String login) throws BusinessException {
		return (List<TaskDTO>) executor.execute(new ListTasksWeek(login));
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<TaskDTO> listTasksByCategory(String login, String categoryName)
			throws BusinessException {
		return (List<TaskDTO>) executor.execute(new ListTasksByCategory(login,
				categoryName));
	}

}
