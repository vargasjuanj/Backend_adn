package com.example.demo.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Entity(name="domicilio")
public class Domicilio implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue
(strategy = GenerationType.IDENTITY)
	private int id;
	@NotNull
	@Column(name="domicilio_calle")
	
	private String calle;
	@NotNull
	@Column(name="domicilio_numero")
	private int numero;
	public Domicilio() {
		super();
	}
	public Domicilio(int id, String calle, int numero) {
		super();
		this.id = id;
		this.calle = calle;
		this.numero = numero;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCalle() {
		return calle;
	}
	public void setCalle(String calle) {
		this.calle = calle;
	}
	public int getNumero() {
		return numero;
	}
	public void setNumero(int numero) {
		this.numero = numero;
	}
	
	
}
