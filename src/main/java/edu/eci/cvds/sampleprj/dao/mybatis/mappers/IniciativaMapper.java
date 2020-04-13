package edu.eci.cvds.sampleprj.dao.mybatis.mappers;

import com.google.inject.Inject;
import edu.eci.cvds.samples.entities.Iniciativa;
import edu.eci.cvds.samples.entities.PalabraClave;
import edu.eci.cvds.samples.entities.Usuario;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface IniciativaMapper {

    public void insertarIniciativa(@Param("iniciativa") Iniciativa i);

    public void agregarPalabraClaveAIniciativa(@Param("iniciativa") Iniciativa iniciativa , @Param("palabra") String palabra);

    public List<Iniciativa> consultarIniciativas();

    public  Iniciativa consultarIniciativasPorId(@Param("id") int id);

    public void cambiarEstadoAiniciativa(@Param("estado") String estado, @Param("iniciativa") Iniciativa iniciativa);
}
