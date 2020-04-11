package edu.eci.cvds.sampleprj.dao;

import edu.eci.cvds.sampleprj.dao.PersistenceException;
import edu.eci.cvds.samples.entities.Iniciativa;
import edu.eci.cvds.samples.entities.Usuario;
import org.apache.ibatis.annotations.Param;

import java.util.Date;


public interface RegistroDAO {
    public void registrarIniciativaAUsuario(Date fecha_registro , Iniciativa iniciativa , Usuario usuario) throws PersistenceException;

}
