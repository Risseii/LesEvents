package com.codingdojo.vany.servicios;

import java.util.List;
import java.util.Optional;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import com.codingdojo.vany.modelos.Event;
import com.codingdojo.vany.modelos.LoginUser;
import com.codingdojo.vany.modelos.Message;
import com.codingdojo.vany.modelos.User;
import com.codingdojo.vany.repositorios.EventRepository;
import com.codingdojo.vany.repositorios.MessageRepository;
import com.codingdojo.vany.repositorios.UserRepository;

@Service
public class AppService {
	
	@Autowired
	private UserRepository repositorio_user;
	
	//agregar otros autowired para los demas repos
	@Autowired
	private EventRepository repositorio_event;
	
	@Autowired
	private MessageRepository repositorio_message;
	
	//registro
			public User register(User nuevoUsuario,BindingResult result) {
				
				String nuevoEmail = nuevoUsuario.getEmail();
				
				//revisar si el correo existe en la BD 
				if(repositorio_user.findByEmail(nuevoEmail).isPresent()) {
					result.rejectValue("email", "Unique","El correo fue ingresado previamente");
				}
				
				if(! nuevoUsuario.getPassword().equals(nuevoUsuario.getConfirm()) ) {
					result.rejectValue("confirm", "Matches","Las contraseñas no coinciden");
				}
				
				if(result.hasErrors()) {
					return null;
				} else {
					//encriptar la contraseña
					String contra_encr = BCrypt.hashpw(nuevoUsuario.getPassword(),BCrypt.gensalt());
					nuevoUsuario.setPassword(contra_encr);
					//guardo usuario
					return repositorio_user.save(nuevoUsuario);
				}
				
			}
			
			public User Login(LoginUser nuevoLogin, BindingResult result) {
				
				if(result.hasErrors()) {
					return null;
				}
				
				//buscamos por correo
				Optional<User> posibleUsuario = repositorio_user.findByEmail(nuevoLogin.getEmail());
				if(!posibleUsuario.isPresent()) {
					result.rejectValue("email", "Unique", "Correo ingresado no existe");
					return null;
				}
				
				User user_login = posibleUsuario.get(); 
				
				//comparacion contraseñas no encriptada con la encriptada
				if(! BCrypt.checkpw(nuevoLogin.getPassword(), user_login.getPassword())) {
					result.rejectValue("password","Matches","Contraseña inválida");
				}
				
				if(result.hasErrors()){
					return null;
				} else {
					return user_login;
				}
				
				
			}
			
			//save user en BD
			public User save_user(User updatedUser) {
				return repositorio_user.save(updatedUser);
			}
			
			//find user
			public User find_user(Long id) {
				Optional<User> optionalUser = repositorio_user.findById(id);
				if(optionalUser.isPresent()) {
					return optionalUser.get();
				} else {
					return null;
				}
			}
			
			//guarda evento en BD
			public Event save_event(Event thisEvent) {
				return repositorio_event.save(thisEvent);
			}
			
			//me muestre los eventos de mi estado
			public List<Event> eventos_estado(String state){
				return repositorio_event.findByState(state);
			}
			
			//me muestre los eventos de otros estados
			public List<Event> eventos_otros(String state){
				return repositorio_event.findByStateIsNot(state);
			}
			
			
			//find event
			public Event find_event(Long id) {
				Optional<Event> optionalEvent=repositorio_event.findById(id);
				if(optionalEvent.isPresent()) {
					return optionalEvent.get();
				} else {
					return null;
				}
			}
			
			
			//para que un usuario se une a un evento
			public void save_event_user(Long user_id,Long event_id) {
				Event myEvent = find_event(event_id); //se crea una instancia de evento
				User myUser = find_user(user_id); //se crea una instancia de user
				
				myUser.getEventsAttending().add(myEvent);
				repositorio_user.save(myUser);
			}
			
			//para eliminar un evento de un usuario
			public void remove_event_user(Long user_id,Long event_id) {
				Event myEvent = find_event(event_id); //se crea una instancia de evento
				User myUser = find_user(user_id); //se crea una instancia de user
				
				myUser.getEventsAttending().remove(myEvent);
				repositorio_user.save(myUser);
			}
			
			//funcion que guarde mensaje
			public Message save_message(Message thisMessage) {
				return repositorio_message.save(thisMessage);
			}
			
			//eliminar x el id
			public void delete_event(Long id) {
				repositorio_event.deleteById(id);
			}
			

}
