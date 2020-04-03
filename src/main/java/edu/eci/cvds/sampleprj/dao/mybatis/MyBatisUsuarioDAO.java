package edu.eci.cvds.sampleprj.dao.mybatis;

import com.google.inject.Inject;
import edu.eci.cvds.sampleprj.dao.UsuarioDAO;
import edu.eci.cvds.samples.entities.Usuario;
import org.mybatis.guice.transactional.Transactional;
import edu.eci.cvds.sampleprj.dao.mybatis.mappers.UsuarioMapper;

import javax.persistence.PersistenceException;
import java.util.Date;
import java.util.List;

public class MyBatisUsuarioDAO implements UsuarioDAO {

    @Inject
    private UsuarioMapper usuarioMapper;

    public Usuario consultarUsuario(String email) throws PersistenceException{
        try {
            return usuarioMapper.consultarUsuario(email);
        } catch (PersistenceException e){
            throw new PersistenceException("Error al consultar usuario con email" + email ,e);
        }
    }
}