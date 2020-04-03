package edu.eci.cvds.samples.services;

import edu.eci.cvds.samples.entities.Usuario;
import java.sql.Date;
import java.util.List;

public interface ServiciosBancoProyectos {

    public abstract Usuario consultarUsuario(String email) throws ExcepcionServiciosBancoProyectos;


}