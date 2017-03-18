package uo.sdi.business;

import java.util.List;

import uo.sdi.business.util.BusinessException;
import uo.sdi.dto.CategoryDTO;
import uo.sdi.dto.UserDTO;

/**
 * Esta interfaz proporciona servicios referidos a la administración de las 
 * categorías del sistema.
 * 
 * @author Amable y Carlos
 *
 */
public interface CategoryService {

	/**
	 * Busca las categorías de un usuario por su login.
	 * 
	 * @param userDTO	El login del usuario del que queremos saber sus 
	 * categorias.
	 * @return	Una lista de CategoryDTO con toda la informacion de las 
	 * categorias del usuario.
	 * @throws BusinessException	Si se cumple alguna irregularidad en la 
	 * lógica de negocio el sistema lanzará a capas superiores una excepción 
	 * de tipo BusinessException (Excepción de lógica de negocio).
	 */
	List<CategoryDTO> findCategoriesByUser(UserDTO userDTO) 
			throws BusinessException;
}
