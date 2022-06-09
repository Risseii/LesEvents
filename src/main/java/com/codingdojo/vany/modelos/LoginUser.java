package com.codingdojo.vany.modelos;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class LoginUser {
	
	@NotEmpty(message="Porfavor ingresar un correo electrónico")
	@Email(message="Ingrese un correo electronico valido")
	private String email;
	
	@NotEmpty(message="Favor de ingresar una contraseña")
	@Size(min=6,max=128,message="La contraseña debe de ser entre 6 y 128")
	private String password;

	public LoginUser() {
		
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
	
	
	

}
