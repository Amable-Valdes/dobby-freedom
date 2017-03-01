package uo.sdi.business;

import java.util.List;

import uo.sdi.business.util.BusinessException;
import uo.sdi.dto.UserDTO;

public interface UserService {

	public void addUser(UserDTO user) throws BusinessException;
	public List<UserDTO> listUsers(String login) throws BusinessException;
	public void removeUser(String login) throws BusinessException;
	public void blockUser(UserDTO user) throws BusinessException;
	public void enableUser(UserDTO user) throws BusinessException;
	public UserDTO loginUser(String login, String password) 
			throws BusinessException;
	public void resetBBDD() throws BusinessException;
	public List<UserDTO> listAll() throws BusinessException;
}
