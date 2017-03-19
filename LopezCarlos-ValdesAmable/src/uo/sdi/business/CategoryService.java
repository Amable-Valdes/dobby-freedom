package uo.sdi.business;

import java.util.List;

import uo.sdi.business.util.BusinessException;
import uo.sdi.dto.CategoryDTO;

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
	 * @param login	El login del usuario del que queremos saber sus 
	 * categorías.
	 * @return	Una lista de CategoryDTO con toda la información de las 
	 * categorias del usuario.
	 * @throws BusinessException	Si se cumple alguna irregularidad en la 
	 * lógica de negocio el sistema lanzará a capas superiores una excepción 
	 * de tipo BusinessException (Excepción de lógica de negocio).
	 */
	List<CategoryDTO> findCategoriesByUser(String login) 
			throws BusinessException;

	/**
	 * Encuentra una categoria por su nombre y por su usuario.
	 * 
	 * @param login	El login del usuario del que buscamos la categoría.
	 * @param name	El nombre de la categoría que estamos buscando.
	 * @return	Un CategoryDTO con toda la información de la categoria.
	 * @throws BusinessException	Si se cumple alguna irregularidad en la 
	 * lógica de negocio el sistema lanzará a capas superiores una excepción 
	 * de tipo BusinessException (Excepción de lógica de negocio).
	 */
	CategoryDTO findCategoryByUserAndCategoryName(String login, String name)
			throws BusinessException;
}
