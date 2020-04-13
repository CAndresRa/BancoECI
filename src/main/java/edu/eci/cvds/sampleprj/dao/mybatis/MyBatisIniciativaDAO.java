package edu.eci.cvds.sampleprj.dao.mybatis;

import com.google.inject.Inject;
import edu.eci.cvds.sampleprj.dao.IniciativaDAO;
import edu.eci.cvds.sampleprj.dao.PersistenceException;
import edu.eci.cvds.sampleprj.dao.mybatis.mappers.IniciativaMapper;
import edu.eci.cvds.samples.entities.Iniciativa;

import java.util.List;

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

    @Override
    public List<Iniciativa> consultarIniciativas() throws PersistenceException {
        try {
            return iniciativaMapper.consultarIniciativas();
        } catch (javax.persistence.PersistenceException e){
            throw new javax.persistence.PersistenceException(e.getMessage(),e);
        }
    }

    @Override
    public Iniciativa consultarIniciativasPorId(int id) throws PersistenceException {
        try{
            return  iniciativaMapper.consultarIniciativasPorId(id);
        } catch ( javax.persistence.PersistenceException e){
            throw new  javax.persistence.PersistenceException(e.getMessage(), e);
        }
    }

    @Override
    public void cambiarEstadoAiniciativa(String estado, Iniciativa iniciativa) throws PersistenceException {
        try{
            iniciativaMapper.cambiarEstadoAiniciativa(estado,iniciativa);
        } catch (javax.persistence.PersistenceException e){
            throw new javax.persistence.PersistenceException(e.getMessage(),e);
        }
    }
}
