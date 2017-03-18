package uo.sdi.business.impl;

import java.util.List;

import uo.sdi.business.impl.actions.user.EnableUser;
import uo.sdi.business.impl.actions.user.ListAllUsers;
import uo.sdi.business.impl.actions.user.ListUsers;
import uo.sdi.business.impl.actions.user.AddUser;
import uo.sdi.business.impl.actions.user.BlockUser;
import uo.sdi.business.impl.actions.user.LoginUser;
import uo.sdi.business.impl.actions.user.RemoveUser;
import uo.sdi.business.impl.actions.user.ResetBBDD;
import uo.sdi.business.impl.actions.user.Update;
import uo.sdi.business.util.BusinessException;
import uo.sdi.business.util.CommandExecutor;
import uo.sdi.business.UserService;
import uo.sdi.dto.UserDTO;

public class UserServiceImpl implements UserService {

	private CommandExecutor executor = new CommandExecutor();
	
	@Override
	public void addUser(UserDTO user) throws BusinessException {
		executor.execute(new AddUser( user ));
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<UserDTO> listUsers() throws BusinessException {
		return (List<UserDTO>) executor.execute(new ListUsers());
	}


	@Override
	public void removeUser(String login) throws BusinessException {
		executor.execute(new RemoveUser(login));
	}

	@Override
	public UserDTO loginUser(String login, String password) 
			throws BusinessException {
		return (UserDTO) executor.execute(new LoginUser(login,password));
	}

	@Override
	public void enableUser(UserDTO user) throws BusinessException {
		executor.execute(new EnableUser(user));
	}
	
	@Override
	public void blockUser(UserDTO user) throws BusinessException {
		executor.execute(new BlockUser(user));
	}

	@Override
	public void resetBBDD() throws BusinessException {
		executor.execute(new ResetBBDD());
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<UserDTO> listAll() throws BusinessException {
		return (List<UserDTO>) executor.execute(new ListAllUsers());
	}

	@Override
	public void update(UserDTO v) throws BusinessException {
		executor.execute(new Update(v));
	}

}
