package uo.sdi.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import uo.sdi.model.types.UserStatus;

@Entity
@Table(name = "TUSERS")
public class User {

	//Variables de lógica
	@Id @GeneratedValue
	private Long id;
	private String email;
	private Boolean isAdmin;
	private String login;
	private String password;
	private UserStatus status;
	
	@OneToMany(mappedBy = "user") 
	private Set<Task> tasks = new HashSet<Task>();
	@OneToMany(mappedBy = "user") 
	private Set<Category> categories = new HashSet<Category>();
	
	//Constructores
	User(){}
	
	//Claves en User: login (email "al parecer" se puede repetir en el sistema)
	public User(String login){
		this.login = login;
	}
	
	//Gets y sets
	public Long getId() {
		return id;
	}

	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public Boolean getIsAdmin() {
		return isAdmin;
	}
	
	public void setIsAdmin(Boolean isAdmin) {
		this.isAdmin = isAdmin;
	}
	
	public String getLogin() {
		return login;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public void setLogin(String login){
		this.login = login;
	}
	
	
	
	public void setId(Long id) {
		this.id = id;
	}

	public void setTasks(Set<Task> tasks) {
		this.tasks = tasks;
	}

	public UserStatus getStatus() {
		return status;
	}
	
	public void setStatus(UserStatus status) {
		this.status = status;
	}
	
	//Gets y Sets de relaciones
	public Set<Task> getTasks() {
		return new HashSet<Task>(tasks);
	}

	Set<Task> _getTasks() {
		return tasks;
	}
	
	public Set<Category> getCategories() {
		return new HashSet<Category>(categories);
	}

	Set<Category> _getCategories() {
		return categories;
	}
	
	//Hashcode y Equals
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((login == null) ? 0 : login.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (login == null) {
			if (other.login != null)
				return false;
		} else if (!login.equals(other.login))
			return false;
		return true;
	}
}
