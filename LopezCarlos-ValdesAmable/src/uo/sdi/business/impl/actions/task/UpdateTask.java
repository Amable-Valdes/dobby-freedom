package uo.sdi.business.impl.actions.task;

import uo.sdi.business.util.BusinessException;
import uo.sdi.business.util.Command;
import uo.sdi.dto.TaskDTO;
import uo.sdi.model.Association;
import uo.sdi.model.Category;
import uo.sdi.model.Task;
import uo.sdi.persistence.CategoryFinder;
import uo.sdi.persistence.TaskFinder;

public class UpdateTask implements Command {

	private TaskDTO taskDTO_Old;
	private TaskDTO taskDTO_Update;

	public UpdateTask(TaskDTO taskDTO_Old, TaskDTO taskDTO_Update) {
		this.taskDTO_Old = taskDTO_Old;
		this.taskDTO_Update = taskDTO_Update;
	}

	@Override
	public Object execute() throws BusinessException {
		//Buscamos la tarea
		Task task = TaskFinder.findByUser_And_CreatedDate(
				taskDTO_Old.getUserId(), taskDTO_Old.getCreated());
		//Modificamos sus valores
		task.setTitle(taskDTO_Update.getTitle());
		task.setComments(taskDTO_Update.getComments());
		task.setPlanned(taskDTO_Update.getPlanned());
		//¿Ha cambiado de categoria?
		if (
		// Si son !=null y son distintas
		(taskDTO_Old.getCategoryId() != null
				&& taskDTO_Update.getCategoryId() != null && !taskDTO_Old
				.getCategoryId().equals(taskDTO_Update.getCategoryId())) ||
		// O si una es null y la otra no
		(taskDTO_Old == null && taskDTO_Update != null || taskDTO_Old != null
				&& taskDTO_Update == null)) {
			//TODO no se si aqui se le puede pasar null, tenemos que comprobarlo en tiempo de ejecución. Si pasa entonces es que jpa acepta en sus consultas null y no hay que hacer nada, si no tenemos que comprobarlo manualmente con ifs
			Category category_old = CategoryFinder.findById(taskDTO_Old
					.getCategoryId());
			Category category_update = CategoryFinder.findById(taskDTO_Update
					.getCategoryId());
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
