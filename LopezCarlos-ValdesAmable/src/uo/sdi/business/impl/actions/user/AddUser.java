package uo.sdi.business.impl.actions.user;

import uo.sdi.business.util.BusinessException;
import uo.sdi.business.util.Command;
import uo.sdi.dto.UserDTO;
import uo.sdi.persistence.UserFinder;
import uo.sdi.persistence.util.Jpa;

public class AddUser implements Command{

	private UserDTO userDto;
	
	public AddUser(UserDTO user){
		this.userDto = user;
	}
	
	@Override
	public Object execute() throws BusinessException {
		//Comprobaciones
		assertLoginUsed( userDto.getLogin() );
		//Lo guardamos en la BBDD
		Jpa.getManager().persist(userDto);
		return null;
	}
	
	private void assertLoginUsed(String login) throws BusinessException
	{
		if( UserFinder.findByLogin(login) == null ) return;
		throw new BusinessException("El login ya existe");
	}

}
