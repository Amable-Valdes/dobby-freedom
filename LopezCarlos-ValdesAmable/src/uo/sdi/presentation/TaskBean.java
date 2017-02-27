package uo.sdi.presentation;

import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import uo.sdi.business.util.BusinessException;
import uo.sdi.dto.TaskDTO;
import uo.sdi.infrastructure.Factories;
import uo.sdi.model.Task;
import uo.sdi.model.User;

@ManagedBean(name = "tareas")
@ApplicationScoped
public class TaskBean {
	
	private List<TaskDTO> listaTareas = new ArrayList<TaskDTO>();
	private List<TaskDTO> listaTodasTareas = new ArrayList<TaskDTO>();
	//tarea sin valores que servira para poder trabajar con la tarea obtenida
	//de un formulario
	private TaskDTO tarea = new TaskDTO();
	
	public TaskBean(){
		iniciaTask(null);
	}
	
	public void iniciaTask(ActionEvent event) {
		tarea.setCategoryId(null);
		tarea.setTitle("");
		tarea.setUserId(null);
	    tarea.setComments("");
	    tarea.setPlanned(new Date());
	    tarea.setFinished(new Date());
	    listaTodasTareas();
	  }

	public void listar(String login) {
		//listaTareas = Factories.services.createTaskService().findTaskByLogin(login);
	}

	public void listaTodasTareas() {
		//lista de todas las tareas que hay
		try {
			listaTodasTareas = Factories.services.createTaskService().findAllTasks();
		} catch (BusinessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public TaskDTO getTarea() {
		return tarea;
	}

	public void setTarea(TaskDTO tarea) {
		this.tarea = tarea;
	}

	public List<TaskDTO> getListaTareas() {
		return listaTareas;
	}

	public void setListaTareas(List<TaskDTO> listaTareas) {
		this.listaTareas = listaTareas;
	}

	public List<TaskDTO> getListaTodasTareas() {
		return listaTodasTareas;
	}

	public void setListaTodasTareas(List<TaskDTO> listaTodasTareas) {
		this.listaTodasTareas = listaTodasTareas;
	}
	
	

}
