package edu.eci.cvds.test;


import com.google.inject.Inject;

import edu.eci.cvds.samples.entities.Iniciativa;
import edu.eci.cvds.samples.entities.Usuario;
import edu.eci.cvds.samples.services.ExcepcionServiciosBancoProyectos;
import edu.eci.cvds.samples.services.ServiciosBancoProyectos;

import edu.eci.cvds.samples.services.ServiciosBancoProyectosFactory;

import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import org.junit.Assert;

import static org.junit.Assert.*;


import java.util.List;

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

    
    @Test
    public void lanzaExcepcionUsuarioNoExiste() {
    	try {
    		serviciosBancoProyectos.consultarUsuario("NN@mail.com");
    	}catch(ExcepcionServiciosBancoProyectos e) {
    		Assert.assertEquals("El usuario no existe",e.getMessage());
    	}    	
    }

/*
    @Test
    public void deberiaConsultarUsuarios() {
    	try {
    		List<Usuario> lista= serviciosBancoProyectos.consultarUsuarios();
    		Assert.assertEquals(3,lista.size());
    	}catch(ExcepcionServiciosBancoProyectos e) {
    		fail();    		
    	}  	
    }

 */


    
    
    @Test
    public void deberiaAsignarRolAUsuario() {
    	try {
    		Usuario noRol= serviciosBancoProyectos.consultarUsuario("no.rol@mail.escuelaing.edu.co");
    		serviciosBancoProyectos.asignarRolUsuario("nuevoRol", noRol);
    		Usuario siRol= serviciosBancoProyectos.consultarUsuario("no.rol@mail.escuelaing.edu.co");
    		Assert.assertEquals("nuevoRol",siRol.getRol());
    	}catch(ExcepcionServiciosBancoProyectos e) {
    		fail();    		
    	}  	
    }
    
    @Test
    public void lanzaExcepcionAsignacionDeRolNula() {
    	try {
    		Usuario pepito= serviciosBancoProyectos.consultarUsuario("pepito.perez@mail.escuelaing.edu.co");
    		serviciosBancoProyectos.asignarRolUsuario(null, pepito);
    		fail(); 
    	}catch(ExcepcionServiciosBancoProyectos e) {
    		Assert.assertEquals("El rol es nulo",e.getMessage());
    	}  	
    }
    
    
    @Test
    public void lanzaExcepcionAsignacionDeRolDeUsuarionNoExistente() {
    	try {
    		serviciosBancoProyectos.asignarRolUsuario("rol", serviciosBancoProyectos.consultarUsuario("NN@mail.com"));
    		fail(); 
    	}catch(ExcepcionServiciosBancoProyectos e) {
    		Assert.assertEquals("El usuario no existe",e.getMessage());
    		}
    	}

    	/*
    @Test
    public void deberiaInsertarUsuario() {
    	try {
    		serviciosBancoProyectos.registrarUsuario(new Usuario(000, "nuevo@mail.com", "nuevo", "nuevo", "1234", "Publico", null));
    	}catch(ExcepcionServiciosBancoProyectos e) {
    		fail();
    	}
    }

    	 */


    
    @Test
    public void lanzaExcepcionDeRolAlInsertarUsuario() {
    	try {
    		serviciosBancoProyectos.registrarUsuario(new Usuario(000, "nuevo@mail.com", "nuevo", "nuevo", "1234", "otro", ""));
    	}catch(ExcepcionServiciosBancoProyectos e) {
    		Assert.assertEquals("Rol invalido",e.getMessage());
    	}
    }
}
