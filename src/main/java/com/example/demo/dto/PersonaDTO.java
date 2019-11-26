package com.example.demo.dto;

import java.io.Serializable;

import com.example.demo.entities.Domicilio;

public class PersonaDTO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private String nombre;
	private String apellido;
	private int dni;
	private int edad;
private String [] adn;
	private Domicilio domicilio;
	
	public PersonaDTO() {
		super();
	}


	public PersonaDTO(int id, String nombre, String apellido, int dni,int edad,String adn[]) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.apellido = apellido;
		this.dni = dni;
		this.edad=edad;
 this.adn=adn;
	}


	


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public String getApellido() {
		return apellido;
	}


	public void setApellido(String apellido) {
		this.apellido = apellido;
	}


	public int getDni() {
		return dni;
	}


	public void setDni(int dni) {
		this.dni = dni;
	}


	public int getEdad() {
		return edad;
	}


	public void setEdad(int edad) {
		this.edad = edad;
	}
	
	public Domicilio getDomicilio() {
		return domicilio;
	}


	public void setDomicilio(Domicilio domicilio) {
		this.domicilio = domicilio;
	}
	public String[] getAdn() {
		return adn;
	}




	public void setAdn(String[] adn) {
		this.adn = adn;
	}
}
