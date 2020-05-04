package edu.eci.cvds.sampleprj.dao;

import edu.eci.cvds.samples.entities.Comentario;
import edu.eci.cvds.samples.entities.Iniciativa;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;


public interface IniciativaDAO {

    /**
     * Metodo que permite registrar un comentario a una iniciativa
     * @param comentario comentario a registrar a la iniciativa
     * @param idIniciativa id de la iniciativa sobre la cual se realiza el comentario
     */
    public void agregarComentarioAIniciativa(Comentario comentario , int idIniciativa)  throws PersistenceException ;


    /**
     * Metodo que permite consultar las iniciativas ordenadas por una columna
     * @param columna Columna por la cual se quieren ordenar las iniciativas
     * @return Lista de iniciativas
     */
    public List<Iniciativa> consultarIniciativasOrdenadasPorColumna(String columna) throws PersistenceException;

    /**
     * Metodo que permite consultar iniciativas que contengan una palabra clave
     * @param palabra palabra la cual va a filtrar la lista de iniciativas
     * @return Lista de iniciativas
     */
    public List<Iniciativa> consultarIniciativasPorPalabraClave(String palabra) throws PersistenceException;

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

    /**
     * Metodo que permite conocer el numero de iniciativas de un area
     * @param area Finanzas, Proyectos, Innovacion, Ventas
     * @return Numero de iniciativas de un area
     * @throws PersistenceException
     */
    public int consultarNumeroDeIniciativasPorArea(String area) throws PersistenceException;

    /**
     * Permite obtener las iniciativas de un area
     * @param area Finanzas, proyectos, ventas, innovacion
     * @return Lista con todas las iniciativas del area
     * @throws PersistenceException
     */
    public List<Iniciativa> consultarIniciativasPorArea(String area) throws  PersistenceException;

    public void agregarIniciativaRelacionadaAIniciativa(int idIni, int idIniRelacionada) throws PersistenceException;

    /**
     * Listado de las iniciativas de un proponente
     * @param email email del proponente
     * @return lista de iniciativas del proponente
     */
    public List<Iniciativa> consultarIniciativasDelProponente(String email) throws PersistenceException;

    /**
     * Permite modificar una iniciativa del proponente
     * @param nombre nuevo nombre de la inicitiva
     * @param iniciativa iniciativa que se va a modificar
     */
    public void modificarIniciativa(String nombre, Iniciativa iniciativa);

    /**
     * Permite modificar una iniciativa del proponente
     * @param  descripcion nueva descripcion de la inicitiva
     * @param iniciativa iniciativa que se va a modificar
     */
    public void modificarDescripcion(String descripcion, Iniciativa iniciativa);

    /**
     * Elimina las palabras clave
     * @param id id de la palabra que se desea eliminar
     */
    public void deletePalabraClave(int id);

    /**
     * Permite conocer el numero de iniciativas en el estado
     * @param estado estado de la iniciativa
     * @return numero de iniciativas con ese estado
     */
    public int consultarNumeroDeIniciativasPorEstado(String estado);

}
