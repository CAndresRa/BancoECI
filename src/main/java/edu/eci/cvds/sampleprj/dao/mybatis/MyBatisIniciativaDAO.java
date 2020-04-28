package edu.eci.cvds.sampleprj.dao.mybatis;

import com.google.inject.Inject;
import edu.eci.cvds.sampleprj.dao.IniciativaDAO;
import edu.eci.cvds.sampleprj.dao.PersistenceException;
import edu.eci.cvds.sampleprj.dao.mybatis.mappers.IniciativaMapper;
import edu.eci.cvds.samples.entities.Comentario;
import edu.eci.cvds.samples.entities.Iniciativa;

import java.util.Date;
import java.util.List;

public class MyBatisIniciativaDAO implements IniciativaDAO {

    @Inject
    private IniciativaMapper iniciativaMapper;

    @Override
    public void agregarComentarioAIniciativa(Comentario comentario, int idIniciativa) {
        try {
            if(comentario == null){
                throw new javax.persistence.PersistenceException("El comentario es nulo");
            }
            if(comentario.getFecha_comentario() == null  ||  comentario.getContenido() == null || comentario.getCorreo_usuario() == null || comentario.getApellido_usuario() == null || comentario.getNombre_usuario() == null){
                throw new javax.persistence.PersistenceException("Alguno de los contenidos del comentario es nulo");
            }
            iniciativaMapper.agregarComentarioAIniciativa(comentario,idIniciativa);
        } catch (javax.persistence.PersistenceException e) {
            throw new javax.persistence.PersistenceException(e.getMessage(), e);
        }
    }

    @Override
    public List<Iniciativa> consultarIniciativasOrdenadasPorColumna(String columna) throws javax.persistence.PersistenceException {
        try {
            return iniciativaMapper.consultarIniciativasOrdenadasPorColumna(columna);
        } catch (javax.persistence.PersistenceException e) {
            throw new javax.persistence.PersistenceException(e.getMessage(), e);
        }
    }

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
    public List<Iniciativa> consultarIniciativasPorPalabraClave(String palabra) throws PersistenceException {
        try{
            List<Iniciativa> iniciativas= iniciativaMapper.consultarIniciativasPorPalabraClave(palabra);
            return iniciativas;
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

    @Override
    public int consultarNumeroDeIniciativasPorArea(String area) throws PersistenceException {
        try{
            return iniciativaMapper.consultarNumeroDeIniciativasPorArea(area);
        } catch (javax.persistence.PersistenceException e) {
            throw new javax.persistence.PersistenceException(e.getMessage(),e);
        }
    }

    @Override
    public List<Iniciativa> consultarIniciativasPorArea(String area) throws PersistenceException {
        try{
            return iniciativaMapper.consultarIniciativasPorArea(area);
        } catch (javax.persistence.PersistenceException e){
            throw new javax.persistence.PersistenceException(e.getMessage(), e);
        }
    }

    @Override
    public void agregarIniciativaRelacionadaAIniciativa(int idIni, int idIniRelacionada) throws PersistenceException {
        try{
            iniciativaMapper.agregarIniciativaRelacionadaAIniciativa(idIni, idIniRelacionada);
        } catch (javax.persistence.PersistenceException e ){
            throw new javax.persistence.PersistenceException(e.getMessage(), e);
        }
    }
}
