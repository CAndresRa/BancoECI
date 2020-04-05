package edu.eci.cvds.samples.services.impl;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import edu.eci.cvds.sampleprj.dao.*;
import edu.eci.cvds.samples.entities.Usuario;
import edu.eci.cvds.samples.services.ExcepcionServiciosBancoProyectos;
import edu.eci.cvds.samples.services.ServiciosBancoProyectos;

import java.util.List;

@Singleton
public class ServiciosBancoProyectosImpl implements ServiciosBancoProyectos {

    @Inject
    private UsuarioDAO usuarioDAO;

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
    public List<Usuario> consultarUsuarios() throws ExcepcionServiciosBancoProyectos {
        try {
            return usuarioDAO.consultarUsuarios();
        } catch (PersistenceException e){
            throw new ExcepcionServiciosBancoProyectos(e.getMessage(), e);
        }
    }

    @Override
    public Usuario consultarUsuario(String email) throws ExcepcionServiciosBancoProyectos {
        try {
            Usuario usuario = usuarioDAO.consultarUsuario(email);
            if(usuario == null){
                throw new ExcepcionServiciosBancoProyectos("El usuario no existe");
            }
            return usuarioDAO.consultarUsuario(email);
        } catch (PersistenceException e){
            throw new ExcepcionServiciosBancoProyectos(e.getMessage(), e);
        }
    }
}