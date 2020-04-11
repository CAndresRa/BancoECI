package edu.eci.cvds.samples.entities;

import java.util.List;

public class PalabraClave {
    private int id;
    private String palabra;

    public PalabraClave() {
    }

    public PalabraClave(int id,   String palabra) {
        this.id = id;
        this.palabra = palabra;
    }

    public PalabraClave( String palabra) {
        this.palabra = palabra;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public String getPalabra() {
        return palabra;
    }

    public void setPalabra(String palabra) {
        this.palabra = palabra;
    }

    @Override
    public String toString() {
        return "PalabraClave{" +
                "id=" + id +
                ", palabra='" + palabra + '\'' +
                '}';
    }

}
