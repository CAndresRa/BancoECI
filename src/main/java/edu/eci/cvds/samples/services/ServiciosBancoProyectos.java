package edu.eci.cvds.samples.services;

import edu.eci.cvds.sampleprj.dao.PersistenceException;
import edu.eci.cvds.samples.entities.Iniciativa;
import edu.eci.cvds.samples.entities.Usuario;

import java.util.Date;
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



    public void  insertarIniciativa(Iniciativa i) throws ExcepcionServiciosBancoProyectos;

    public void agregarPalabrasClaveAIniciativa(Iniciativa iniciativa , String[] palabras) throws ExcepcionServiciosBancoProyectos;

    /**
     * Permite registrar a un usuario
     * @param u Usuario que se va a registrar
     * @throws ExcepcionServiciosBancoProyectos
     */
    public abstract void registrarUsuario(Usuario u) throws ExcepcionServiciosBancoProyectos;

    public abstract void registrarIniciativaAUsuario(Date fecha_registro , Iniciativa iniciativa , Usuario usuario , String[] palabras) throws ExcepcionServiciosBancoProyectos;

    public abstract void asignarRolUsuario(String rol , Usuario usuario) throws ExcepcionServiciosBancoProyectos;
}