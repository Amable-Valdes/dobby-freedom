package uo.sdi.presentation;

import java.awt.event.ActionEvent;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import uo.sdi.business.util.BusinessException;
import uo.sdi.dto.CategoryDTO;
import uo.sdi.dto.TaskDTO;
import uo.sdi.dto.UserDTO;
import uo.sdi.infrastructure.Factories;

@ManagedBean(name = "tareas")
@SessionScoped
public class TaskBean implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private List<TaskDTO> listaTareas = new ArrayList<TaskDTO>();
	private List<TaskDTO> listaTareasCopia = new ArrayList<TaskDTO>();
	//tarea sin valores que servira para poder trabajar con la tarea obtenida
	//de un formulario
	private TaskDTO tarea = new TaskDTO();
	private String title;
	private String comments;
	private String planned;
	private String category;
	private String user;
	
	public TaskBean(){
		iniciaTask(null);
	}
	
	public void init(){
		user = (String) getFromSession("login");
	}
	
	private Object getFromSession(String key) {
		return FacesContext.getCurrentInstance().getExternalContext()
				.getSessionMap().get(key);
	}
	
	public void iniciaTask(ActionEvent event) {
		tarea.setCategoryId(null);
		tarea.setTitle("");
		tarea.setUserId(null);
	    tarea.setComments("");
	    tarea.setPlanned(new Date());
	    tarea.setFinished(new Date());
	}
	
	
	public List<TaskDTO> getListaTareasCopia() {
		return listaTareasCopia;
	}

	public void setListaTareasCopia(List<TaskDTO> listaTareasCopia) {
		this.listaTareasCopia = listaTareasCopia;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public String getPlanned() {
		return planned;
	}

	public void setPlanned(String planned) {
            this.planned = planned;
	}
	
	public void exitoCrearTarea(){
		FacesContext.getCurrentInstance().addMessage(
				null, new FacesMessage(FacesMessage.SEVERITY_INFO, 
						"Crear Tarea", "La tarea se ha creado con exito"));
	}
	
	public void errorCrearTarea(){
		FacesContext.getCurrentInstance().addMessage(
				null, new FacesMessage(FacesMessage.SEVERITY_ERROR, 
						"Crear Tarea", "La fecha debe tener un valor correcto"));
	}

	public String crearTask() {
		try {
			
			TaskDTO task = new TaskDTO();
			user = (String) getFromSession("login");
			task.setTitle(title);
			task.setComments(comments);
			if(planned.equals("mm/dd/yyyy")){
				errorCrearTarea();
				return "usuario";
			}
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			 Date date = formatter.parse(planned);
			task.setPlanned(date);
			CategoryDTO c = new CategoryDTO();
			if(!category.equals("")){
				c.setName(category);
			}
			else{
				c= null;
			}
			Factories.services.createTaskService().addTask(
					user, c, task);
			exitoCrearTarea();
			listar(user);
			return "exito";
		} catch (Exception e) { 
			e.printStackTrace();
			return "usuario";
		}
	}
	
	public String finalizarTask(UserDTO user,CategoryDTO category, TaskDTO task) {
		try {
			Factories.services.createTaskService().finishTask(user.getLogin(), 
					category.getName(), task.getCreated(), task.getTitle());
			return "exito";
		} catch (Exception e) { 
			return "fracaso";
		}
	}
	
	public String editarTask(TaskDTO task_Old, TaskDTO task_New) {
		try {
			Factories.services.createTaskService().updateTask(task_Old, task_New);
			return "exito";
		} catch (Exception e) { 
			return "fracaso";
		}
	}

	public void listar(String login) {
		try {
			listaTareas = Factories.services.createTaskService()
					.listTasksInbox(login,true);
		} catch (BusinessException e) {
			e.printStackTrace();
		}
	}

	public void listarTaskInbox(UserDTO user) {
		try {
			listaTareas = Factories.services.createTaskService()
					.listTasksInbox(user.getLogin(),true);
		} catch (BusinessException e) {
			e.printStackTrace();
		}
	}
	
	public void listarTaskHoy(UserDTO user) {
		try {
			listaTareas = Factories.services.createTaskService()
					.listTasksToday(user.getLogin());
		} catch (BusinessException e) {
			e.printStackTrace();
		}
	}
	
	public void listarTasksSemana(UserDTO user) {
		try {
			listaTareas = Factories.services.createTaskService()
					.listTasksWeek(user.getLogin());
		} catch (BusinessException e) {
			e.printStackTrace();
		}
	}
	
	public void listarByCategory(UserDTO user,CategoryDTO category) {
		try {
			listaTareas = Factories.services.createTaskService()
					.listTasksByCategory(user.getLogin(), category.getName());
		} catch (BusinessException e) {
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

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public void vaciar() {
		category = "";
		comments="";
		title="";
		planned="mm/dd/yyyy";
	}
	
}
