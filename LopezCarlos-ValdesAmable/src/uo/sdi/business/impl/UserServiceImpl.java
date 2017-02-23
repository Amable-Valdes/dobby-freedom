package uo.sdi.business.impl;

import uo.sdi.business.impl.actions.user.AddUser;
import uo.sdi.business.util.BusinessException;
import uo.sdi.business.util.CommandExecutor;
import uo.sdi.business.UserService;
import uo.sdi.dto.UserDTO;

public class UserServiceImpl implements UserService {

	CommandExecutor executor = new CommandExecutor();
	
	@Override
	public void addUser(UserDTO user) throws BusinessException {
		executor.execute(new AddUser( user ));
	}

	@Override
	public UserDTO findUser(UserDTO user) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updateUser(UserDTO user) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeUser(UserDTO user) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void blockUser(UserDTO user) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void enableUser(UserDTO user) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public UserDTO loginUser(String login, String password) {
		// TODO Auto-generated method stub
		return null;
	}

}
