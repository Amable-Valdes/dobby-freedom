package uo.sdi.business;

import java.util.Date;
import java.util.List;

import uo.sdi.business.util.BusinessException;
import uo.sdi.dto.CategoryDTO;
import uo.sdi.dto.TaskDTO;

/**
 * Esta interfaz proporciona servicios referidos a la administración de 
 * las tareas del sistema.
 * 
 * @author Amable y Carlos
 *
 */
public interface TaskService {

	/**
	 * Añade una nueva tarea al sistema.
	 * 
	 * @param login	El login del usuario que quiere añadir la nueva tarea.
	 * @param categoryDTO	La categoria a la que añadiremos la tarea. Puede 
	 * ser null ya que una tarea puede no tener categoría.
	 * @param taskDTO	La información de la nueva tarea que se añadirá al 
	 * sistema.
	 * @throws BusinessException	Si se cumple alguna irregularidad en la 
	 * lógica de negocio el sistema lanzará a capas superiores una excepción 
	 * de tipo BusinessException (Excepción de lógica de negocio).
	 */
	void addTask(String login, CategoryDTO categoryDTO, TaskDTO taskDTO)
			throws BusinessException;

	/**
	 * Edita y actualiza una tarea ya existente con nueva información.
	 * 
	 * @param taskDTO	La nueva información de la tarea.
	 * @throws BusinessException	Si se cumple alguna irregularidad en la 
	 * lógica de negocio el sistema lanzará a capas superiores una excepción 
	 * de tipo BusinessException (Excepción de lógica de negocio).
	 */
	void updateTask(TaskDTO taskDTO) throws BusinessException;

	/**
	 * Marca una tarea como finalizada.
	 * 
	 * @param login	El login del usuario que va a finalizar la tarea
	 * @param categoryName	La categoria de la tarea que se va a finalizar.
	 * @param created	La fecha de creación
	 * @param taskName
	 * @throws BusinessException
	 */
	void finishTask(String login, TaskDTO taskDTO) throws BusinessException;

	/**
	 * Hay distintas listas en el sistema, esta es la lista Inbox que muestra 
	 * las tareas que no tienen categoría, las retrasadas y las finalizadas 
	 * si el usuario te piede que se las muestres.
	 * 
	 * @param login	El login del usuario que pide que les muestren las tareas.
	 * @param finished	Indica si el usuario ha pedido que le muestren las 
	 * tareas finalizadas.
	 * @return	Una lista de TaskDTO con los datos de las tareas.
	 * @throws BusinessException	Si se cumple alguna irregularidad en la 
	 * lógica de negocio el sistema lanzará a capas superiores una excepción 
	 * de tipo BusinessException (Excepción de lógica de negocio).
	 */
	List<TaskDTO> listTasksInbox(String login, boolean finished) 
			throws BusinessException;

	/**
	 * Hay distintas listas en el sistema, esta es la lista Today que muestra 
	 * las tareas que son para hoy y aquellas retrasadas.
	 * 
	 * @param login	El login del usuario que pide que les muestren las tareas.
	 * @return	Una lista de TaskDTO con los datos de las tareas.
	 * @throws BusinessException	Si se cumple alguna irregularidad en la 
	 * lógica de negocio el sistema lanzará a capas superiores una excepción 
	 * de tipo BusinessException (Excepción de lógica de negocio).
	 */
	List<TaskDTO> listTasksToday(String login) throws BusinessException;

	/**
	 * Hay distintas listas en el sistema, esta es la lista Week que muestra 
	 * las tareas que son para esta semana y aquellas retrasadas.
	 * 
	 * @param login	El login del usuario que pide que les muestren las tareas.
	 * @return	Una lista de TaskDTO con los datos de las tareas.
	 * @throws BusinessException	Si se cumple alguna irregularidad en la 
	 * lógica de negocio el sistema lanzará a capas superiores una excepción 
	 * de tipo BusinessException (Excepción de lógica de negocio).
	 */
	List<TaskDTO> listTasksWeek(String login) throws BusinessException;

	/**
	 * Hay distintas listas en el sistema, esta es la lista ByCategory 
	 * que muestra las tareas pertenecientes a una categoría.
	 * 
	 * @param login	El login del usuario que pide que les muestren las tareas.
	 * @param categoryName	El nombre de la categoria.
	 * @return	Una lista de TaskDTO con los datos de las tareas.
	 * @throws BusinessException	Si se cumple alguna irregularidad en la 
	 * lógica de negocio el sistema lanzará a capas superiores una excepción 
	 * de tipo BusinessException (Excepción de lógica de negocio).
	 */
	List<TaskDTO> listTasksByCategory(String login, String categoryName)
			throws BusinessException;
	
	/**
	 * Hay distintas listas en el sistema, esta es la lista del inicio del 
	 * sistema que muestra las tareas creadas y no terminadas
	 * 
	 * @param login	El login del usuario que pide que les muestren las tareas.
	 * @return	Una lista de TaskDTO con los datos de las tareas.
	 * @throws BusinessException	Si se cumple alguna irregularidad en la 
	 * lógica de negocio el sistema lanzará a capas superiores una excepción 
	 * de tipo BusinessException (Excepción de lógica de negocio).
	 */
	List<TaskDTO> listTasksCreatedAndNotFinished(String login)
			throws BusinessException;

	/**
	 * Encuentra una tarea por su fecha de creación y el nombre de su usuario.
	 * 
	 * @param login	El login del usuario que tiene la tarea.	
	 * @param created	La fecha de creación de la tarea.
	 * @return	Un TaskDTO con toda la información de la tarea.
	 * @throws BusinessException	Si se cumple alguna irregularidad en la 
	 * lógica de negocio el sistema lanzará a capas superiores una excepción 
	 * de tipo BusinessException (Excepción de lógica de negocio).
	 */
	TaskDTO findTask(String login, Date created) throws BusinessException;

//	/**
//	 * Hay distintas listas en el sistema, esta es la lista ByLogin 
//	 * que muestra las tareas pertenecientes a un usuario.
//	 * 
//	 * @param login	El login del usuario que pide que les muestren las tareas.
//	 * @return	Una lista de TaskDTO con los datos de las tareas.
//	 * @throws BusinessException	Si se cumple alguna irregularidad en la 
//	 * lógica de negocio el sistema lanzará a capas superiores una excepción 
//	 * de tipo BusinessException (Excepción de lógica de negocio).
//	 */
//	List<TaskDTO> listUsersTasks(String login) throws BusinessException;
}
