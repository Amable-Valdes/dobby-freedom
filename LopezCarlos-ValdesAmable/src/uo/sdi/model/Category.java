package uo.sdi.model;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "TCATEGORIES")
public class Category {

	//Variables de lógica
	@Id @GeneratedValue
	private Long id;
	private String name;
	@Temporal(value = TemporalType.TIMESTAMP)
	private Date created;
	
	@ManyToOne
	private User user;
	@OneToMany(mappedBy = "category") 
	private Set<Task> tasks = new HashSet<Task>();
	
	//Constructores
	Category(){}
	
	//Claves de Category: par de valores User y fecha de creación
	public Category(User user, Date created){
		this.created = new Timestamp(System.currentTimeMillis());
		Association.Organizes.link(user, this);
	}
	
	public Category(User user){
		this(user,new Timestamp(System.currentTimeMillis()));
	}
	
	public Category(User user, String name){
		this(user);
		this.name = name;
	}
	
	//Gets y Sets
	public Long getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public User getUser() {
		return user;
	}
	
	//Gets y Sets relaciones
	public Set<Task> getTasks() {
		return new HashSet<Task>(tasks);
	}

	Set<Task> _getTasks() {
		return tasks;
	}

	void _setUser(User user) {
		this.user = user;
	}

	//Hashcode y Equals
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((created == null) ? 0 : created.hashCode());
		result = prime * result + ((user == null) ? 0 : user.hashCode());
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
		Category other = (Category) obj;
		if (created == null) {
			if (other.created != null)
				return false;
		} else if (!created.equals(other.created))
			return false;
		if (user == null) {
			if (other.user != null)
				return false;
		} else if (!user.equals(other.user))
			return false;
		return true;
	}
}
