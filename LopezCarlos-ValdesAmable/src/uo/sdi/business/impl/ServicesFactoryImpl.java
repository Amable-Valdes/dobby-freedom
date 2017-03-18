package uo.sdi.business.impl;

import uo.sdi.business.CategoryService;
import uo.sdi.business.ServicesFactory;
import uo.sdi.business.TaskService;
import uo.sdi.business.UserService;

/**
 * Implementación de la Factoria de Servicios ServicesFactory que devuelve 
 * los distintos servicio del sistema.
 * 
 * @author Amable y Carlos
 */
public class ServicesFactoryImpl implements ServicesFactory {

	/* (non-Javadoc)
	 * @see uo.sdi.business.ServicesFactory#createUserService()
	 */
	@Override
	public UserService createUserService() {
		return new UserServiceImpl();
	}

	/* (non-Javadoc)
	 * @see uo.sdi.business.ServicesFactory#createTaskService()
	 */
	@Override
	public TaskService createTaskService() {
		return new TaskServiceImpl();
	}

	/* (non-Javadoc)
	 * @see uo.sdi.business.ServicesFactory#createCategoryService()
	 */
	@Override
	public CategoryService createCategoryService() {
		return new CategoryServiceImpl();
	}

}
