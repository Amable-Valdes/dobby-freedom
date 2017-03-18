package uo.sdi.business.util;

import uo.sdi.model.Category;
import uo.sdi.model.Task;
import uo.sdi.model.User;
import uo.sdi.model.types.UserStatus;

public class Asserts {

	//Asserts User
	public static void assertUserExist(User user) throws BusinessException {
		if (user != null) return;
		throw new BusinessException("El usuario no existe");
	}
	
	public static void assertSameUsers(User user1, User user2) throws BusinessException {
		if (user1.equals(user2)) return;
		throw new BusinessException("Los usuarios no son el mismo");
	}
	
	public static void assertUserIsEnabled(User user) throws BusinessException{
		if (user.getStatus() == UserStatus.ENABLED) return;
		throw new BusinessException("El usuario ya está bloqueado");
	}
	
	public static void assertUserIsBlocked(User user) throws BusinessException{
		if (user.getStatus() == UserStatus.DISABLED) return;
		throw new BusinessException("El usuario ya está activado");
	}
	
	//Asserts Category
	public static void assertCategoryExist(Category category) throws BusinessException {
		if (category == null) {
			throw new BusinessException("La categoría no existe");
		}
	}
	
	//Asserts Task
	public static void assertTaskExist(Task task) throws BusinessException {
		if (task != null) return;
		throw new BusinessException("La tearea no existe");
	}
	
	public static void assertTaskIsNotFinished(Task task) throws BusinessException {
		if (task.getFinished() == null) return;
		throw new BusinessException("La tearea ya esta finalizada");
	}
}
