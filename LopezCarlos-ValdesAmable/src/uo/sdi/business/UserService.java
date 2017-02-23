package uo.sdi.business;

import uo.sdi.business.util.BusinessException;
import uo.sdi.dto.UserDTO;

public interface UserService {

	//CRUD
	public void addUser(UserDTO user) throws BusinessException;
	public UserDTO findUser(UserDTO user) throws BusinessException;
	public void updateUser(UserDTO user) throws BusinessException;
	public void removeUser(UserDTO user) throws BusinessException;
	
	//Otras funciones
	public void blockUser(UserDTO user) throws BusinessException;
	public void enableUser(UserDTO user) throws BusinessException;
	public UserDTO loginUser(String login, String password) 
			throws BusinessException;
}
