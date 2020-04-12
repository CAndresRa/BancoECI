package edu.eci.cvds.sampleprj.dao.mybatis;

import com.google.inject.Inject;
import edu.eci.cvds.sampleprj.dao.IniciativaDAO;
import edu.eci.cvds.sampleprj.dao.PersistenceException;
import edu.eci.cvds.sampleprj.dao.mybatis.mappers.IniciativaMapper;
import edu.eci.cvds.sampleprj.dao.mybatis.mappers.UsuarioMapper;
import edu.eci.cvds.samples.entities.Iniciativa;

public class MyBatisIniciativaDAO implements IniciativaDAO {

    @Inject
    private IniciativaMapper iniciativaMapper;

    @Override
    public void insertarIniciativa(Iniciativa i) throws javax.persistence.PersistenceException{
        try {
            if(i == null){
                throw new javax.persistence.PersistenceException("La iniciativa es nula");
            }
            iniciativaMapper.insertarIniciativa(i);
        } catch (javax.persistence.PersistenceException e){
            throw new javax.persistence.PersistenceException(e.getMessage(),e);
        }
    }

    @Override
    public void agregarPalabraClaveAIniciativa(Iniciativa iniciativa, String palabra){
        try {
            if(iniciativa == null){
                throw new javax.persistence.PersistenceException("La iniciativa es nula");
            }
            if(palabra == null){
                throw new javax.persistence.PersistenceException("La palabra es nula");
            }
            iniciativaMapper.agregarPalabraClaveAIniciativa(iniciativa , palabra);
        } catch (javax.persistence.PersistenceException e){
            throw new javax.persistence.PersistenceException(e.getMessage(),e);
        }
    }
}
