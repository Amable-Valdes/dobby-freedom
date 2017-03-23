package uo.sdi.business.impl.actions.task;

import uo.sdi.business.util.Asserts;
import uo.sdi.business.util.BusinessException;
import uo.sdi.business.util.Command;
import uo.sdi.dto.TaskDTO;
import uo.sdi.model.Association;
import uo.sdi.model.Category;
import uo.sdi.model.Task;
import uo.sdi.persistence.CategoryFinder;
import uo.sdi.persistence.TaskFinder;

public class UpdateTask implements Command {

	private TaskDTO taskDTO;

	public UpdateTask(TaskDTO taskDTO) {
		this.taskDTO = taskDTO;
	}

	@Override
	public Object execute() throws BusinessException {
		//Buscamos la tarea
		Task task = TaskFinder.findByUser_And_CreatedDate(
				taskDTO.getUserId(), taskDTO.getCreated());
		//Comprobamos que la tarea no está ya finalizada
		Asserts.assertTaskIsNotFinished(task);
		//Modificamos sus valores
		task.setTitle(taskDTO.getTitle());
		task.setComments(taskDTO.getComments());
		task.setPlanned(taskDTO.getPlanned());
		//¿Ha cambiado de categoria?
		if (
		// Si son !=null y son distintas
		(taskDTO.getCategoryId() != null
		&& task.getCategory() != null 
		&& !taskDTO.getCategoryId().equals(task.getCategory().getId())) ||
		// O si una es null y la otra no
		(task.getCategory() == null && taskDTO.getCategoryId() != null || 
		task.getCategory() != null && taskDTO.getCategoryId() == null)) {
			Category category_old = task.getCategory();
			Category category_update = CategoryFinder.findById(
					taskDTO.getCategoryId());
			//Comprobamos que existe la categoría
			//Esto de aqui, si lo dejo, no permite poner categoria null
			//y siempre vamos a poder meter una categoria que exista
			//porque es un combobox con las categorias del usuario
			//Asserts.assertCategoryExist(category_update);
			//Si tenia categoria, se quita
			if(category_old != null){
				Association.Classifies.unlink(category_old, task);
			}
			//Si se le pone en una categoria en especial, se le añade
			if(category_update != null){
				Association.Classifies.link(category_update, task);
			}
		}
		return null;
	}

}
