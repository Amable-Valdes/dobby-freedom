package uo.sdi.business.impl.actions.user;

import uo.sdi.business.util.BusinessException;
import uo.sdi.business.util.Command;
import uo.sdi.business.util.Encriptator;
import uo.sdi.dto.UserDTO;
import uo.sdi.dto.types.UserStatusDTO;
import uo.sdi.model.User;
import uo.sdi.model.types.UserStatus;
import uo.sdi.persistence.UserFinder;

public class LoginUser implements Command {
	
	private String login;
	private String password;

	public LoginUser(String login, String password) {
		this.login = login;
		this.password = password;
	}

	@Override
	public Object execute() throws BusinessException {
		User user = UserFinder.findByLogin(login);
		assertUserExist(user);
		if(user.getStatus() == UserStatus.ENABLED && 
				user.getPassword().equals(Encriptator.encrypt(password))){
			UserDTO userDTO = new UserDTO();
			userDTO.setId(user.getId());
			userDTO.setLogin(user.getLogin());
			userDTO.setEmail(user.getEmail());
			userDTO.setIsAdmin(user.getIsAdmin());
			userDTO.setPassword(user.getPassword());
			userDTO.setStatus((user.getStatus() == UserStatus.ENABLED) ? 
					UserStatusDTO.ENABLED : UserStatusDTO.DISABLED);
			return userDTO;
		}
		return null;
	}

	private void assertUserExist(User user) throws BusinessException
	{
		if( user != null ) return;
		throw new BusinessException("El usuario no existe");
	}
}
