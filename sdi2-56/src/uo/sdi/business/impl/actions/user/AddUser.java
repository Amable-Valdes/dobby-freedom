package uo.sdi.business.impl.actions.user;

import uo.sdi.business.util.Asserts;
import uo.sdi.business.util.BusinessException;
import uo.sdi.business.util.Command;
import uo.sdi.business.util.Encriptator;
import uo.sdi.dto.UserDTO;
import uo.sdi.model.User;
import uo.sdi.model.types.UserStatus;
import uo.sdi.persistence.UserFinder;
import uo.sdi.persistence.util.Jpa;

/**
 * Este Action nos permite añadir un nuevo usuario al sistema.
 * 
 * @author Amable y Carlos
 *
 */
public class AddUser implements Command{

	private UserDTO userDTO;
	
	public AddUser(UserDTO userDTO){
		this.userDTO = userDTO;
	}
	
	@Override
	public Object execute() throws BusinessException {
		//Buscamos el usuario
		User user = UserFinder.findByLogin(userDTO.getLogin());
		//Comprobamos que no existe un usuario con login como ese.
		Asserts.assertUserNoExist(user);
		//Creamos el usuario
		User newUser = new User(userDTO.getLogin());
		newUser.setEmail(userDTO.getEmail());
		newUser.setIsAdmin(false);
		newUser.setStatus(UserStatus.ENABLED);
		newUser.setPassword(Encriptator.encrypt(userDTO.getPassword()));
		Jpa.getManager().persist(newUser);
		return null;
	}
}
