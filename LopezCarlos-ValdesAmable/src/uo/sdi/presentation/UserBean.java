package uo.sdi.presentation;

import java.awt.event.ActionEvent;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import org.primefaces.event.RowEditEvent;

import alb.util.log.Log;
import uo.sdi.business.ServicesFactory;
import uo.sdi.business.UserService;
import uo.sdi.business.util.BusinessException;
import uo.sdi.dto.UserDTO;
import uo.sdi.dto.types.UserStatusDTO;
import uo.sdi.infrastructure.Factories;

@ManagedBean(name = "usuarios")
@SessionScoped
public class UserBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@ManagedProperty("#{tareas}")
	private TaskBean tasks;
	
	private String pass = "";
	private String passRew="";
	public String getPassRew() {
		return passRew;
	}

	public void setPassRew(String passRew) {
		this.passRew = passRew;
	}

	private String login = "";
	private UserDTO user = new UserDTO();
	private List<UserDTO> listaUsuarios = new ArrayList<UserDTO>();
	private List<UserStatusDTO> estados = new ArrayList<UserStatusDTO>();

	public UserBean() {
		
		iniciaUser(null);
		estados.add(UserStatusDTO.ENABLED);
		estados.add(UserStatusDTO.DISABLED);
	}

	public void iniciaUser(ActionEvent event) {
	    user.setId(null);
	    user.setEmail("");
	    user.setIsAdmin(false);
	    user.setPassword("");
	    user.setStatus(UserStatusDTO.ENABLED);
	  }
	
	public UserDTO getUsuario() {
		return user;
	}
	
	public void setUser(UserDTO user) {
		this.user = user;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}
	
	public String getPass(){
		return user.getPassword();
	}
	
	public void setPass(String pass){
		this.pass = pass;
	}
	
	public List<UserDTO> getListaUsuarios() {
		return listaUsuarios;
	}

	public void setListaUsuarios(List<UserDTO> listaUsuarios) {
		this.listaUsuarios = listaUsuarios;
	}

	public TaskBean getTasks() {
		return tasks;
	}

	public void setTasks(TaskBean tasks) {
		this.tasks = tasks;
	}

	public UserDTO getUser() {
		return user;
	}

	public List<UserStatusDTO> getEstados() {
		return estados;
	}

	public void setEstados(List<UserStatusDTO> estados) {
		this.estados = estados;
	}

	/**
	 * Este m�todo comprueba que los datos de inicio sean correctos y carga los
	 * datos necesarios para la vista principal (lista de tareas)
	 * 
	 * @return exito en caso de que todo fuera bien y fracaso en el contrario
	 */
	public String iniciarSesion() {
		if (user != null) {
			UserService us = Factories.services.createUserService();
			UserDTO userByLogin = null;
			try {
				userByLogin = us.loginUser(login, pass);
			} catch (BusinessException e) {
				e.printStackTrace();
			}
			if (userByLogin != null) {
				Log.info("El usuario [%s] ha iniciado sesi�n",
						user.getLogin());
				user = userByLogin;
				if(user.getIsAdmin()){
					return "administrador";
				}
				rellenarLista();
				tasks.setUsuario(user);
				return "usuario";
			}
		}
		return "fracaso";
	}
	
	public void inicializarBBDD(){
		//iniciar base de datos
		try {
			Factories.services.createUserService().resetBBDD();
		} catch (BusinessException e) {
			e.printStackTrace();
		}
	}
	
	public String listarUsuarios(){
		try {
			listaUsuarios = Factories.services.createUserService().listAll();
			return "exito";
		} catch (BusinessException e) {
			e.printStackTrace();
		}
		return "fracaso";
	}

	/**
	 * Crea la lista de las tareas del usuario
	 * 
	 * @return
	 */
	public String rellenarLista() {
		try {
			tasks.listar(user.getLogin());
			return "exito";
		} catch (NullPointerException e) {
			return "fracaso";
		}

	}

	public String bloquearUsuario(UserDTO user) {
		try {
			Factories.services.createUserService().blockUser(user);
			return "exito";
		} catch (BusinessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "fracaso";
		}
	}
	
	public String registraUsuario() {
		try {
			if(pass.equals(passRew)){
				user.setLogin(login);
				user.setPassword(pass);
				Factories.services.createUserService().addUser(user);
				user=new UserDTO();
				return "exito";
			}
			return "fracaso";
		} catch (BusinessException e) {
			e.printStackTrace();
			return "fracaso";
		}
	}
	
	public String desbloquearUsuario(UserDTO user) {
		try {
			Factories.services.createUserService().enableUser(user);
			return "exito";
		} catch (BusinessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "fracaso";
		}
	}

	/**
	 * Cierra la sesi�n de usuario actual y deja el bean listo para aceptar
	 * nuevos datos.
	 */
	public String cerrarSesion() {
		setUser(new UserDTO());
		pass = "";
		login = "";
		return "exito";
	}

	/**
	 * Introduce a la BD el usuario con los datos proporcionados y limpia los
	 * valores para que no se muestren despu�s en posteriores formularios si el
	 * mismo usuario quisiera crear varios usuarios.
	 * 
	 * @return exito si se introdujo adecuadamente y fracaso si hubo alg�n error
	 */
	public String crearUsuario() {
		try {
			Factories.services.createUserService().addUser(new UserDTO());
			setUser(new UserDTO());
			return "exito";
		} catch (Exception e) { 
			setUser(new UserDTO());
			return "fracaso";
		}
	}
	
	/**
	 * Método que registra un evento RowEdit y actualiza un viaje al modificarse su estado
	 * en dicho evento
	 * @param event
	 */
	public void actualizar(RowEditEvent event){
		UserDTO v = (UserDTO) event.getObject();
		try {
			Factories.services.createUserService().update(v);
		} catch (BusinessException e) {
			e.printStackTrace();
		}
	} 
	
	public void cancelarActualizar(RowEditEvent event) {

	}
	
	public String creacionTarea(){
		return "exito";
	}
	
	
}
