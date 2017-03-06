package uo.sdi.business.impl.actions.user;

import uo.sdi.business.util.BusinessException;
import uo.sdi.business.util.Command;
import uo.sdi.business.util.Encriptator;
import uo.sdi.dto.UserDTO;
import uo.sdi.dto.types.UserStatusDTO;
import uo.sdi.model.User;
import uo.sdi.model.types.UserStatus;
import uo.sdi.persistence.UserFinder;
import uo.sdi.persistence.util.Jpa;

public class Update implements Command {
	private UserDTO userDto;

	public Update(UserDTO userDTO) {
		this.userDto = userDTO;
	}

	@Override
	public Object execute() throws BusinessException {
		User u = UserFinder.findByLogin(userDto.getLogin());
		if(userDto.getStatus().equals(UserStatusDTO.ENABLED)){
			u.setStatus(UserStatus.ENABLED);
		}
		else{
			u.setStatus(UserStatus.DISABLED);
		}
		Jpa.getManager().merge(u);
		return null;
	}

}
