package uo.sdi.business.impl;

import java.util.List;

import uo.sdi.business.TaskService;
import uo.sdi.business.impl.actions.task.AddTask;
import uo.sdi.business.impl.actions.task.FinishTask;
import uo.sdi.business.impl.actions.task.ListTasksByCategory;
import uo.sdi.business.impl.actions.task.ListTasksInbox;
import uo.sdi.business.impl.actions.task.ListTasksToday;
import uo.sdi.business.impl.actions.task.ListTasksWeek;
import uo.sdi.business.impl.actions.task.UpdateTask;
import uo.sdi.business.util.BusinessException;
import uo.sdi.business.util.CommandExecutor;
import uo.sdi.dto.CategoryDTO;
import uo.sdi.dto.TaskDTO;

/**
 * Implementación de la interfaz TaskService que da distintos servicios 
 * (referidos al manejo y administración de las tareas) a los distintos 
 * usuarios del sistema.
 * 
 * @author Amable y Carlos
 *
 */
public class TaskServiceImpl implements TaskService {

	private CommandExecutor executor = new CommandExecutor();

	/* (non-Javadoc)
	 * @see uo.sdi.business.TaskService#addTask(java.lang.String, uo.sdi.dto.CategoryDTO, uo.sdi.dto.TaskDTO)
	 */
	@Override
	public void addTask(String login,CategoryDTO categoryDTO, TaskDTO taskDTO) 
			throws BusinessException {
		executor.execute(new AddTask(login,categoryDTO,taskDTO));
	}

	/* (non-Javadoc)
	 * @see uo.sdi.business.TaskService#updateTask(uo.sdi.dto.TaskDTO, uo.sdi.dto.TaskDTO)
	 */
	@Override
	public void updateTask(TaskDTO taskDTO_Old, TaskDTO taskDTO_Update) 
			throws BusinessException{
		executor.execute(new UpdateTask(taskDTO_Old, taskDTO_Update));
	}

	@Override
	public void finishTask(String login, TaskDTO taskDTO) 
			throws BusinessException {
		executor.execute(new FinishTask(login, taskDTO));
	}
	
	/* (non-Javadoc)
	 * @see uo.sdi.business.TaskService#listTasksInbox(java.lang.String, boolean)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<TaskDTO> listTasksInbox(String login, boolean finished) 
			throws BusinessException {
		return (List<TaskDTO>) executor.execute(new ListTasksInbox(login,
				finished));
	}
	
	/* (non-Javadoc)
	 * @see uo.sdi.business.TaskService#listTasksToday(java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<TaskDTO> listTasksToday(String login) throws BusinessException {
		return (List<TaskDTO>) executor.execute(new ListTasksToday(login));
	}
	
	/* (non-Javadoc)
	 * @see uo.sdi.business.TaskService#listTasksWeek(java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<TaskDTO> listTasksWeek(String login) throws BusinessException {
		return (List<TaskDTO>) executor.execute(new ListTasksWeek(login));
	}
	
	/* (non-Javadoc)
	 * @see uo.sdi.business.TaskService#listTasksByCategory(java.lang.String, java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<TaskDTO> listTasksByCategory(String login, String categoryName)
			throws BusinessException {
		return (List<TaskDTO>) executor.execute(new ListTasksByCategory(login,
				categoryName));
	}
	
//	@SuppressWarnings("unchecked")
//	@Override
//	public List<TaskDTO> listUserTasks(String login) throws BusinessException {
//		return (List<TaskDTO>) executor.execute(new ListTasksByLogin(login));
//	}

}
