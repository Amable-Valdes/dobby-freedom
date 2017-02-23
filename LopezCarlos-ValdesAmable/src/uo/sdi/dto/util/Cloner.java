package uo.sdi.dto.util;

import uo.sdi.dto.CategoryDTO;
import uo.sdi.dto.TaskDTO;
import uo.sdi.dto.UserDTO;


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

}
