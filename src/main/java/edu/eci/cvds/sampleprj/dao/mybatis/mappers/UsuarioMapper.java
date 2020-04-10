package edu.eci.cvds.sampleprj.dao.mybatis.mappers;



import org.apache.ibatis.annotations.Param;

import edu.eci.cvds.samples.entities.Usuario;

import java.util.List;

/**
 *
 * @author 2106913
 */
public interface UsuarioMapper {

    /**
     * Metodo que permite consultar a un usuario dado el correo
     * @param correo Correo del usuario a consultar
     * @return Usuario cuyo correo es el ingresado
     */
    public Usuario consultarUsuario(@Param("email") String correo);

    /**
     * Metodo que permite consultar usuarios del sistema
     * @return Usuarios del sistema
     */
    public List<Usuario> consultarUsuarios();

    /**
     * Permite actualizar rol a un usuario
     * @param rol rol al que se desea actualizar
     * @param usuario usuario al que se le actualizara el rol
     */
    public void  asignarRolUsuario(@Param("rol") String rol,@Param("usuario") Usuario usuario);

    /**
     * Permite registrar un usuario al sistema
     * @param u Usuario a registrar
     */
    public void registrarUsuario(@Param("usuario") Usuario u);
}