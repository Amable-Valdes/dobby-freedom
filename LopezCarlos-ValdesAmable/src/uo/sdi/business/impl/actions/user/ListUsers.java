package uo.sdi.business.impl.actions.user;

import java.util.ArrayList;
import java.util.List;

import uo.sdi.business.util.BusinessException;
import uo.sdi.business.util.Command;
import uo.sdi.dto.UserDTO;
import uo.sdi.dto.types.UserStatusDTO;
import uo.sdi.model.User;
import uo.sdi.model.types.UserStatus;
import uo.sdi.persistence.UserFinder;

public class ListUsers implements Command {

	@Override
	public Object execute() throws BusinessException {
		List<User> listUsers = UserFinder.findAll();
		ArrayList<UserDTO> list = new ArrayList<UserDTO>();
		UserDTO userDTO;
		for (User user : listUsers) {
			userDTO = new UserDTO();
			userDTO.setId(user.getId());
			userDTO.setLogin(user.getLogin());
			userDTO.setPassword(user.getPassword());
			userDTO.setEmail(user.getEmail());
			userDTO.setStatus((user.getStatus() == UserStatus.ENABLED) ? 
					UserStatusDTO.ENABLED : UserStatusDTO.DISABLED);
			userDTO.setIsAdmin(user.getIsAdmin());
			list.add(userDTO);
		}
		return list;
	}

}
