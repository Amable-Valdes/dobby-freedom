package uo.sdi.presentation;

import java.awt.event.ActionEvent;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.el.ELContext;
import javax.el.ExpressionFactory;
import javax.el.ValueExpression;
import javax.faces.application.Application;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import alb.util.log.Log;
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
	private String passRew = "";
	private String login = "";
	private String email = "";
	private boolean isAdmin = false;

	private UserDTO user = new UserDTO();
	private List<UserDTO> listaUsuarios = new ArrayList<UserDTO>();
	private boolean listaInbox = true;
	private boolean listaHoy = false;;
	private boolean listaSemana = false;;

	public UserBean() {

		iniciaUser(null);
	}
	
	@PostConstruct
	public void init(){
		if(tasks == null){
			FacesContext context = FacesContext.getCurrentInstance();
			ELContext contextoEL = context.getELContext();
			Application apli = context.getApplication();
			ExpressionFactory ef = apli.getExpressionFactory();
			ValueExpression ve = ef.createValueExpression(contextoEL,
					"#{tareas}", TaskBean.class);
			tasks = (TaskBean) ve.getValue(contextoEL);
		}
	}
	
	
	
	private Object putInSession(String key, Object value) {
		return FacesContext.getCurrentInstance().getExternalContext()
				.getSessionMap().put(key, value);
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

	public boolean isAdmin() {
		return isAdmin;
	}

	public void setAdmin(boolean isAdmin) {
		this.isAdmin = isAdmin;
	}

	public String getPassRew() {
		return passRew;
	}

	public void setPassRew(String passRew) {
		this.passRew = passRew;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPass() {
		return user.getPassword();
	}

	public void setPass(String pass) {
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
	
	public void errorLogin(){
		FacesContext.getCurrentInstance().addMessage(
				null, new FacesMessage(FacesMessage.SEVERITY_ERROR, 
						"login", "El usuario introducido es erroneo, "
								+ "intentelo de nuevo"));
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
				errorLogin();
				return "";
			}
			if (userByLogin != null) {
				Log.info("El usuario [%s] ha iniciado sesi�n", user.getLogin());
				user = userByLogin;
				putUserInSession(user);
				isAdmin = user.getIsAdmin();
				if (user.getIsAdmin()) {
					listarUsuarios();
					return "administrador";
				}
				rellenarLista();
				FacesContext.getCurrentInstance().getExternalContext()
				.getSessionMap().put("login", user.getLogin());
				putInSession("login", user.getLogin());
				return "usuario";
			}
		}
		return "fracaso";
	}

	private void putUserInSession(UserDTO user) {
		Map<String, Object> session = FacesContext
				.getCurrentInstance()
				.getExternalContext()
				.getSessionMap();
		session.put("LOGGEDIN_USER", user);
	}
	
	private void removeUserInSession(UserDTO user) {
		Map<String, Object> session = FacesContext
				.getCurrentInstance()
				.getExternalContext()
				.getSessionMap();
		session.remove("LOGGEDIN_USER");
	}

	public void inicializarBBDD() {
		// iniciar base de datos
		try {
			Factories.services.createUserService().resetBBDD();
			listarUsuarios();
		} catch (BusinessException e) {
			e.printStackTrace();
		}
	}

	public String listarUsuarios() {
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
	
	public void exitoRegistro(){
		FacesContext.getCurrentInstance().addMessage(
				null, new FacesMessage(FacesMessage.SEVERITY_INFO, 
						"Registro", "El usuario introducido ha sido registrado"));
	}
	
	public String registraUsuario() {
		try {
			if (pass.equals(passRew)) {
				user.setLogin(login);
				user.setPassword(pass);
				Factories.services.createUserService().addUser(user);
				user = new UserDTO();
				login = "";
				pass = "";
				exitoRegistro();
				return "exito";
			}
			return "fracaso";
		} catch (BusinessException e) {
			return "registro";
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

	public String eliminarUsuario(UserDTO u) {
		try {
			Factories.services.createUserService().removeUser(u.getLogin());
			listarUsuarios();
			return "exito";
		} catch (BusinessException e) {
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
		removeUserInSession(user);
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

	public void activarDesactivar(UserDTO u) {
		try {
			if (u.getStatus().equals(UserStatusDTO.ENABLED)) {
				Factories.services.createUserService().blockUser(u);
			} else {
				Factories.services.createUserService().enableUser(u);
			}
			listarUsuarios();
		} catch (BusinessException e) {
			e.printStackTrace();
		}
	}

	public String inbox() {
		listaInbox = true;
		listaHoy = false;
		listaSemana = false;
		tasks.listarTaskInbox(user);
		return "exito";
	}

	public String hoy() {
		listaInbox = false;
		listaHoy = true;
		listaSemana = false;
		tasks.listarTaskHoy(user);
		return "exito";
	}

	public String semana() {
		listaInbox = false;
		listaHoy = false;
		listaSemana = true;
		tasks.listarTasksSemana(user);
		return "exito";
	}

	public boolean isListaInbox() {
		return listaInbox;
	}

	public void setListaInbox(boolean listaInbox) {
		this.listaInbox = listaInbox;
	}

	public boolean isListaHoy() {
		return listaHoy;
	}

	public void setListaHoy(boolean listaHoy) {
		this.listaHoy = listaHoy;
	}

	public boolean isListaSemana() {
		return listaSemana;
	}

	public void setListaSemana(boolean listaSemana) {
		this.listaSemana = listaSemana;
	}

}
