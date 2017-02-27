package uo.sdi.persistence;

import java.util.List;
import uo.sdi.model.Category;
import uo.sdi.persistence.util.Jpa;

public class CategoryFinder {

	public static List<Category> findAll() {
		return Jpa.getManager()
				.createNamedQuery("Category.findAll", Category.class)
				.getResultList();
	}

}
