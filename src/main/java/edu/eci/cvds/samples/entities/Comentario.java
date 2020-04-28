package edu.eci.cvds.samples.entities;

import java.util.Date;

public class Comentario {

    private String contenido;
    private Date fecha_comentario;
    private String correo_usuario;
    private String nombre_usuario;
    private String apellido_usuario;

    public Comentario(){
    }

    public Comentario(String contenido , Date fecha_comentario , String correo_usuario , String nombre_usuario , String apellido_usuario){
        this.contenido = contenido;
        this.fecha_comentario = fecha_comentario;
        this.nombre_usuario = nombre_usuario;
        this.apellido_usuario = apellido_usuario;
        this.correo_usuario = correo_usuario;
    }

    public String getCorreo_usuario() {
        return correo_usuario;
    }

    public void setCorreo_usuario(String correo_usuario) {
        this.correo_usuario = correo_usuario;
    }

    public String getNombre_usuario() {
        return nombre_usuario;
    }

    public void setNombre_usuario(String nombre_usuario) {
        this.nombre_usuario = nombre_usuario;
    }

    public String getApellido_usuario() {
        return apellido_usuario;
    }

    public void setApellido_usuario(String apellido_usuario) {
        this.apellido_usuario = apellido_usuario;
    }

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    public Date getFecha_comentario() {
        return fecha_comentario;
    }

    public void setFecha_comentario(Date fecha_comentario) {
        this.fecha_comentario = fecha_comentario;
    }

    @Override
    public String toString() {
        return "Comentario{" +
                "contenido='" + contenido + '\'' +
                ", fecha_comentario=" + fecha_comentario +
                ", correo_usuario='" + correo_usuario + '\'' +
                ", nombre_usuario='" + nombre_usuario + '\'' +
                ", apellido_usuario='" + apellido_usuario + '\'' +
                '}';
    }
}
