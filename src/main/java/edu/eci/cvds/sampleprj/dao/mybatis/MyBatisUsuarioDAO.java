package edu.eci.cvds.sampleprj.dao.mybatis;

import com.google.inject.Inject;
import edu.eci.cvds.sampleprj.dao.UsuarioDAO;
import edu.eci.cvds.samples.entities.Usuario;

import edu.eci.cvds.sampleprj.dao.mybatis.mappers.UsuarioMapper;
import org.mybatis.guice.transactional.Transactional;

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
    @Transactional
    @Override
    public void registrarUsuario(Usuario u) throws PersistenceException, edu.eci.cvds.sampleprj.dao.PersistenceException {
        try{
        	if(u.getRol().equals("Administrador") || u.getRol().equals("PMO") || u.getRol().equals("Publico") || u.getRol().equals("Proponente")) {
        		usuarioMapper.registrarUsuario(u);
            }
            else{
                throw new edu.eci.cvds.sampleprj.dao.PersistenceException("Rol invalido");
            }
        } catch (org.apache.ibatis.exceptions.PersistenceException e){
            throw new PersistenceException("Error al registrar al cliente" ,e);
        }
    }

    public Usuario consultarUsuario(String email) throws PersistenceException{
        try {
            Usuario usuario = usuarioMapper.consultarUsuario(email);
            /*if(usuario == null){
                throw new PersistenceException("El usuario no existe");
            }*/
            return usuario;
        } catch (PersistenceException e){
            /*throw new PersistenceException(e.getMessage(),e);*/
            throw new PersistenceException("El usuario no existe");
        }
    }
}