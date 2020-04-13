package edu.eci.cvds.sampleprj.dao;

import edu.eci.cvds.samples.entities.Usuario;

import java.util.List;

public interface UsuarioDAO {

    /**
     * Metodo que permite consultar un usuario dado su correo
     * @param email correo del usuario a consultar
     * @return Usuario cuyo correo sea el ingresado
     * @throws PersistenceException
     */
    public Usuario consultarUsuario(String email) throws PersistenceException;

    /**
     * Metodo que permite consultar todos los usuarios del sistema
     * @return Lista con usuarios del sistema
     * @throws PersistenceException
     */
    public List<Usuario> consultarUsuarios() throws PersistenceException;

    /**
     * Metodo que permite asignar rol a un usuario
     * @param rol rol que se desea asignar
     * @param usuario Usuario al que se le asignara el nuevo rol
     * @throws PersistenceException
     */
    public void  asignarRolUsuario(String rol, Usuario usuario) throws PersistenceException;

    /**
     * Metodo que permite registrar a un usuario
     * @param u Usuario al que se le asignara el nuevo rol
     * @throws PersistenceException
     */

    public void registrarUsuario(Usuario u) throws PersistenceException;

}