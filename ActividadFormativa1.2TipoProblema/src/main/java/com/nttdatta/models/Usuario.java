package com.nttdatta.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity  //representacion de la entidad modelo
@Table(name="USERS") //nombre de la tabla en DB
public class Usuario {
	@Id //este es el is o primary key de la tabla
	@GeneratedValue(strategy=GenerationType.IDENTITY) //es autoincrementable
	private Long id;
	private String nombre;
    private String apellido;
    private int limite;
    private int codigoPostal;
	
	public Usuario() {
		super();
	}
	
	public Usuario(String nombre, String apellido, int limite, int codigoPostal) {
		super();
		this.nombre = nombre;
		this.apellido = apellido;
		this.limite = limite;
		this.codigoPostal = codigoPostal;
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
	public int getLimite() {
		return limite;
	}
	public void setLimite(int limite) {
		this.limite = limite;
	}
	public int getCodigoPostal() {
		return codigoPostal;
	}
	public void setCodigoPostal(int codigoPostal) {
		this.codigoPostal = codigoPostal;
	}
	@Override
	public String toString() {
		return "Usuario [nombre=" + nombre + ", apellido=" + apellido + ", limite=" + limite + ", codigoPostal="
				+ codigoPostal + "]";
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
}
