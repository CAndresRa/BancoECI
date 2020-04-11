package edu.eci.cvds.sampleprj.dao.mybatis;

import com.google.inject.Inject;
import edu.eci.cvds.sampleprj.dao.PersistenceException;
import edu.eci.cvds.sampleprj.dao.RegistroDAO;
import edu.eci.cvds.sampleprj.dao.mybatis.mappers.RegistroMapper;

import edu.eci.cvds.samples.entities.Iniciativa;
import edu.eci.cvds.samples.entities.Usuario;

import java.util.Date;

public class MyBatisRegistroDAO implements RegistroDAO {
    @Inject
    private RegistroMapper registroMapper;

    @Override
    public void registrarIniciativaAUsuario(Date fecha_registro, Iniciativa iniciativa, Usuario usuario) throws PersistenceException {
        try {
            if (iniciativa == null) {
                throw new javax.persistence.PersistenceException("La iniciativa es nula");
            }
            if (usuario == null) {
                throw new javax.persistence.PersistenceException("El usuario es nulo");
            }
            if (fecha_registro == null) {
                throw new javax.persistence.PersistenceException("La fecha es nula");
            }
            registroMapper.registrarIniciativaAUsuario(fecha_registro, iniciativa, usuario);
        } catch (javax.persistence.PersistenceException e) {
            throw new javax.persistence.PersistenceException(e.getMessage(), e);
        }
    }
}
