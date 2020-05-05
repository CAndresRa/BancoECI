package edu.eci.cvds.test;


import com.google.inject.Inject;

import edu.eci.cvds.sampleprj.dao.PersistenceException;
import edu.eci.cvds.samples.entities.Iniciativa;
import edu.eci.cvds.samples.entities.PalabraClave;
import edu.eci.cvds.samples.entities.Usuario;
import edu.eci.cvds.samples.entities.Comentario;
import edu.eci.cvds.samples.services.ExcepcionServiciosBancoProyectos;
import edu.eci.cvds.samples.services.ServiciosBancoProyectosFactory;

import edu.eci.cvds.samples.services.ServiciosIniciativa;
import edu.eci.cvds.samples.services.ServiciosUsuario;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import org.junit.Assert;

import static org.junit.Assert.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ServiciosBancoProyectosTestUsuario {
    @Inject
    private SqlSession sqlSession;

    ServiciosUsuario serviciosUsuario;
	ServiciosIniciativa serviciosIniciativa;
    
    public ServiciosBancoProyectosTestUsuario() {
        serviciosUsuario = ServiciosBancoProyectosFactory.getInstance().getServiciosUsuarioTesting();
		serviciosIniciativa = ServiciosBancoProyectosFactory.getInstance().getServiciosIniciativaTesting();
    }
    
    @Test
    public void deberiaConsultarUsuario() {
    	try {
    		serviciosUsuario.consultarUsuario("ernesto.camacho@mail.escuelaing.edu.co");
    	}catch(ExcepcionServiciosBancoProyectos e) {
    		fail();    		
    	}  	
    }
    
    @Test(expected = ExcepcionServiciosBancoProyectos.class)
    public void lanzaExcepcionUsuarioNoExiste() throws ExcepcionServiciosBancoProyectos {
    	serviciosUsuario.consultarUsuario("NN@mail.com");  	
    }
    
    @Test
    public void deberiaConsultarUsuarios() {
    	try {
    		List<Usuario> lista= serviciosUsuario.consultarUsuarios();
    		Assert.assertEquals(1,lista.size());
    	}catch(ExcepcionServiciosBancoProyectos e) {
    		fail();    		
    	}  	
    }

    @Test
    public void deberiaAsignarRolAUsuario() {
    	try {
    		Usuario noRol= serviciosUsuario.consultarUsuario("no.rol@mail.escuelaing.edu.co");
    		serviciosUsuario.asignarRolUsuario("nuevoRol", noRol);
    		Usuario siRol= serviciosUsuario.consultarUsuario("no.rol@mail.escuelaing.edu.co");
    		Assert.assertEquals("nuevoRol",siRol.getRol());
    	}catch(ExcepcionServiciosBancoProyectos e) {
    		fail();    		
    	}  	
    }
    
    @Test(expected = ExcepcionServiciosBancoProyectos.class)
    public void lanzaExcepcionAsignacionDeRolNula() throws ExcepcionServiciosBancoProyectos {
    	Usuario usuario= serviciosUsuario.consultarUsuario("no.rol@mail.escuelaing.edu.co");
    	serviciosUsuario.asignarRolUsuario(null, usuario); 	
    }
      
    @Test(expected = ExcepcionServiciosBancoProyectos.class)
    public void lanzaExcepcionAsignacionDeRolDeUsuarionNoExistente() throws ExcepcionServiciosBancoProyectos {
    	serviciosUsuario.asignarRolUsuario("rol", serviciosUsuario.consultarUsuario("NN@mail.com"));
    }

    @Test
    public void deberiaInsertarUsuario() {
    	try {
    		int usuariosInicio= serviciosUsuario.consultarUsuarios().size();
    		serviciosUsuario.registrarUsuario(new Usuario(000, "nuevo@mail.com", "nuevo", "nuevo", "1234", "Publico", null));
    		int usuariosFin= serviciosUsuario.consultarUsuarios().size();
    		Assert.assertEquals((usuariosInicio + 1), usuariosFin);
    	}catch(ExcepcionServiciosBancoProyectos e) {
    		fail();
    	}
    }
    
    @Test(expected = ExcepcionServiciosBancoProyectos.class)
    public void lanzaExcepcionDeRolAlInsertarUsuario() throws ExcepcionServiciosBancoProyectos {
    	serviciosUsuario.registrarUsuario(new Usuario(000, "nuevo@mail.com", "nuevo", "nuevo", "1234", "otro", ""));
    }
    
    
}