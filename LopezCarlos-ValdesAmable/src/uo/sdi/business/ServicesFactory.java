package uo.sdi.business;

public interface ServicesFactory {

	UserService createUserService();
	TaskService createTaskService();
	CategoryService createCategoryService();
	
}
