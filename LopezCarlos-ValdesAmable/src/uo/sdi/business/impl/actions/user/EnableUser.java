package uo.sdi.business.impl.actions.user;

import alb.util.log.Log;
import uo.sdi.business.util.BusinessException;
import uo.sdi.business.util.Command;
import uo.sdi.dto.UserDTO;
import uo.sdi.model.User;
import uo.sdi.model.types.UserStatus;
import uo.sdi.persistence.UserFinder;
import uo.sdi.persistence.util.Jpa;

public class EnableUser implements Command {

	private UserDTO userDTO;

	public EnableUser(UserDTO user) {
		this.userDTO = user;
	}

	@Override
	public Object execute() throws BusinessException {
			User user = UserFinder.findByLogin(userDTO.getLogin());
			assertUserExist(user);
			assertIsBlocked(user);
			user.setStatus(UserStatus.ENABLED);
			Jpa.getManager().merge(user);
			return null;
	}

	private void assertUserExist(User user) throws BusinessException {
		if (user != null) return;
		throw new BusinessException("El usuario no existe");
	}

	private void assertIsBlocked(User user) throws BusinessException {
		if (user.getStatus() == UserStatus.DISABLED) return;
		throw new BusinessException("El usuario ya está activado");
	}
}
