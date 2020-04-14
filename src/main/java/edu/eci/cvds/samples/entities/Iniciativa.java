package edu.eci.cvds.samples.entities;

import java.util.Date;
import java.util.List;

public class Iniciativa {

    private int id;
    private String nombre;
    private String descripcion;
    private String estado;
    private Usuario usuario;
    private Date fecha_registro;
    private List<PalabraClave> palabras_clave;

    public Iniciativa() {
    }

    public Iniciativa(int id, String nombre , String descripcion, String estado , Date fecha_registro , List<PalabraClave> palabras_clave) {
        this.id = id;
        this.descripcion = descripcion;
        this.nombre = nombre;
        this.estado = estado;
        this.fecha_registro = fecha_registro;
    }

    public Iniciativa( String nombre , String descripcion, String estado , Date fecha_registro , List<PalabraClave> palabras_clave , Usuario usuario) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.estado = estado;
        this.fecha_registro= fecha_registro;
        this.palabras_clave=palabras_clave;
        this.usuario = usuario;
    }

    public Iniciativa( String nombre , String descripcion, String estado , Date fecha_registro , Usuario usuario  ) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.estado = estado;
        this.fecha_registro = fecha_registro;
        this.usuario = usuario;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
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

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Date getFecha_registro() {
        return fecha_registro;
    }

    public void setFecha_registro(Date fecha_registro) {
        this.fecha_registro = fecha_registro;
    }

    public List<PalabraClave> getPalabras_clave() {
        return palabras_clave;
    }

    public void setPalabras_clave(List<PalabraClave> palabras_clave) {
        this.palabras_clave = palabras_clave;
    }

    @Override
    public String toString() {
        return "Iniciativa{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", estado='" + estado + '\'' +
                ", usuario=" + usuario +
                ", fecha_registro=" + fecha_registro +
                ", palabras_clave=" + palabras_clave +
                '}';
    }
}
