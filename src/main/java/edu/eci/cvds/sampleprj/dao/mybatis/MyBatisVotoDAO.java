package edu.eci.cvds.sampleprj.dao.mybatis;

import com.google.inject.Inject;
import edu.eci.cvds.sampleprj.dao.VotoDAO;
import edu.eci.cvds.sampleprj.dao.mybatis.mappers.VotacionMapper;
import edu.eci.cvds.samples.entities.Iniciativa;
import edu.eci.cvds.samples.entities.Usuario;

public class MyBatisVotoDAO  implements VotoDAO {
    @Inject
    private VotacionMapper votoMapper;

    @Override
    public void agregarVoto(Usuario usuario, Iniciativa iniciativa) {
        try{
            votoMapper.agregarVoto(usuario,iniciativa);
        } catch (javax.persistence.PersistenceException e){
            throw new javax.persistence.PersistenceException(e.getMessage(), e);
        }
    }

    @Override
    public int confirmarSiYaVoto(Usuario usuario, Iniciativa iniciativa) {
        try {
            return votoMapper.confirmarSiYaVoto(usuario, iniciativa);
        } catch (javax.persistence.PersistenceException e) {
            throw new javax.persistence.PersistenceException(e.getMessage(), e);
        }
    }

    @Override
    public void deleteVoto(int id) {
        try {
            votoMapper.deleteVoto(id);
        } catch (javax.persistence.PersistenceException e){
            throw new javax.persistence.PersistenceException(e.getMessage(), e);
        }
    }

    @Override
    public int consultarIdDeVotacion(Usuario usuario, Iniciativa iniciativa) {
        try {
            return votoMapper.consultarIdDeVotacion(usuario,iniciativa);
        } catch (javax.persistence.PersistenceException e){
            throw new javax.persistence.PersistenceException(e.getMessage(), e);
        }

    }

    @Override
    public int numeroDeVotosIniciativa(Iniciativa iniciativa) {
        try{
            return votoMapper.numeroDeVotosIniciativa(iniciativa);
        } catch (javax.persistence.PersistenceException e){
            throw new javax.persistence.PersistenceException(e.getMessage(), e);
        }
    }
}
