package edu.eci.cvds.test;


import com.google.inject.Inject;
import edu.eci.cvds.samples.services.ExcepcionServiciosBancoProyectos;
import edu.eci.cvds.samples.services.ServiciosBancoProyectos;
import edu.eci.cvds.samples.services.ServiciosBancoProyectosFactory;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import org.junit.Assert;

import static org.junit.Assert.*;

public class ServiciosBancoProyectosTest {
    @Inject
    private SqlSession sqlSession;

    ServiciosBancoProyectos serviciosBancoProyectos;

    public ServiciosBancoProyectosTest() {
        serviciosBancoProyectos = ServiciosBancoProyectosFactory.getInstance().getServiciosBancoProyectosTesting();
    }
    @Test
    public void deberiaConsultarUsuario() {
    	try {
    		serviciosBancoProyectos.consultarUsuario("ernesto.camacho@mail.escuelaing.edu.co");
    	}catch(ExcepcionServiciosBancoProyectos e) {
    		fail();    		
    	}
    	
    }

    /*@Test
    public void lanzaExcepcionUsuarioNoExiste() {
    	try {
    		serviciosBancoProyectos.consultarUsuario("NN@mail.com");
    	}catch(ExcepcionServiciosBancoProyectos e) {
    		Assert.assertEquals(e.getMessage(),"Error al consultar clientes");
    	}    	
    }*/
}
