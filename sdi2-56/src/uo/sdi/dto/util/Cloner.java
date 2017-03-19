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
		UserDTO user = new UserDTO()
		.setId( 		u.getId() )
		.setEmail( 		u.getEmail() )
		.setIsAdmin(	u.getIsAdmin() )
		.setLogin( 		u.getLogin() )
		.setPassword( 	u.getPassword() );
		user.setStatus(u.getStatus());
		return user;
	}
	
	public static TaskDTO clone(TaskDTO t) {
		TaskDTO task = new TaskDTO()
		.setCategoryId( t.getCategoryId() )
		.setComments( 	t.getComments() )
		.setId( 		t.getId() )
		.setTitle( 		t.getTitle() )
			.setUserId( 	t.getUserId() );
		task.setCreated(t.getCreated());
		task.setFinished(t.getFinished());
		task.setPlanned(t.getPlanned());
		return task;
	}

	public static CategoryDTO clone(CategoryDTO c) {
		CategoryDTO cat = new CategoryDTO();
		cat.setName(c.getName());
		cat.setUserId(c.getUserId());
		return cat;
	}
	
	public static UserDTO clone(User u) {
		UserDTO user = new UserDTO()
		.setId( 		u.getId() )
		.setEmail( 		u.getEmail() )
		.setIsAdmin(	u.getIsAdmin() )
		.setLogin( 		u.getLogin() )
		.setPassword( 	u.getPassword() );
		user.setStatus( 	(u.getStatus() == UserStatus.ENABLED) ? 
							UserStatusDTO.ENABLED : UserStatusDTO.DISABLED);
		return user;
	}
	
	public static TaskDTO clone(Task t) {
		if (t.getCategory() == null) {
			TaskDTO task = new TaskDTO().setCategoryId(null)
					.setComments(t.getComments()).setId(t.getId())
					.setTitle(t.getTitle()).setUserId(t.getUser().getId());
			task.setCreated(t.getCreated());
			task.setFinished(t.getFinished());
			task.setPlanned(t.getPlanned());
			return task;
		} else {
			TaskDTO task = new TaskDTO().setCategoryId(t.getCategory().getId())
					.setComments(t.getComments()).setId(t.getId())
					.setTitle(t.getTitle()).setUserId(t.getUser().getId());
			task.setCreated(t.getCreated());
			task.setFinished(t.getFinished());
			task.setPlanned(t.getPlanned());
			return task;
		}
	}

	public static CategoryDTO clone(Category c) {
		CategoryDTO cat = new CategoryDTO();
		cat.setName(c.getName());
		cat.setUserId(c.getUser().getId());
		return cat;
	}

}
