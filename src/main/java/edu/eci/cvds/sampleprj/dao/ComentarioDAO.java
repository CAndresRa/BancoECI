package edu.eci.cvds.sampleprj.dao;

import edu.eci.cvds.samples.entities.Comentario;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ComentarioDAO {
    /**
     * Metodo que permite consular los comentarios por iniciativa
     * @param idIniciativa id de la iniciativa de la cual se quieren conocer los comentarios
     * @return Lista de comentarios
     */
    public List<Comentario> consultarComentariosPorIniciativa(@Param("idIniciativa") int idIniciativa) ;
}
