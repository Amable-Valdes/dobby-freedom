package uo.sdi.business;

import java.util.List;

import uo.sdi.business.util.BusinessException;
import uo.sdi.dto.UserDTO;

public interface UserService {

	void addUser(UserDTO user) throws BusinessException;
	List<UserDTO> listUsers() throws BusinessException;
	void removeUser(String login) throws BusinessException;
	void blockUser(UserDTO user) throws BusinessException;
	void enableUser(UserDTO user) throws BusinessException;
	UserDTO loginUser(String login, String password) 
			throws BusinessException;
	void resetBBDD() throws BusinessException;
	List<UserDTO> listAll() throws BusinessException;
	void update(UserDTO v) throws BusinessException;
}
