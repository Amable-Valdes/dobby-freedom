package uo.sdi.business.impl.actions.user;

import uo.sdi.business.util.BusinessException;
import uo.sdi.business.util.Command;
import uo.sdi.dto.UserDTO;
import uo.sdi.model.User;
import uo.sdi.model.types.UserStatus;
import uo.sdi.persistence.UserFinder;

public class ChangeUserState implements Command {

	private UserDTO userDTO;

	public ChangeUserState(UserDTO user) {
		this.userDTO = user;
	}

	@Override
	public Object execute() throws BusinessException {
		User user = UserFinder.findByLogin(userDTO.getLogin());
		assertUserExist(user);
		if (user.getStatus() == UserStatus.ENABLED) {
			user.setStatus(UserStatus.DISABLED);
		} else {
			user.setStatus(UserStatus.ENABLED);
		}
		return null;
	}

	private void assertUserExist(User user) throws BusinessException {
		if (user != null)
			return;
		throw new BusinessException("El usuario no existe");
	}

}
