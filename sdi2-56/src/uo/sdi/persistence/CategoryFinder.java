package uo.sdi.persistence;

import java.util.List;

import javax.persistence.NoResultException;

import uo.sdi.model.Category;
import uo.sdi.persistence.util.Jpa;

/**
 * Clase CategoryFinder que permite realizar consultas sobre la BBDD y 
 * devuelve objetos Category ya persistentes.
 * 
 * @author Amable y Carlos
 *
 */
public class CategoryFinder {

	/**
	 * Lista todas las Category de de la BBDD.
	 * 
	 * @return	Todas las Category d la BBDD.
	 */
	public static List<Category> findAll() {
		return Jpa.getManager()
				.createNamedQuery("Category.findAll", Category.class)
				.getResultList();
	}

	/**
	 * El nombre de la categoria es único para un usuario, por lo que se 
	 * puede buscar una categoría por usuario y nombre categoría.
	 * 
	 * @param id	id del usuario en la BBDD.
	 * @param categoryName	Nombre de la categoría.
	 * @return	Un objeto de tipo Category.
	 */
	public static Category findByUserAndName(Long id, String categoryName) {
		try{
			return Jpa.getManager()
				.createNamedQuery("Category.findByUserAndName", Category.class)
				.setParameter(1, id)
				.setParameter(2, categoryName)
				.getSingleResult();
		}catch(NoResultException e){
			return null;
		}
	}

	/**
	 * Encuentra por id de la base de datos un Category.
	 * 
	 * @param categoryId	ID de la BBDD de la categoría.
	 * @return Un objeto de tipo Category.
	 */
	public static Category findById(Long categoryId) {
		try{
			return Jpa.getManager()
				.createNamedQuery("Category.findById", Category.class)
				.setParameter(1, categoryId)
				.getSingleResult();
		}catch(NoResultException e){
			return null;
		}
	}

	/**
	 * Lista todas las Category de un usuario de la BBDD.
	 * 
	 * @param loginUser	El login del usuario del que queremos las Category.
	 * @return	Una lista de Category
	 */
	public static List<Category> findByUser(String loginUser) {
		return Jpa.getManager()
				.createNamedQuery("Category.findByUser", Category.class)
				.setParameter(1, loginUser)
				.getResultList();
	}

}
