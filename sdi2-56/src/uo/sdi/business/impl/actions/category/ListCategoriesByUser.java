package uo.sdi.business.impl.actions.category;

import java.util.ArrayList;
import java.util.List;

import uo.sdi.business.util.BusinessException;
import uo.sdi.business.util.Command;
import uo.sdi.dto.CategoryDTO;
import uo.sdi.dto.util.Cloner;
import uo.sdi.model.Category;
import uo.sdi.persistence.CategoryFinder;

/**
 * Este Action nos permite listar todas las categorías de un usuario.
 * 
 * @author Amable y Carlos
 *
 */
public class ListCategoriesByUser implements Command{
	
	private String login;
	
	public ListCategoriesByUser(String login){
		this.login = login;
	}

	@Override
	public Object execute() throws BusinessException {
		List<Category> lista = CategoryFinder.findByUser(login);
		ArrayList<CategoryDTO> listDTO = new ArrayList<CategoryDTO>();
		for(Category category : lista){
			listDTO.add(Cloner.clone(category));
		}
		return listDTO;
	}

}
