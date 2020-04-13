package edu.eci.cvds.sampleprj.dao.mybatis.mappers;

import edu.eci.cvds.samples.entities.Iniciativa;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface IniciativaMapper {

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
}
