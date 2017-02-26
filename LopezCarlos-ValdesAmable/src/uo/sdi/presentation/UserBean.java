package uo.sdi.presentation;

import java.awt.event.ActionEvent;
import java.io.Serializable;
import java.util.Date;

import alb.util.log.Log;
import uo.sdi.business.UserService;
import uo.sdi.dto.UserDTO;
import uo.sdi.dto.types.UserStatusDTO;
import uo.sdi.infrastructure.Factories;
import uo.sdi.model.User;
import uo.sdi.model.types.UserStatus;

@ManagedBean(name = "usuarios")
@SessionScoped
public class UserBean implements Serializable{

	private static final long serialVersionUID = 1L;

	@ManagedProperty("#{tareas}")
	private TaskBean tasks;
	
	private String pass = "";
	private String login = "";
	private UserDTO user = new UserDTO();

	public UserBean() {
		iniciaUser(null);
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

	/**
	 * Este método comprueba que los datos de inicio sean correctos y carga los
	 * datos necesarios para la vista principal (lista de tareas)
	 * 
	 * @return exito en caso de que todo fuera bien y fracaso en el contrario
	 */
	public String iniciarSesion() {
		if (user != null) {
			UserService us = Factories.services.createUserService();
			UserDTO userByLogin = us.findUser(new UserDTO());
			if (userByLogin != null && userByLogin.getPassword().equals(pass)) {
				Log.info("El usuario [%s] ha iniciado sesión",
						user.getLogin());
				user = userByLogin;
				us.iniciaSesion(user);
				rellenarLista();

				return "exito";
			}
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
			tasks.listar(user.getId());
			return "exito";
		} catch (NullPointerException e) {
			return "fracaso";
		}

	}

	/**
	 * Envía a la BD el objeto User de este bean que ya ha sido previamente
	 * modificado
	 */
	public void modificarUsuario() {
		Factories.services.createUserService().updateUser(new UserDTO());
		//Actualizar el usuario
		Factories.services.createUserService().updateUser(user);
	}

	/**
	 * Cierra la sesión de usuario actual y deja el bean listo para aceptar
	 * nuevos datos.
	 */
	public void cerrarSesion() {
		setUser(new UserDTO());
		pass = "";
		login = "";
		tasks.listaTodasTareas();
		//Se cierra sesion
		Factories.services.createUserService().singOut(null);
	}

	/**
	 * Introduce a la BD el viaje con los datos proporcionados
	 * 
	 * @return exito si se introdujo adecuadamente y fracaso si hubo algún error
	 */
	public String crearViaje() {
		try {
			User u = new User(user.getLogin());
			Factories.services.createTaskService().addTask(tasks.getTarea(), u);
			tasks.listar(user.getId());
			return "exito";
		} catch (Exception e) {
			return "fracaso";
		}
	}

	/**
	 * Introduce a la BD el usuario con los datos proporcionados y limpia los
	 * valores para que no se muestren después en posteriores formularios si el
	 * mismo usuario quisiera crear varios usuarios.
	 * 
	 * @return exito si se introdujo adecuadamente y fracaso si hubo algún error
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
}
