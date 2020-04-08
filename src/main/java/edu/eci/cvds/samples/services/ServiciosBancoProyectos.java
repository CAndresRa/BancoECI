package edu.eci.cvds.samples.services;

import edu.eci.cvds.samples.entities.Usuario;
import java.util.List;

public interface ServiciosBancoProyectos {

    public abstract Usuario consultarUsuario(String email) throws ExcepcionServiciosBancoProyectos;

    public abstract List<Usuario> consultarUsuarios() throws ExcepcionServiciosBancoProyectos;

    public abstract void registrarUsuario(Usuario u) throws ExcepcionServiciosBancoProyectos;

    public abstract void asignarRolUsuario(String rol , Usuario usuario) throws ExcepcionServiciosBancoProyectos;
}