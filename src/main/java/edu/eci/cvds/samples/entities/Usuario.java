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

    public Usuario() {
    }

    public Usuario(long documento,  String email , String nombre ,  String apellido  , String password , String rol ) {
        this.documento=documento;
        this.email=email;
        this.nombre=nombre;
        this.apellido=apellido;
        this.password=password;
        this.rol=rol;
    }

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

    @Override
    public String toString() {
        return "Usuario{" + "nombre=" + nombre + ", documento=" + documento + ", email= "+ email + ", rol= "+ rol + '}';
    }




}