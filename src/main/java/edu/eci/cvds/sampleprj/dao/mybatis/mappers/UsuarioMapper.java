package edu.eci.cvds.sampleprj.dao.mybatis.mappers;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import edu.eci.cvds.samples.entities.Usuario;

/**
 *
 * @author 2106913
 */
public interface UsuarioMapper {

    public Usuario consultarUsuario(@Param("email") String correo);
}