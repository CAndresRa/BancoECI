package edu.eci.cvds.sampleprj.dao;

import edu.eci.cvds.samples.entities.Iniciativa;
import org.apache.ibatis.annotations.Param;


public interface IniciativaDAO {


    public void insertarIniciativa(Iniciativa i) throws PersistenceException;


    public void agregarPalabraClaveAIniciativa(Iniciativa iniciativa , String palabra);

}
