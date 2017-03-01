package uo.sdi.business.impl;

import java.util.Date;
import java.util.List;

import uo.sdi.business.TaskService;
import uo.sdi.business.impl.actions.task.AddTask;
import uo.sdi.business.impl.actions.task.FinishTask;
import uo.sdi.business.impl.actions.task.ListAllTasks;
import uo.sdi.business.impl.actions.task.ListTasksByCategory;
import uo.sdi.business.impl.actions.task.ListTasksByLogin;
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
	public TaskDTO findTask() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updateTask(TaskDTO taskDTO_Old, TaskDTO taskDTO_Update) 
			throws BusinessException{
		executor.execute(new UpdateTask(taskDTO_Old, taskDTO_Update));
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

	@SuppressWarnings("unchecked")
	@Override
	public List<TaskDTO> listTasks(String login) throws BusinessException {
		return (List<TaskDTO>) executor.execute(new ListTasksByLogin(login));
	}

}
