package edu.eci.cvds.sampleprj.dao;

import edu.eci.cvds.samples.entities.Iniciativa;
import edu.eci.cvds.samples.entities.Usuario;
import edu.eci.cvds.samples.services.ExcepcionServiciosBancoProyectos;
import org.apache.ibatis.annotations.Param;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;


public interface IniciativaDAO {

    /**
     * Metodo que permite registrar una iniciativa
     * @param i Iniciativa a insertar del usuario a consultar
     * @throws PersistenceException
     */

    public void insertarIniciativa(Iniciativa i) throws PersistenceException;

    /**
     * Metodo que permite registrar una palabra clave a una inicitiava
     * @param iniciativa Iniciativa a insertar del usuario a consultar
     * @param palabra palabra a insertar
     * @throws PersistenceException
     */

    public void agregarPalabraClaveAIniciativa(Iniciativa iniciativa , String palabra) throws PersistenceException;

    /**
     * Metodo que permite consultar todas las iniciativas
     * @return Lista de iniciativas
     * @throws PersistenceException
     */


    public List<Iniciativa> consultarIniciativas() throws PersistenceException;

    /**
     * Metodo que permite consultar todas las iniciativas por id
     * @param id id de la iniciativa a consultar
     * @return Iniciativa que coincida con el id ingresado como parametro
     * @throws PersistenceException
     */

    public Iniciativa consultarIniciativasPorId(int id) throws PersistenceException;

    /**
     * Metodo que permite consultar todas las iniciativas por id
     * @param estado estado a actualizar
     * @param iniciativa Iniciativa a la cual se le cambiará el estado
     * @throws PersistenceException
     */

    public  void cambiarEstadoAiniciativa(String estado, Iniciativa iniciativa) throws  PersistenceException;
}
