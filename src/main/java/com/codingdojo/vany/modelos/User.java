package com.codingdojo.vany.modelos;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="users")
public class User {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotEmpty(message="¡Se requiere first name!")
    @Size(min=2, max=30, message="Username debe tener entre 2 y 30 caracteres")
    private String first_name;
    
    @NotEmpty(message="¡Se requiere last name!")
    @Size(min=2, max=30, message="Username debe tener entre 2 y 30 caracteres")
    private String last_name;
    
    @NotEmpty(message="¡Se requiere Email!")
    @Email(message="¡Ingrese un Email válido!")
    private String email;
    
    @NotEmpty(message="¡Se requiere contraseña!")
    @Size(min=6, max=128, message="La contraseña debe tener entre 6 y 128 caracteres")
    private String password;
    
    @Transient
    @NotEmpty(message="Confirm Password es obligatorio!")
    @Size(min=6, max=128, message="La confirmación debe tener entre 6 y 128 caracteres")
    private String confirm;
    
    
    @NotEmpty(message="Location es obligatorio!")
    private String location;
    
    
    @NotEmpty(message="El state es obligatorio!")
    private String state;
    
    @Column(updatable=false)
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date created_at;
	
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date updated_at;
	
	//relaciones
	//Uno a muchos
	@OneToMany(mappedBy="planner",fetch=FetchType.LAZY)
	private List<Event> eventsPlanned; //los eventos que cree
	
	//Muchos a muchos
	@ManyToMany(fetch= FetchType.LAZY)
	@JoinTable(name="users_has_events",
			   joinColumns = @JoinColumn(name="user_id"),
			   inverseJoinColumns = @JoinColumn(name="event_id"))
	
	private List<Event> eventsAttending; //los eventos a los que asisto
	
	//Uno a muchos
	@OneToMany(mappedBy="author",fetch=FetchType.LAZY)
	private List<Message> messages; //todos los mensajes que yo he publicado

	//constructor vacio
	public User() {
		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirst_name() {
		return first_name;
	}

	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}

	public String getLast_name() {
		return last_name;
	}

	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getConfirm() {
		return confirm;
	}

	public void setConfirm(String confirm) {
		this.confirm = confirm;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public List<Event> getEventsPlanned() {
		return eventsPlanned;
	}

	public void setEventsPlanned(List<Event> eventsPlanned) {
		this.eventsPlanned = eventsPlanned;
	}

	public List<Event> getEventsAttending() {
		return eventsAttending;
	}

	public void setEventsAttending(List<Event> eventsAttending) {
		this.eventsAttending = eventsAttending;
	}

	public List<Message> getMessages() {
		return messages;
	}

	public void setMessages(List<Message> messages) {
		this.messages = messages;
	}
	
	@PrePersist
	protected void onCreate() {
		this.created_at = new Date();
	}
	
	@PreUpdate
	protected void onUpdate() {
		this.updated_at = new Date();
	}
	
	

}
