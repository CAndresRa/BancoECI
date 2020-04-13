package edu.eci.cvds.sampleprj.dao;

import edu.eci.cvds.samples.entities.Iniciativa;
import edu.eci.cvds.samples.entities.Usuario;
import org.apache.ibatis.annotations.Param;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;


public interface IniciativaDAO {


    public void insertarIniciativa(Iniciativa i) throws PersistenceException;

    public void agregarPalabraClaveAIniciativa(Iniciativa iniciativa , String palabra) throws PersistenceException;

    public List<Iniciativa> consultarIniciativas() throws PersistenceException;

    public Iniciativa consultarIniciativasPorId(int id) throws PersistenceException;

    public  void cambiarEstadoAiniciativa(String estado, Iniciativa iniciativa) throws  PersistenceException;
}
