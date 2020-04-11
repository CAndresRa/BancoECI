package edu.eci.cvds.sampleprj.dao.mybatis.mappers;

import com.google.inject.Inject;
import edu.eci.cvds.samples.entities.Iniciativa;
import edu.eci.cvds.samples.entities.PalabraClave;
import org.apache.ibatis.annotations.Param;

public interface IniciativaMapper {

    public void insertarIniciativa(@Param("iniciativa") Iniciativa i);

    public void agregarPalabraClaveAIniciativa(@Param("iniciativa") Iniciativa iniciativa , @Param("palabra") String palabra);


}
