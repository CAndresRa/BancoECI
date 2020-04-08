package edu.eci.cvds.sampleprj.dao;

import edu.eci.cvds.samples.entities.Usuario;

import java.util.List;

public interface UsuarioDAO {

    public Usuario consultarUsuario(String email) throws PersistenceException;

    public List<Usuario> consultarUsuarios() throws PersistenceException;

    public void  asignarRolUsuario(String rol, Usuario usuario) throws PersistenceException;

    public void registrarUsuario(Usuario u) throws PersistenceException;

}