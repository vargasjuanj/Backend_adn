package com.example.demo.entities;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

@Entity
public class Persona implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
(strategy = GenerationType.IDENTITY)
	private int id;
	@NotNull
	private String nombre;
	@NotNull
	private String apellido;
	@NotNull
	private int dni;
	@NotNull
	private int edad;
	@NotNull
	private String  adn;
	@NotNull
	//Para que me funcione con Angular, tuvé que usar el cascade.ALL
	@OneToOne(cascade=CascadeType.ALL, orphanRemoval = true) //El orphanRemoval=true lo usamos para que no queden direcciones sin personas
	@JoinColumn(name="persona_domicilio")
	private Domicilio domicilio;
	
	




	public Persona() {
		super();
	}




	public Persona(String nombre, String apellido, int dni,int edad, String adn) {
	
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
	public String getAdn() {
		return adn;
	}




	public void setAdn(String adn) {
		this.adn = adn;
	}






}
