package uo.sdi.persistence;

import java.util.List;

import uo.sdi.model.Task;
import uo.sdi.persistence.util.Jpa;

public class TaskFinder {

	public static List<Task> findAll() {
		return Jpa.getManager()
				.createNamedQuery("Task.findAll", Task.class)
				.getResultList();
	}

}
