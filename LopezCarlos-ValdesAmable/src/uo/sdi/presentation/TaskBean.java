package uo.sdi.presentation;

import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import uo.sdi.dto.TaskDTO;
import uo.sdi.model.Task;
import uo.sdi.model.User;

@ManagedBean(name = "tareas")
@SessionScoped
public class TaskBean {
	
	private List<Task> listaTareas = new ArrayList<Task>();
	private List<Task> listaTodasTareas = new ArrayList<Task>();
	//tarea sin valores que servira para poder trabajar con la tarea obtenida
	//de un formulario
	private Task tarea = new Task(null);
	
	public TaskBean(){
		iniciaTask(null);
	}
	
	public void iniciaTask(ActionEvent event) {
	    tarea.setId(null);
	    tarea.setUser(new User(""));
	    tarea.setComments("");
	    tarea.setPlanned(new Date());
	    tarea.setFinished(new Date());
	  }

	public void listar(Long id) {
		// TODO Auto-generated method stub
		//lista de las tareas de un usuario en concreto
	}

	public void listaTodasTareas() {
		// TODO Auto-generated method stub
		//lista de todas las tareas que hay
	}

	public Task getTarea() {
		return tarea;
	}

	public void setTarea(Task tarea) {
		this.tarea = tarea;
	}
	
	

}
