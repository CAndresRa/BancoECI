package edu.eci.cvds.test;


import com.google.inject.Inject;

import edu.eci.cvds.sampleprj.dao.PersistenceException;
import edu.eci.cvds.samples.entities.Iniciativa;
import edu.eci.cvds.samples.entities.PalabraClave;
import edu.eci.cvds.samples.entities.Usuario;
import edu.eci.cvds.samples.services.ExcepcionServiciosBancoProyectos;
import edu.eci.cvds.samples.services.ServiciosBancoProyectos;

import edu.eci.cvds.samples.services.ServiciosBancoProyectosFactory;

import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import org.junit.Assert;

import static org.junit.Assert.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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

    @Test
    public void deberiaConsultarUsuarios() {
    	try {
    		List<Usuario> lista= serviciosBancoProyectos.consultarUsuarios();
    		Assert.assertEquals(3,lista.size());
    	}catch(ExcepcionServiciosBancoProyectos e) {
    		fail();    		
    	}  	
    }

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

    @Test
    public void deberiaInsertarUsuario() {
    	try {
    		serviciosBancoProyectos.registrarUsuario(new Usuario(000, "nuevo@mail.com", "nuevo", "nuevo", "1234", "Publico", null));
    	}catch(ExcepcionServiciosBancoProyectos e) {
    		fail();
    	}
    }
    
    @Test
    public void lanzaExcepcionDeRolAlInsertarUsuario() {
    	try {
    		serviciosBancoProyectos.registrarUsuario(new Usuario(000, "nuevo@mail.com", "nuevo", "nuevo", "1234", "otro", ""));
    	}catch(ExcepcionServiciosBancoProyectos e) {
    		Assert.assertEquals("Rol invalido",e.getMessage());
    	}
    }
    
    @Test
    public void deberiaInsertarIniciativa() {
    	try {
    		List<PalabraClave> palabrasPrueba= new ArrayList<PalabraClave>();
    		List<String> palabrasVacio= new ArrayList<String>();
    		palabrasPrueba.add(new PalabraClave("prueba"));
    		Iniciativa iniciativaDePrueba= new Iniciativa("prueba","prueba","prueba",
    				new SimpleDateFormat("yyyy/MM/dd").parse("2020/04/13"), palabrasPrueba
    				,serviciosBancoProyectos.consultarUsuario("ernesto.camacho@mail.escuelaing.edu.co"));
    		serviciosBancoProyectos.insertarIniciativa(iniciativaDePrueba,palabrasVacio);
    	}catch(ExcepcionServiciosBancoProyectos e) {
    		System.out.println(e.getMessage());
    		fail();
    	}catch (PersistenceException e) {
    		fail();
		} catch (ParseException e) {
			fail();
		}
    }
    
    @Test
    public void deberiaLanzarExcepticonAlIntnentarInsertarIniciativaDescripcionNula() {
    	try {
    		List<PalabraClave> palabrasPrueba= new ArrayList<PalabraClave>();
    		List<String> palabrasVacio= new ArrayList<String>();
    		palabrasPrueba.add(new PalabraClave("prueba"));
    		Iniciativa iniciativaDePrueba= new Iniciativa(000,"prueba",null,"prueba",
    				new SimpleDateFormat("yyyy/MM/dd").parse("2020/04/13"), palabrasPrueba);
    		serviciosBancoProyectos.insertarIniciativa(iniciativaDePrueba,palabrasVacio);
    	}catch(ExcepcionServiciosBancoProyectos e) {
    		Assert.assertEquals("La iniciativa no tiene toda la informacion necesaria", e.getMessage());
    	}catch (PersistenceException e) {
    		fail();
		} catch (ParseException e) {
			fail();
		}
    }
    
    @Test
    public void deberiaLanzarExcepticonAlIntnentarInsertarIniciativaFechaNula() {
    	try {
    		List<PalabraClave> palabrasPrueba= new ArrayList<PalabraClave>();
    		List<String> palabrasVacio= new ArrayList<String>();
    		palabrasPrueba.add(new PalabraClave("prueba"));
    		Iniciativa iniciativaDePrueba= new Iniciativa(000,"prueba","prueba","prueba", null, palabrasPrueba);
    		serviciosBancoProyectos.insertarIniciativa(iniciativaDePrueba,palabrasVacio);
    	}catch(ExcepcionServiciosBancoProyectos e) {
    		Assert.assertEquals("La iniciativa no tiene toda la informacion necesaria", e.getMessage());
    	}catch (PersistenceException e) {
    		fail();
    	}
    }
    
    @Test
    public void deberiaLanzarExcepticonAlIntnentarInsertarIniciativaUsuarioNulo() {
    	try {
    		List<PalabraClave> palabrasPrueba= new ArrayList<PalabraClave>();
    		List<String> palabrasVacio= new ArrayList<String>();
    		palabrasPrueba.add(new PalabraClave("prueba"));
    		Iniciativa iniciativaDePrueba= new Iniciativa(000,"prueba","prueba","prueba",
    				new SimpleDateFormat("yyyy/MM/dd").parse("2020/04/13"), palabrasPrueba);
    		serviciosBancoProyectos.insertarIniciativa(iniciativaDePrueba,palabrasVacio);
    	}catch(ExcepcionServiciosBancoProyectos e) {
    		Assert.assertEquals("La iniciativa no tiene toda la informacion necesaria", e.getMessage());
    	}catch (PersistenceException e) {
    		fail();
		} catch (ParseException e) {
			fail();
		}
    }   	
    
    /*@Test
    public void deberiaConsultarIniciativaPorId() {
    	try {
    		System.out.println(serviciosBancoProyectos.consultarIniciativas().get(0));
    		Iniciativa iniciativaDePrueba= serviciosBancoProyectos.consultarIniciativasPorId(000);
    		System.out.println(iniciativaDePrueba);
    		//System.out.println(iniciativaDePrueba.getPalabras_clave().size());
    		//Assert.assertEquals(serviciosBancoProyectos.consultarUsuario("ernesto.camacho@mail.escuelaing.edu.co"), iniciativaDePrueba.getUsuario());
    	}catch(ExcepcionServiciosBancoProyectos e) {
    		fail();
    	}
    }*/
    
    /*@Test
    public void deberiaAgregarPalabrasClaveAIniciativa() {
    	try {
    		List<String> palabrasTest= new ArrayList<String>();
    		palabrasTest.add("test");
    		palabrasTest.add("intento");
    		palabrasTest.add("ayudeme profe");
    		Iniciativa iniciativaDePrueba= serviciosBancoProyectos.consultarIniciativasPorId(000);
    		serviciosBancoProyectos.agregarPalabrasClaveAIniciativa(iniciativaDePrueba2, palabrasTest);
    	}catch(ExcepcionServiciosBancoProyectos e) {
    		fail();
    	}catch (PersistenceException e) {
    		fail();
		}
    }*/
    
    @Test
    public void deberiaLanzarExepecionAlAgregarPalabrasClaveAIniciativaNula() {
    	try {
    		List<String> palabrasTest= new ArrayList<String>();
    		palabrasTest.add("test");
    		palabrasTest.add("intento");
    		palabrasTest.add("ayudeme profe");
    		serviciosBancoProyectos.agregarPalabrasClaveAIniciativa(null, palabrasTest);
    	}catch(ExcepcionServiciosBancoProyectos e) {
    		Assert.assertEquals("La iniciativa no existe", e.getMessage());
    		}
    }
    
    /*@Test
    public void deberiaLanzarExepecionAlAgregarPalabrasClaveNulasAIniciativa() {
    	try {
    		Iniciativa iniciativaDePrueba= serviciosBancoProyectos.consultarIniciativasPorId(000);
    		serviciosBancoProyectos.agregarPalabrasClaveAIniciativa(iniciativaDePrueba, null);
    	}catch(ExcepcionServiciosBancoProyectos e) {
    		Assert.assertEquals("No hay palabras Clave", e.getMessage());
    	} catch (PersistenceException e) {
			e.printStackTrace();
		}
    }*/
    
}