package uo.sdi.business;

public interface ServicesFactory {

	public UserService createUserService();
	public TaskService createTaskService();
//	public CategoryService createCategoryService();
	
}
