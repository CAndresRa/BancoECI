package edu.eci.cvds.sampleprj.dao.mybatis.mappers;

import edu.eci.cvds.samples.entities.Comentario;
import edu.eci.cvds.samples.entities.Iniciativa;
import edu.eci.cvds.samples.entities.Usuario;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface IniciativaMapper {

    /**
     * Metodo que permite registrar un comentario a una iniciativa
     * @param comentario comentario a registrar a la iniciativa
     * @param idIniciativa id de la iniciativa sobre la cual se realiza el comentario
     */
    public void agregarComentarioAIniciativa(@Param("comentario") Comentario comentario  , @Param("iniciativa") int idIniciativa) ;

    /**
     * Metodo que permite consultar las iniciativas ordenadas por una columna
     * @param columna Columna por la cual se quieren ordenar las iniciativas
     * @return Lista de iniciativas
     */
    public List<Iniciativa> consultarIniciativasOrdenadasPorColumna(@Param("columna") String columna) ;

    /**
     * Agrega una iniciativa relacionada a una iniciativa
     * @param idIniciativa iniciativa principal
     * @param idIniciativaRelacionada iniciativa relacionada a la principal
     */
    public void agregarIniciativaRelacionadaAIniciativa(@Param("idIniciativa") int idIniciativa , @Param("idIniciativaRelacionada") int idIniciativaRelacionada );

    /**
     * Metodo que permite consultar las iniciativas que pertenecen a cierta area
     * @param area Area a la que pertenece el usuario que registro la iniciativa
     * @return Lista de iniciativas
     */
    public List<Iniciativa> consultarIniciativasPorArea(@Param("area") String area);

    /**
     * Metodo que permite consultar el numero de iniciativas que pertenecen a cierta area
     * @param area Area a la que pertenece el usuario que registro la iniciativa
     * @return Numero de iniciativas
     */
    public int consultarNumeroDeIniciativasPorArea(@Param("area") String area);

    /**
     * Metodo que permite registrar una iniciativa
     * @param i Iniciativa a insertar del usuario a consultar
     */
    public void insertarIniciativa(@Param("iniciativa") Iniciativa i);

    /**
     * Metodo que permite registrar una palabra clave a una inicitiava
     * @param iniciativa Iniciativa a insertar del usuario a consultar
     * @param palabra palabra a insertar
     */
    public void agregarPalabraClaveAIniciativa(@Param("iniciativa") Iniciativa iniciativa , @Param("palabra") String palabra);

    /**
     * Metodo que permite consultar todas las iniciativas
     * @return Lista de iniciativas
     */
    public List<Iniciativa> consultarIniciativas();

    /**
     * Metodo que permite consultar iniciativas que contengan una palabra clave
     * @param palabra palabra la cual va a filtrar la lista de iniciativas
     * @return Lista de iniciativas
     */
    public List<Iniciativa> consultarIniciativasPorPalabraClave(@Param("palabra") String palabra);

    /**
     * Metodo que permite consultar todas las iniciativas por id
     * @param id id de la iniciativa a consultar
     * @return Iniciativa que coincida con el id ingresado como parametro
     */
    public  Iniciativa consultarIniciativasPorId(@Param("id") int id);

    /**
     * Metodo que permite consultar todas las iniciativas por id
     * @param estado estado a actualizar
     * @param iniciativa Iniciativa a la cual se le cambiará el estado
     */
    public void cambiarEstadoAiniciativa(@Param("estado") String estado, @Param("iniciativa") Iniciativa iniciativa);

    /**
     * Listado de las iniciativas de un proponente
     * @param email email del proponente
     * @return lista de iniciativas del proponente
     */
    public List<Iniciativa> consultarIniciativasDelProponente(@Param("email") String email);

    /**
     * Permite modificar una iniciativa del proponente
     * @param nombre nuevo nombre de la inicitiva
     * @param iniciativa iniciativa que se va a modificar
     */
    public void modificarIniciativa(@Param("nombre") String nombre, @Param("iniciativa") Iniciativa iniciativa);

    /**
     * Permite modificar una iniciativa del proponente
     * @param descripcion nueva descripcion de la inicitiva
     * @param iniciativa iniciativa que se va a modificar
     */
    public void modificarDescripcion(@Param("descripcion") String descripcion, @Param("iniciativa") Iniciativa iniciativa);


    /**
     * Elimina las palabras clave
     * @param id id de la palabra que se desea eliminar
     */
    public void deletePalabraClave(@Param("id") int id);

    /**
     * Permite conocer el numero de iniciativas en el estado
     * @param estado estado de la iniciativa
     * @return numero de iniciativas con ese estado
     */
    public int consultarNumeroDeIniciativasPorEstado(@Param("estado") String estado);

}
