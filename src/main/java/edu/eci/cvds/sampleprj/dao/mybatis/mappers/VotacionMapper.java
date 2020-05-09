package edu.eci.cvds.sampleprj.dao.mybatis.mappers;

import edu.eci.cvds.samples.entities.Iniciativa;
import edu.eci.cvds.samples.entities.Usuario;
import org.apache.ibatis.annotations.Param;

public interface VotacionMapper {

    /**
     *
     * @param usuario
     * @param iniciativa
     */
    public void agregarVoto (@Param("usuario") Usuario usuario, @Param("iniciativa") Iniciativa iniciativa);

    /**
     *
     * @param usuario
     * @param iniciativa
     * @return
     */
    public int confirmarSiYaVoto(@Param("usuario") Usuario usuario, @Param("iniciativa") Iniciativa iniciativa);

    /**
     *
     * @param id
     */
    public void deleteVoto(@Param("id") int id);

    /**
     *
     * @param usuario
     * @param iniciativa
     * @return
     */
    public int consultarIdDeVotacion(@Param("usuario") Usuario usuario, @Param("iniciativa") Iniciativa iniciativa);

    /**
     * Permite conocer el numero de votos de una iniciativa
     * @param iniciativa
     * @return
     */
    public int numeroDeVotosIniciativa(@Param("iniciativa") Iniciativa iniciativa);
}
