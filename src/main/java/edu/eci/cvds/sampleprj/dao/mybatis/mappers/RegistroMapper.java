package edu.eci.cvds.sampleprj.dao.mybatis.mappers;

import edu.eci.cvds.samples.entities.Iniciativa;
import edu.eci.cvds.samples.entities.Usuario;
import org.apache.ibatis.annotations.Param;

import java.util.Date;

public interface RegistroMapper {

    public void registrarIniciativaAUsuario(@Param("fecha_registro") Date fecha_registro , @Param("iniciativa") Iniciativa i , @Param("usuario") Usuario u );

}
