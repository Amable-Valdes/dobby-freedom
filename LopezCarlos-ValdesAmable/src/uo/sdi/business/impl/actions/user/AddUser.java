package uo.sdi.business.impl.actions.user;

import uo.sdi.business.util.BusinessException;
import uo.sdi.business.util.Command;
import uo.sdi.business.util.Encriptator;
import uo.sdi.dto.UserDTO;
import uo.sdi.model.User;
import uo.sdi.model.types.UserStatus;
import uo.sdi.persistence.UserFinder;
import uo.sdi.persistence.util.Jpa;

public class AddUser implements Command{

	private UserDTO userDto;
	
	public AddUser(UserDTO userDTO){
		this.userDto = userDTO;
	}
	
	@Override
	public Object execute() throws BusinessException {
		assertLoginUsed(userDto.getLogin());
		User newUser = new User(userDto.getLogin());
		newUser.setEmail(userDto.getEmail());
		newUser.setIsAdmin(false);
		newUser.setStatus(UserStatus.ENABLED);
		newUser.setPassword(Encriptator.encrypt(userDto.getPassword()));
		Jpa.getManager().persist(newUser);
		return null;
	}
	
	private void assertLoginUsed(String login) throws BusinessException
	{
		if( UserFinder.findByLogin(login) == null ) return;
		throw new BusinessException("El login ya existe");
	}

}
