package uo.sdi.presentation;

import java.awt.event.ActionEvent;
import java.io.Serializable;
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
	private Date created;
	private Date planned;
	private String category;
	private String user;
	
	private Long id;
	
	public TaskBean(){
		iniciaTask(null);
	}
	
	public void init(){
		user = (String) getFromSession("login");
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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
	    tarea.setCreated(new Date());
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

	public Date getPlanned() {
		return planned;
	}

	public void setPlanned(Date planned) {
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
			task.setPlanned(planned);
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
			vaciar();
			return "exito";
		} catch (Exception e) { 
			e.printStackTrace();
			return "usuario";
		}
	}
	
	public String finalizarTask(TaskDTO task) {
		try {
			user = (String) getFromSession("login");
			Factories.services.createTaskService()
				.finishTask(user, task);
			return "exito";
		} catch (Exception e) { 
			return "fracaso";
		}
	}
	
	public String editar(TaskDTO t){
		tarea = t;
		title = tarea.getTitle();
		comments = tarea.getComments();
		created = tarea.getCreated();
		planned = tarea.getPlanned();
		id = tarea.getId();
		return "editarTarea";
	}
	
	public String editarTask() {
		try {
			user = (String) getFromSession("login");
			TaskDTO task = Factories.services.createTaskService()
					.findTask(user,created);
			task.setTitle(title);
			task.setPlanned(planned);
			task.setComments(comments);
			CategoryDTO categoria = Factories.services.createCategoryService()
					.findCategoryByUserAndCategoryName(user, category);
			task.setCategoryId(categoria.getId());
			Factories.services.createTaskService().updateTask(task);
			exitoEditarTarea();
			listar(user);
			vaciar();
			return "usuario";
		} catch (Exception e) { 
			return "fracaso";
		}
	}
	
	public void exitoEditarTarea(){
		FacesContext.getCurrentInstance().addMessage(
				null, new FacesMessage(FacesMessage.SEVERITY_INFO, 
						"Editar Tarea", "La tarea se ha editado con exito"));
	}
//TODO
	/*public void listar(String login) {
		try {
			listaTareas = Factories.services.createTaskService()
					.listTasksNotFinished(login);
		} catch (BusinessException e) {
			e.printStackTrace();
		}
	}*/
	
	public void listar(String login) {
		try {

			listaTareas = Factories.services.createTaskService()
					.listTasksInbox(login,true);
		} catch (BusinessException e) {
			e.printStackTrace();
		}
	}

	public void listarTaskInbox(UserDTO user,boolean fin) {
		try {
			listaTareas = Factories.services.createTaskService()
					.listTasksInbox(user.getLogin(),fin);
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
	
	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public void vaciar() {
		category = "";
		comments="";
		title="";
		planned= new Date();
	}
	
}
