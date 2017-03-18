package uo.sdi.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "TTASKS")
public class Task {

	//Variables de lógica
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String title;
	private String comments;
	@Temporal(value = TemporalType.TIMESTAMP)
	private Date created;
	@Temporal(value = TemporalType.TIMESTAMP)
	private Date planned;
	@Temporal(value = TemporalType.TIMESTAMP)
	private Date finished;
	
	@ManyToOne  
	private User user;
	@ManyToOne  
	private Category category;
	
	//Constructores
	Task(){}
	
	//Claves en Task: User (la tarea no puede existir sin usuario) y
	//la fecha de creación (que será new Timestamp, así que no se pide).
	public Task(User user, Date createdTime){
		this.created = createdTime;
		Association.Perform.link(user, this);
	}
	
	public Task(User user){
		this(user,new Date(System.currentTimeMillis()));
	}
	
	public Task(User user, String title){
		this(user);
		this.title = title;
	}
	
	public Task(User user, String title, Date createdTime){
		this(user,createdTime);
		this.title = title;
	}
	
	public Task(User user, Category category, String title){
		this(user, title);
		this.title = title;
		Association.Classifies.link(category, this);
	}

	//Gets y Sets
	public Long getId() {
		return id;
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

	public Date getCreated() {
		return created;
	}
	
	public void setCreated(Date created){
		this.created = created;
	}

	public Date getPlanned() {
		return planned;
	}

	public void setPlanned(Date planned) {
		this.planned = planned;
	}

	public Date getFinished() {
		return finished;
	}

	public void setFinished(Date finished) {
		this.finished = finished;
	}

	public User getUser() {
		return user;
	}

	public Category getCategory() {
		return category;
	}
	
	//Gets y Sets de relaciones
	void _setUser(User user) {
		this.user = user;
	}

	void _setCategory(Category category) {
		this.category = category;
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
		Task other = (Task) obj;
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

	public void setId(Long id) {
		this.id = id;
	}
}
