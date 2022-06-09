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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="events")
public class Event {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	@NotEmpty(message="¡Se requiere name!")
    @Size(min=2, max=30, message="El campo de name debe tener entre 2 y 30 caracteres")
	private String name;
	
	@Future
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date eventDate;
	
	@NotEmpty(message="¡El campo de location es obligatorio!")
	private String location;
	
	@NotEmpty(message="¡El campo de state es obligatorio!")
	private String state;
	
	@Column(updatable=false)
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date created_at;
	
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date updated_at;
	
	//Manytoone
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="user_id")
	private User planner; //debe coincidir con el mappedby de user
	
	//OnetoMany
	@OneToMany(mappedBy="event",fetch=FetchType.LAZY)
	private List<Message> messages;
	
	//Many2many
	@ManyToMany(fetch=FetchType.LAZY)
	@JoinTable(name="users_has_events",
	   joinColumns = @JoinColumn(name="event_id"),
	   inverseJoinColumns = @JoinColumn(name="user_id"))
	private List<User> attendees;

	
	//constructor
	public Event() {
		
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public Date getEventDate() {
		return eventDate;
	}


	public void setEventDate(Date eventDate) {
		this.eventDate = eventDate;
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


	public User getPlanner() {
		return planner;
	}


	public void setPlanner(User planner) {
		this.planner = planner;
	}


	public List<Message> getMessages() {
		return messages;
	}


	public void setMessages(List<Message> messages) {
		this.messages = messages;
	}


	public List<User> getAttendees() {
		return attendees;
	}


	public void setAttendees(List<User> attendees) {
		this.attendees = attendees;
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
