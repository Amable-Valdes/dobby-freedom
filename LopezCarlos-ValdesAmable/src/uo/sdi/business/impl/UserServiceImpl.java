package uo.sdi.business.impl;

import java.util.List;

import uo.sdi.business.impl.actions.user.ListAllUsers;
import uo.sdi.business.impl.actions.user.ListUsers;
import uo.sdi.business.impl.actions.user.AddUser;
import uo.sdi.business.impl.actions.user.ChangeUserState;
import uo.sdi.business.impl.actions.user.RemoveUser;
import uo.sdi.business.impl.actions.user.ResetBBDD;
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

	@SuppressWarnings("unchecked")
	@Override
	public List<UserDTO> listUsers(String login) throws BusinessException {
		return (List<UserDTO>) executor.execute(new ListUsers());
	}


	@Override
	public void removeUser(String login) throws BusinessException {
		executor.execute(new RemoveUser(login));
	}

	@Override
	public UserDTO loginUser(String login, String password) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void changeUserStatus(UserDTO user) throws BusinessException {
		executor.execute(new ChangeUserState(user));
	}

	@Override
	public void resetBBDD() throws BusinessException {
		executor.execute(new ResetBBDD());
	}

	@Override
	public List<UserDTO> listAll() throws BusinessException {
		return (List<UserDTO>) executor.execute(new ListAllUsers());
	}

}
