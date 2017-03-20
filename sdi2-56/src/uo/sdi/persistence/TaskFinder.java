package uo.sdi.persistence;

import java.util.Date;
import java.util.List;

import javax.persistence.NoResultException;

import uo.sdi.model.Task;
import uo.sdi.persistence.util.Jpa;

/**
 * Clase TaskFinder que permite realizar consultas sobre la BBDD y 
 * devuelve objetos Task ya persistentes.
 * 
 * @author Amable y Carlos
 *
 */
public class TaskFinder {

	/**
	 * Lista todas las Task presentes en la BBDD.
	 * 
	 * @return	Una lista con todas las Task de la BBDD.
	 */
	public static List<Task> findAll() {
		return Jpa.getManager().createNamedQuery("Task.findAll", Task.class)
				.getResultList();
	}
	
	/**
	 * Lista todas las Task de un usuario que tenga el login identico al 
	 * pasado como parámetro.
	 * 
	 * @param login	El login del usuario del que queremos saber las tareas.
	 * @return	Una lista de Task del usuario pasado como parámetro.
	 */
	public static List<Task> findByLogin(String login) {
		return Jpa.getManager()
				.createNamedQuery("Task.findByLogin", Task.class)
				.setParameter(1, login)
				.getResultList();
	}//TODO Esta es la que nos pide De La cal al principio cuando se loguea el usuario

	/**
	 * Lista todas las Task de un usuario con id identico al pasado como 
	 * parámetro y con categoría con id identica a la pasada como parámetro.
	 * 
	 * @param idUser	Id del User del que queremos saber las Task.
	 * @param idCategory	Id del Category del que queremos saber las Task.
	 * @return	Una lista de Task del User y Category pasado como parámetros.
	 */
	public static List<Task> findByUser_And_Category(Long idUser,
			Long idCategory) {
		return Jpa.getManager()
				.createNamedQuery("Task.findByUser_Category",
						Task.class)
				.setParameter(1, idUser)
				.setParameter(2, idCategory)
				.getResultList();
	}

	//TODO Comentar si funciona
	public static Task findByUser_And_CreatedDate(Long idUser, Date created) {
		try {
			return Jpa
					.getManager()
					.createNamedQuery("Task.findByUser_CreatedDate",Task.class)
					.setParameter(1, idUser)
					.setParameter(3, created)
					.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}
	
	
	/**
	 * Lista todas las tareas de un User (pasado como parámetro) para la
	 * pseudolista Inbox.
	 * 
	 * @param idUser	El id del User del que queremos saber las Task.
	 * @return	Una lista de Task del User para la pseudolista Inbox.
	 */
	public static List<Task> findInbox(Long idUser) {
		return Jpa.getManager().createNamedQuery("Task.findInbox", Task.class)
				.setParameter(1, idUser)
				.getResultList();
	}
	
	/**
	 * Lista todas las tareas de un User (pasado como parámetro) para la
	 * pseudolista Inbox PERO solo aquellas finalizadas.
	 * 
	 * @param idUser	El id del User del que queremos saber las Task.
	 * @return	Una lista de Task del User para la pseudolista Inbox.
	 */
	public static List<Task> findFinished(Long idUser) {
		return Jpa.getManager().createNamedQuery("Task.findFinished", 
				Task.class)
				.setParameter(1, idUser)
				.getResultList();
	}

	/**
	 * Lista todas las tareas de un User (pasado como parámetro) que tengan el 
	 * valor de planned (un Date) entre los dos parámetros dateBegin y dateEnd.
	 * 
	 * @param idUser	El id del User del que queremos saber las Task.
	 * @param dateBegin	Fecha mínima para aparecer en la lista.
	 * @param dateEnd	Fecha máxima para aparecer en la lista.
	 * @return	Una lista de Tasks cuya fecha de planned esté entre dateBegin 
	 * y dateEnd.
	 */
	public static List<Task> findBetween(Long idUser, Date dateBegin, 
			Date dateEnd) {
		return Jpa.getManager().createNamedQuery("Task.findBetween", 
				Task.class)
			.setParameter(1, idUser)
			.setParameter(2, dateBegin)
			.setParameter(3, dateEnd)
			.getResultList();
	}

}
