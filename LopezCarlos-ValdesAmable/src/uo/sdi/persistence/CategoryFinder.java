package uo.sdi.persistence;

import java.util.List;

import javax.persistence.NoResultException;
import uo.sdi.model.Category;
import uo.sdi.persistence.util.Jpa;

public class CategoryFinder {

	public static List<Category> findAll() {
		return Jpa.getManager()
				.createNamedQuery("Category.findAll", Category.class)
				.getResultList();
	}

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

}
