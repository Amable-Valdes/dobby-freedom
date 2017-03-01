package uo.sdi.persistence;

import java.util.Date;
import java.util.List;

import javax.persistence.NoResultException;

import uo.sdi.model.Task;
import uo.sdi.persistence.util.Jpa;

public class TaskFinder {

	public static List<Task> findAll() {
		return Jpa.getManager().createNamedQuery("Task.findAll", Task.class)
				.getResultList();
	}
	
	public static List<Task> findByLogin(String login) {
		return Jpa.getManager()
				.createNamedQuery("Task.findByUser", Task.class)
				.setParameter(1, login)
				.getResultList();
	}

	public static List<Task> findByUserAndCategoryActive(Long idUser,
			Long idCategory) {
		return Jpa.getManager()
				.createNamedQuery("Task.findByUserAndCategoryActive",
						Task.class)
				.setParameter(1, idUser)
				.setParameter(2, idCategory)
				.getResultList();
	}

	public static Task findByUser_Category_CreatedDate_TaskName(Long idUser,
			Long idCategory, Date created, String taskName) {
		try {
			return Jpa
					.getManager()
					.createNamedQuery(
							"Task.findByUser_Category_CreatedDate_TaskName",
							Task.class)
					.setParameter(1, idUser)
					.setParameter(2, idCategory).setParameter(3, created)
					.setParameter(4, taskName).getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}
	
	public static List<Task> findInbox(Long idUser) {
		return Jpa.getManager().createNamedQuery("Task.findInbox", Task.class)
				.setParameter(1, idUser)
				.getResultList();
	}

	public static List<Task> findToday(Long idUser) {
		return Jpa.getManager().createNamedQuery("Task.findToday", Task.class)
				.setParameter(1, idUser)
				//.setParameter(2, new Date())
				.getResultList();
	}

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
