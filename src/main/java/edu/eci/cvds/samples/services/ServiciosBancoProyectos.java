package edu.eci.cvds.samples.services;

import edu.eci.cvds.sampleprj.dao.PersistenceException;
import edu.eci.cvds.samples.entities.Iniciativa;
import edu.eci.cvds.samples.entities.Usuario;

import java.util.List;

public interface ServiciosBancoProyectos {

    /**
     * Permite consultar a un usuario dado su correo
     * @param email correo del usuario que se desea consultar
     * @return Usuario cuyo correo sea el ingresado
     * @throws ExcepcionServiciosBancoProyectos
     */
    public abstract Usuario consultarUsuario(String email) throws ExcepcionServiciosBancoProyectos;

    /**
     * Permite consultar a todos los usuarios
     * @return Lista con los usuarios del sistema
     * @throws ExcepcionServiciosBancoProyectos
     */
    public abstract List<Usuario> consultarUsuarios() throws ExcepcionServiciosBancoProyectos;

    /**
     * Permite insertar una iniciativa
     * @param iniciativa Iniciativa a insertar
     * @param palabras palabras clave de la iniciativa
     * @throws ExcepcionServiciosBancoProyectos
     * @throws PersistenceException
     */

    public void  insertarIniciativa(Iniciativa iniciativa , List<String> palabras) throws ExcepcionServiciosBancoProyectos, PersistenceException;

    /**
     * Permite agregar palabras clave a una iniciativa
     * @param iniciativa Iniciativa a la cual se le van a asignar las palabras clave
     * @param palabras palabras clave de la iniciativa
     * @throws ExcepcionServiciosBancoProyectos
     * @throws PersistenceException
     */

    public void agregarPalabrasClaveAIniciativa(Iniciativa iniciativa , List<String> palabras) throws ExcepcionServiciosBancoProyectos;

    /**
     * Permite registrar a un usuario
     * @param u Usuario que se va a registrar
     * @throws ExcepcionServiciosBancoProyectos
     */

    public abstract void registrarUsuario(Usuario u) throws ExcepcionServiciosBancoProyectos;

    /**
     * Permite asignar un rol a un usuario
     * @param usuario Usuario al que se le va a asignar al rol
     * @param rol que se va a asignar al usuario
     * @throws ExcepcionServiciosBancoProyectos
     */

    public abstract void asignarRolUsuario(String rol , Usuario usuario) throws ExcepcionServiciosBancoProyectos;

    /**
     * Permite consultar todas las iniciativas
     * @return Lista con las iniciativas del sistema
     * @throws ExcepcionServiciosBancoProyectos
     */

    public abstract List<Iniciativa> consultarIniciativas() throws ExcepcionServiciosBancoProyectos;

    /**
     * Permite consultar una iniciativa por su id
     * @param  id id de la iniciativa a consultar
     * @return Iniciativa que coincida con el id ingresado como parametro
     * @throws ExcepcionServiciosBancoProyectos
     */

    public abstract Iniciativa consultarIniciativasPorId(int id) throws ExcepcionServiciosBancoProyectos;

    /**
     * Permite cambiar el estado de una iniciativa
     * @param  estado de la iniciativa a asignar
     * @param  iniciativa a la cual se le va asignar el nuevo estado
     * @throws ExcepcionServiciosBancoProyectos
     */

    public abstract void cambiarEstadoAiniciativa(String estado, Iniciativa iniciativa) throws  ExcepcionServiciosBancoProyectos;
}