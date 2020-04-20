package edu.eci.cvds.samples.services.impl;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import edu.eci.cvds.sampleprj.dao.IniciativaDAO;
import edu.eci.cvds.sampleprj.dao.PersistenceException;
import edu.eci.cvds.sampleprj.dao.UsuarioDAO;
import edu.eci.cvds.samples.entities.Usuario;
import edu.eci.cvds.samples.services.ExcepcionServiciosBancoProyectos;
import edu.eci.cvds.samples.services.ServiciosUsuario;

import javax.transaction.Transactional;
import java.util.List;

@Singleton
public class ServiciosUsuarioImpl implements ServiciosUsuario {
    @Inject
    private UsuarioDAO usuarioDAO;

    @Inject
    private IniciativaDAO iniciativaDAO;


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
            return usuarioDAO.consultarUsuario(email);
        } catch (PersistenceException e){
            throw new ExcepcionServiciosBancoProyectos("El usuario no existe");
        }
    }
}
