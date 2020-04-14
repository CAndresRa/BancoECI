/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.cvds.samples.entities;

import java.io.Serializable;

public class Usuario implements Serializable{

    private long documento;
    private String email;
    private String nombre;
    private String apellido;
    private String password;
    private String rol;
    private String area;

    public Usuario() {}

    /**
     * Contructor de usuario
     * @param documento documento del usuario
     * @param email correo del usuario
     * @param nombre nombre del usuario
     * @param apellido apellido del usuario
     * @param password contraseña del usuario
     * @param rol rol del usuario
     * @param area Area a donde pertenece el usuario
     */
    public Usuario(long documento,  String email , String nombre ,  String apellido  , String password , String rol, String area) {
        this.documento=documento;
        this.email=email;
        this.nombre=nombre;
        this.apellido=apellido;
        this.password=password;
        this.rol=rol;
        this.area=area;
    }

    /**
     * Contructor de usuario
     * @param documento documento del usuario
     * @param email correo del usuario
     * @param nombre nombre del usuario
     * @param apellido apellido del usuario
     * @param password contraseña del usuario
     * @param rol rol del usuario
     */
    public Usuario(long documento,  String email , String nombre ,  String apellido  , String password , String rol ) {
        this.documento=documento;
        this.email=email;
        this.nombre=nombre;
        this.apellido=apellido;
        this.password=password;
        this.rol = rol;
    }

    /**
     * Contructor de usuario
     * @param documento documento del usuario
     * @param email correo del usuario
     * @param nombre nombre del usuario
     * @param apellido apellido del usuario
     * @param password contraseña del usuario
     */
    public Usuario(long documento,  String email , String nombre ,  String apellido  , String password ) {
        this.documento=documento;
        this.email=email;
        this.nombre=nombre;
        this.apellido=apellido;
        this.password=password;
    }

    public long getDocumento() {
        return documento;
    }

    public void setDocumento(long documento) {
        this.documento = documento;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public String getArea() { return area; }

    public void setArea(String area) { this.area = area; }

    @Override
    public String toString() {
        return "Usuario{" + "nombre=" + nombre + ", documento=" + documento + ", email= "+ email + ", rol= "+ rol + ", area= "+ area + '}';
    }
    
    @Override
    public boolean equals(Object o) {
    	Usuario user= (Usuario) o;
    	boolean flag= false;
    	if(user.getEmail().equals(getEmail())) {
    		if(user.getPassword().equals(getPassword())) {
        		flag= true;
        	}
    	}
		return flag;    	
    }
}