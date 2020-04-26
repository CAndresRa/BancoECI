package edu.eci.cvds.samples.entities;

import java.util.Date;

public class Comentario {
    private String contenido;
    private Date fecha_comentario;
    private Usuario usuario;

    public Comentario(){
    }

    public Comentario(String contenido , Date fecha_comentario , Usuario usuario){
        this.contenido = contenido;
        this.fecha_comentario = fecha_comentario;
        this.usuario = usuario;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
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
                ", usuario=" + usuario +
                '}';
    }
}
