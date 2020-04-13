package edu.eci.cvds.samples.services.impl;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import edu.eci.cvds.sampleprj.dao.*;
import edu.eci.cvds.samples.entities.Iniciativa;
import edu.eci.cvds.samples.entities.Usuario;
import edu.eci.cvds.samples.services.ExcepcionServiciosBancoProyectos;
import edu.eci.cvds.samples.services.ServiciosBancoProyectos;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

@Singleton
public class ServiciosBancoProyectosImpl implements ServiciosBancoProyectos {


    @Inject
    private UsuarioDAO usuarioDAO;

    @Inject
    private IniciativaDAO iniciativaDAO;

    @Inject
    private RegistroDAO registroDAO;

    @Override
    public void registrarIniciativaAUsuario(Date fecha_registro, Iniciativa iniciativa, Usuario usuario , String[] palabras) throws ExcepcionServiciosBancoProyectos {
        try {
            if (iniciativa == null) {
                throw new ExcepcionServiciosBancoProyectos("La iniciativa no existe");
            }
            if (usuario == null) {
                throw new ExcepcionServiciosBancoProyectos("El usuario no existe");
            }
            if (fecha_registro == null) {
                throw new ExcepcionServiciosBancoProyectos("La fecha es nula");
            }
            iniciativaDAO.insertarIniciativa(iniciativa);
            agregarPalabrasClaveAIniciativa(iniciativa,palabras);
            registroDAO.registrarIniciativaAUsuario(fecha_registro, iniciativa, usuario);
        } catch (PersistenceException e) {
            throw new ExcepcionServiciosBancoProyectos(e.getMessage(), e);
        }
    }

    @Override
    public void insertarIniciativa(Iniciativa i) throws ExcepcionServiciosBancoProyectos, PersistenceException {
        try{
        	if(i.getDescripcion() == null) {
        		throw new ExcepcionServiciosBancoProyectos("La iniciativa no tiene toda la informacion necesaria");
        	}
            iniciativaDAO.insertarIniciativa(i);
            /*if(i == null){
                throw new ExcepcionServiciosBancoProyectos("La iniciativa no existe");
            }*/
        } catch (javax.persistence.PersistenceException e){
            throw new ExcepcionServiciosBancoProyectos(e.getMessage(), e);
        }
    }

    @Override
    public void agregarPalabrasClaveAIniciativa(Iniciativa iniciativa, String[] palabras) throws ExcepcionServiciosBancoProyectos {
        try{
            if(iniciativa == null){
                throw new ExcepcionServiciosBancoProyectos("La iniciativa no existe");
            }
            if(palabras == null || palabras.length == 0){
                throw new ExcepcionServiciosBancoProyectos("No hay palabras Clave");
            }
            for(int i=0 ; i < palabras.length ; i++){
                iniciativaDAO.agregarPalabraClaveAIniciativa(iniciativa , palabras[i]);
            }
        } catch (ExcepcionServiciosBancoProyectos | PersistenceException e){
            throw new ExcepcionServiciosBancoProyectos(e.getMessage(), e);
        }
    }

    @Override
    public void asignarRolUsuario(String rol, Usuario usuario) throws ExcepcionServiciosBancoProyectos {
        try {
            if(usuario == null){
                throw new ExcepcionServiciosBancoProyectos("El usuario no existe");
            }
            if(rol == null){
                throw new ExcepcionServiciosBancoProyectos("El rol es nulo");
            }
            usuarioDAO.asignarRolUsuario(rol,usuario);
        } catch (PersistenceException e){
            throw new ExcepcionServiciosBancoProyectos(e.getMessage(), e);
        }
    }

    @Override
    public List<Iniciativa> consultarIniciativas() throws ExcepcionServiciosBancoProyectos {
        try {
            return iniciativaDAO.consultarIniciativas();
        } catch (PersistenceException e){
            throw new ExcepcionServiciosBancoProyectos(e.getMessage(), e);
        }
    }

    @Override
    public Iniciativa consultarIniciativasPorId(int id) throws ExcepcionServiciosBancoProyectos {
        try{
            return  iniciativaDAO.consultarIniciativasPorId(id);
        }catch (PersistenceException e){
            throw new  ExcepcionServiciosBancoProyectos(e.getMessage(), e);
        }
    }

    @Override
    public void cambiarEstadoAiniciativa(String estado, Iniciativa iniciativa) throws ExcepcionServiciosBancoProyectos {
        try{
            iniciativaDAO.cambiarEstadoAiniciativa(estado,iniciativa);
        } catch (PersistenceException e){
            throw new ExcepcionServiciosBancoProyectos(e.getMessage(), e);
        }
    }

    @Override
    public List<Usuario> consultarUsuarios() throws ExcepcionServiciosBancoProyectos {
        try {
            return usuarioDAO.consultarUsuarios();
        } catch (PersistenceException e){
            throw new ExcepcionServiciosBancoProyectos(e.getMessage(), e);
        }
    }

    @Transactional
    @Override
    public void registrarUsuario(Usuario u) throws ExcepcionServiciosBancoProyectos {
        try{
            usuarioDAO.registrarUsuario(u);
        } catch (PersistenceException e) {
            throw new ExcepcionServiciosBancoProyectos("Rol invalido");
        }
    }

    @Override
    public Usuario consultarUsuario(String email) throws ExcepcionServiciosBancoProyectos {
        try {
            Usuario usuario = usuarioDAO.consultarUsuario(email);
            /*if(usuario == null){
                throw new ExcepcionServiciosBancoProyectos("El usuario no existe");
            }*/
            return usuarioDAO.consultarUsuario(email);
        } catch (PersistenceException e){
            /*throw new ExcepcionServiciosBancoProyectos(e.getMessage(), e);*/
            throw new ExcepcionServiciosBancoProyectos("El usuario no existe");
        }
    }
}