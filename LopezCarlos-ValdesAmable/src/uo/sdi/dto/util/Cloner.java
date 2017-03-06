package uo.sdi.dto.util;

import uo.sdi.dto.CategoryDTO;
import uo.sdi.dto.TaskDTO;
import uo.sdi.dto.UserDTO;
import uo.sdi.dto.types.UserStatusDTO;
import uo.sdi.model.Category;
import uo.sdi.model.Task;
import uo.sdi.model.User;
import uo.sdi.model.types.UserStatus;


public class Cloner {

	public static UserDTO clone(UserDTO u) {
		return new UserDTO()
			.setId( 		u.getId() )
			.setEmail( 		u.getEmail() )
			.setIsAdmin(	u.getIsAdmin() )
			.setLogin( 		u.getLogin() )
			.setPassword( 	u.getPassword() )
			.setStatus( 	u.getStatus() );
	}
	
	public static TaskDTO clone(TaskDTO t) {
		return new TaskDTO()
			.setCategoryId( t.getCategoryId() )
			.setComments( 	t.getComments() )
			.setCreated( 	t.getCreated() )
			.setFinished( 	t.getFinished() )
			.setId( 		t.getId() )
			.setPlanned( 	t.getPlanned() )
			.setTitle( 		t.getTitle() )
			.setUserId( 	t.getUserId() );
	}

	public static CategoryDTO clone(CategoryDTO c) {
		return new CategoryDTO()
				.setName( 	c.getName() )
				.setUserId( c.getUserId() );
	}
	
	public static UserDTO clone(User u) {
		return new UserDTO()
			.setId( 		u.getId() )
			.setEmail( 		u.getEmail() )
			.setIsAdmin(	u.getIsAdmin() )
			.setLogin( 		u.getLogin() )
			.setPassword( 	u.getPassword() )
			.setStatus( 	(u.getStatus() == UserStatus.ENABLED) ? 
							UserStatusDTO.ENABLED : UserStatusDTO.DISABLED);
	}
	
	public static TaskDTO clone(Task t) {
		return new TaskDTO()
			.setCategoryId( t.getCategory().getId() )
			.setComments( 	t.getComments() )
			.setCreated( 	t.getCreated() )
			.setFinished( 	t.getFinished() )
			.setId( 		t.getId() )
			.setPlanned( 	t.getPlanned() )
			.setTitle( 		t.getTitle() )
			.setUserId( 	t.getUser().getId() );
	}

	public static CategoryDTO clone(Category c) {
		return new CategoryDTO()
				.setName( 	c.getName() )
				.setUserId( c.getUser().getId() );
	}

}
