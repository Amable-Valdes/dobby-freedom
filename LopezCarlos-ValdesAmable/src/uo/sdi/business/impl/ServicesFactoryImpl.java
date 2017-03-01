package uo.sdi.business.impl;

import uo.sdi.business.ServicesFactory;
import uo.sdi.business.TaskService;
import uo.sdi.business.UserService;

public class ServicesFactoryImpl implements ServicesFactory {

	@Override
	public UserService createUserService() {
		return new UserServiceImpl();
	}

	@Override
	public TaskService createTaskService() {
		return new TaskServiceImpl();
	}

//	@Override
//	public CategoryService createCategoryService() {
//		return new CategoryServiceImpl();
//	}

}
