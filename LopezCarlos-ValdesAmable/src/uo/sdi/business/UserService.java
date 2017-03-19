package uo.sdi.business;

import java.util.List;

import uo.sdi.business.util.BusinessException;
import uo.sdi.dto.UserDTO;

/**
 * Esta interfaz proporciona servicios referidos a la administración de los 
 * usuarios del sistema.
 * 
 * @author Amable y Carlos
 *
 */
public interface UserService {

	/**
	 * Añade un nuevo usuario a la base de datos.
	 * 
	 * @param user	Un DTO con toda la información del nuevo usuario que se 
	 * añadirá al sistema
	 * @throws BusinessException	Si se cumple alguna irregularidad en la 
	 * lógica de negocio el sistema lanzará a capas superiores una excepción 
	 * de tipo BusinessException (Excepción de lógica de negocio).
	 */
	void addUser(UserDTO user) throws BusinessException;
	
	/**
	 * Elimina un usuario del sistema.
	 * 
	 * @param login	El login del usuario que se borrará.
	 * @throws BusinessException	Si se cumple alguna irregularidad en la 
	 * lógica de negocio el sistema lanzará a capas superiores una excepción 
	 * de tipo BusinessException (Excepción de lógica de negocio).
	 */
	void removeUser(String login) throws BusinessException;
	
	/**
	 * Busca un usuario en el sistema.
	 * 
	 * @param login	El login del usuario a buscar.
	 * @throws BusinessException	Si se cumple alguna irregularidad en la 
	 * lógica de negocio el sistema lanzará a capas superiores una excepción 
	 * de tipo BusinessException (Excepción de lógica de negocio).
	 */
	UserDTO findUser(String login) throws BusinessException;
	
	/**
	 * Cambia el estado del usuario de enable a disable.
	 * @param user	DTO con la información del usuario a bloquear.
	 * @throws BusinessException	Si se cumple alguna irregularidad en la 
	 * lógica de negocio el sistema lanzará a capas superiores una excepción 
	 * de tipo BusinessException (Excepción de lógica de negocio).
	 */
	void blockUser(UserDTO user) throws BusinessException;
	
	/**
	 * Cambia el estado del usuario de disabled a enable.
	 * 
	 * @param user	DTO con la información del usuario a bloquear.
	 * @throws BusinessException	Si se cumple alguna irregularidad en la 
	 * lógica de negocio el sistema lanzará a capas superiores una excepción 
	 * de tipo BusinessException (Excepción de lógica de negocio).
	 */
	void enableUser(UserDTO user) throws BusinessException;
	
	/**
	 * Servicio que permite comprobar que todos los datos están en orden para 
	 * loguearse en el sistema.
	 * 
	 * @param login	El login del usuario que se va a loguear.
	 * @param password	El password que el usuario ha introducido para 
	 * loguearse en el sistema y que se comparará con el password 
	 * guardado en el sistema
	 * @return	Un UserDTO que tenga todos los datos del usuario 
	 * que se ha logueado
	 * @throws BusinessException	Si se cumple alguna irregularidad en la 
	 * lógica de negocio el sistema lanzará a capas superiores una excepción 
	 * de tipo BusinessException (Excepción de lógica de negocio).
	 */
	UserDTO loginUser(String login, String password) throws BusinessException;
	
	/**
	 * Método que permite al administrador del sistema resetear la BBDD con
	 * datos por defecto.
	 * 
	 * @throws BusinessException	Si se cumple alguna irregularidad en la 
	 * lógica de negocio el sistema lanzará a capas superiores una excepción 
	 * de tipo BusinessException (Excepción de lógica de negocio).
	 */
	void resetBBDD() throws BusinessException;
	
	/**
	 * Lista al administrador todos los usuarios del sistema (excepto otros 
	 * administradores).
	 * 
	 * @return	Una lista de UserDTO con todos los usuarios del sistema 
	 * (excepto otros administradores).
	 * @throws BusinessException	Si se cumple alguna irregularidad en la 
	 * lógica de negocio el sistema lanzará a capas superiores una excepción 
	 * de tipo BusinessException (Excepción de lógica de negocio).
	 */
	List<UserDTO> listAll() throws BusinessException;
}
