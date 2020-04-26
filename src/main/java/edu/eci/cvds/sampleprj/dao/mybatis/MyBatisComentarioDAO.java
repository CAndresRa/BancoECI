package edu.eci.cvds.sampleprj.dao.mybatis;

import com.google.inject.Inject;
import edu.eci.cvds.sampleprj.dao.ComentarioDAO;
import edu.eci.cvds.sampleprj.dao.mybatis.mappers.ComentarioMapper;
import edu.eci.cvds.samples.entities.Comentario;

import java.util.List;

public class MyBatisComentarioDAO implements ComentarioDAO {
    @Inject
    private ComentarioMapper comentarioMapper;

    @Override
    public List<Comentario> consultarComentariosPorIniciativa(int idIniciativa) {
        try {
            return comentarioMapper.consultarComentariosPorIniciativa(idIniciativa);
        } catch (javax.persistence.PersistenceException e) {
            throw new javax.persistence.PersistenceException(e.getMessage(), e);
        }
    }
}
