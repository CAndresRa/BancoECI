package edu.eci.cvds.samples.services;

public class ExcepcionServiciosBancoProyectos extends Exception {
    public ExcepcionServiciosBancoProyectos(String mensaje,Exception e){
        super(mensaje,e);
    }
    public ExcepcionServiciosBancoProyectos(String mensaje){
        super(mensaje);
    }
}