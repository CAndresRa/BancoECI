package edu.eci.cvds.samples.entities;

import java.util.List;

public class Iniciativa {

    private int id;
    private String descripcion;
    private String estado;
    private String nombre;
    private List <PalabraClave> palabras_clave;

    public Iniciativa() {
    }

    public Iniciativa(int id, String nombre , String descripcion, String estado ) {
        this.id = id;
        this.descripcion = descripcion;
        this.nombre = nombre;
        this.estado = estado;
    }

    public Iniciativa( String nombre , String descripcion, String estado ) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.estado = estado;
        this.palabras_clave=palabras_clave;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public List <PalabraClave> getPalabras_clave() {
        return palabras_clave;
    }

    public void setPalabras_clave(List <PalabraClave> palabras_clave) {
        this.palabras_clave = palabras_clave;
    }

    @Override
    public String toString() {
        return "Iniciativa{" +
                "id=" + id +
                ", descripcion='" + descripcion + '\'' +
                ", estado='" + estado + '\'' +
                ", palabras_clave=" + palabras_clave +
                '}';
    }
}
