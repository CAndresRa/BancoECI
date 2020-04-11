package edu.eci.cvds.samples.entities;

import java.util.Date;

public class Registro {

    private int id;
    private Date fecha_registro;
    private Usuario usuario;
    private Iniciativa iniciativa;


    public Registro() {
    }

    public Registro (Date fecha_registro , Usuario usuario , Iniciativa iniciativa){
        this.fecha_registro = fecha_registro;
        this.usuario = usuario;
        this.iniciativa = iniciativa;
    }

    public Registro (int id , Date fecha_registro , Usuario usuario , Iniciativa iniciativa){
        this.id = id;
        this.fecha_registro = fecha_registro;
        this.usuario = usuario;
        this.iniciativa = iniciativa;
    }


    public Date getFecha_registro() {
        return fecha_registro;
    }

    public void setFecha_registro(Date fecha_registro) {
        this.fecha_registro = fecha_registro;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Iniciativa getIniciativa() {
        return iniciativa;
    }

    public void setIniciativa(Iniciativa iniciativa) {
        this.iniciativa = iniciativa;
    }


    @Override
    public String toString() {
        return "Registro{" +
                "fecha_registro=" + fecha_registro +
                ", usuario=" + usuario +
                ", iniciativa=" + iniciativa +
                '}';
    }
}