package edu.eci.cvds.sampleprj.dao.mybatis;

import com.google.inject.Inject;
import edu.eci.cvds.sampleprj.dao.UsuarioDAO;
import edu.eci.cvds.samples.entities.Usuario;

import edu.eci.cvds.sampleprj.dao.mybatis.mappers.UsuarioMapper;

import javax.persistence.PersistenceException;
import java.util.List;

public class MyBatisUsuarioDAO implements UsuarioDAO {
	@Inject
    private UsuarioMapper usuarioMapper;
	
    @Override
    public List<Usuario> consultarUsuarios() throws PersistenceException {
        try {
            return usuarioMapper.consultarUsuarios();
        } catch (PersistenceException e){
            throw new PersistenceException(e.getMessage(),e);
        }
    }

    @Override
    public void asignarRolUsuario(String rol, Usuario usuario) throws PersistenceException {
        try {
            if(usuario == null){
                throw new PersistenceException("El usuario no existe");
            }
            if(rol == null){
                throw new PersistenceException("El rol es nulo");
            }
            usuarioMapper.asignarRolUsuario(rol,usuario);
        } catch (PersistenceException e){
            throw new PersistenceException(e.getMessage(),e);
        }
    }

    public Usuario consultarUsuario(String email) throws PersistenceException{
        try {
            Usuario usuario = usuarioMapper.consultarUsuario(email);
            if(usuario == null){
                throw new PersistenceException("El usuario no existe");
            }
            return usuario;
        } catch (PersistenceException e){
            throw new PersistenceException(e.getMessage(),e);
        }
    }
}