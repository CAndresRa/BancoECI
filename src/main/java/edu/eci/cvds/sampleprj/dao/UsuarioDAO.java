package edu.eci.cvds.sampleprj.dao;

import edu.eci.cvds.samples.entities.Usuario;

import java.util.List;

public interface UsuarioDAO {

    public Usuario consultarUsuario(String email) throws PersistenceException;
}