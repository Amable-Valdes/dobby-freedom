package uo.sdi.business;

public interface UserService {

	//CRUD
	public void addUser(User user);
	public User findUser(User user);
	public void updateUser(User user);
	public void removeUser(User user);
	
	//Otras funciones
	public void blockUser(User user);
	public void enableUser(User user);
	public User loginUser(String login, String password);
}
