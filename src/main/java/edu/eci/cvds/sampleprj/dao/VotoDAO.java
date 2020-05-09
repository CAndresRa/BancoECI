package edu.eci.cvds.sampleprj.dao;
import edu.eci.cvds.samples.entities.Iniciativa;
import edu.eci.cvds.samples.entities.Usuario;
import org.apache.ibatis.annotations.Param;

public interface VotoDAO {
    /**
     * Agrega voto a la iniciativa
     * @param usuario usuario que registra el voto
     * @param iniciativa iniciativa que es votada
     */
     public void agregarVoto(Usuario usuario, Iniciativa iniciativa);

    /**
     *
     * @param usuario
     * @param iniciativa
     * @return
     */
     public int confirmarSiYaVoto(Usuario usuario,Iniciativa iniciativa);

    /**
     *
     * @param id
     */
     public void deleteVoto(int id);

    /**
     *
     * @param usuario
     * @param iniciativa
     * @return
     */
     public int consultarIdDeVotacion(Usuario usuario, Iniciativa iniciativa);

    /**
     * Numero de votos de una iniciativa
     * @param iniciativa
     * @return
     */
     public int numeroDeVotosIniciativa(Iniciativa iniciativa);

}
