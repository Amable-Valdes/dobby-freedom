package uo.sdi.business.impl;

import java.util.List;

import uo.sdi.business.impl.actions.user.EnableUser;
import uo.sdi.business.impl.actions.user.ListAllUsers;
import uo.sdi.business.impl.actions.user.AddUser;
import uo.sdi.business.impl.actions.user.BlockUser;
import uo.sdi.business.impl.actions.user.LoginUser;
import uo.sdi.business.impl.actions.user.RemoveUser;
import uo.sdi.business.impl.actions.user.ResetBBDD;
import uo.sdi.business.util.BusinessException;
import uo.sdi.business.util.CommandExecutor;
import uo.sdi.business.UserService;
import uo.sdi.dto.UserDTO;

/**
 * Implementación de la interfaz UserService que da distintos servicios 
 * (referidos al manejo y administración de los usuarios) a los distintos 
 * usuarios del sistema.
 * 
 * @author Amable y Carlos
 *
 */
public class UserServiceImpl implements UserService {

	private CommandExecutor executor = new CommandExecutor();
	
	/* (non-Javadoc)
	 * @see uo.sdi.business.UserService#addUser(uo.sdi.dto.UserDTO)
	 */
	@Override
	public void addUser(UserDTO user) throws BusinessException {
		executor.execute(new AddUser( user ));
	}

	/* (non-Javadoc)
	 * @see uo.sdi.business.UserService#removeUser(java.lang.String)
	 */
	@Override
	public void removeUser(String login) throws BusinessException {
		executor.execute(new RemoveUser(login));
	}

	/* (non-Javadoc)
	 * @see uo.sdi.business.UserService#loginUser(java.lang.String, java.lang.String)
	 */
	@Override
	public UserDTO loginUser(String login, String password) 
			throws BusinessException {
		return (UserDTO) executor.execute(new LoginUser(login,password));
	}

	/* (non-Javadoc)
	 * @see uo.sdi.business.UserService#enableUser(uo.sdi.dto.UserDTO)
	 */
	@Override
	public void enableUser(UserDTO user) throws BusinessException {
		executor.execute(new EnableUser(user));
	}
	
	/* (non-Javadoc)
	 * @see uo.sdi.business.UserService#blockUser(uo.sdi.dto.UserDTO)
	 */
	@Override
	public void blockUser(UserDTO user) throws BusinessException {
		executor.execute(new BlockUser(user));
	}

	/* (non-Javadoc)
	 * @see uo.sdi.business.UserService#resetBBDD()
	 */
	@Override
	public void resetBBDD() throws BusinessException {
		executor.execute(new ResetBBDD());
	}

	/* (non-Javadoc)
	 * @see uo.sdi.business.UserService#listAll()
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<UserDTO> listAll() throws BusinessException {
		return (List<UserDTO>) executor.execute(new ListAllUsers());
	}

}
