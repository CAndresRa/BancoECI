package edu.eci.cvds.sampleprj.dao.mybatis.mappers;



import org.apache.ibatis.annotations.Param;

import edu.eci.cvds.samples.entities.Usuario;

import java.util.List;

/**
 *
 * @author 2106913
 */
public interface UsuarioMapper {

    public Usuario consultarUsuario(@Param("email") String correo);

    public List<Usuario> consultarUsuarios();

    public void  asignarRolUsuario(@Param("rol") String rol,@Param("usuario") Usuario usuario);

}