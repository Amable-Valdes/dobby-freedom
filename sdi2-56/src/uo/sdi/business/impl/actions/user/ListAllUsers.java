package uo.sdi.business.impl.actions.user;

import java.util.ArrayList;
import java.util.List;

import uo.sdi.business.util.BusinessException;
import uo.sdi.business.util.Command;
import uo.sdi.dto.UserDTO;
import uo.sdi.dto.util.Cloner;
import uo.sdi.model.User;
import uo.sdi.persistence.UserFinder;

/**
 * Este Action nos permite listar los usuarios que no sean administrador.
 * 
 * @author Amable y Carlos
 *
 */
public class ListAllUsers implements Command {

	@Override
	public Object execute() throws BusinessException {
		List<User> listUsers = UserFinder.findAll();
		ArrayList<UserDTO> list = new ArrayList<UserDTO>();
		for (User user : listUsers) {
			if(!user.getIsAdmin()){
				list.add(Cloner.clone(user));
			}
		}
		return list;
	}
}
