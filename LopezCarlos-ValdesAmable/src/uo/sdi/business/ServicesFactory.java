package uo.sdi.business;

/**
 * Factoria de Servicios que devuelve los distintos servicio del sistema.
 * 
 * @author Amable y Carlos
 */
public interface ServicesFactory {

	/**
	 * Devuelve los distintos servicios que administran y realizan operaciones
	 * con los usuarios del sistema.
	 * 
	 * @return	Un Objeto de tipo UserService.
	 */
	UserService createUserService();

	/**
	 * Devuelve los distintos servicios que administran y realizan operaciones
	 * con las tareas del sistema.
	 * 
	 * @return	Un Objeto de tipo TaskService.
	 */
	TaskService createTaskService();

	/**
	 * Devuelve los distintos servicios que administran y realizan operaciones
	 * con las categorias del sistema.
	 * 
	 * @return	Un Objeto de tipo CategoryService.
	 */
	CategoryService createCategoryService();
	
}
