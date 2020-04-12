package edu.eci.cvds.test;


import com.google.inject.Inject;

import edu.eci.cvds.sampleprj.dao.PersistenceException;
import edu.eci.cvds.samples.entities.Iniciativa;
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
    		Iniciativa iniciativaDePrueba= new Iniciativa(000,"prueba","prueba","prueba");
    		serviciosBancoProyectos.insertarIniciativa(iniciativaDePrueba);
    	}catch(ExcepcionServiciosBancoProyectos e) {
    		fail();
    	}catch (PersistenceException e) {
    		fail();
		}
    }
    
    @Test
    public void deberiaLanzarExcepticonAlIntnentarInsertarIniciativa() {
    	try {
    		Iniciativa iniciativaDePrueba= new Iniciativa(000,"prueba",null,"prueba");
    		serviciosBancoProyectos.insertarIniciativa(iniciativaDePrueba);
    	}catch(ExcepcionServiciosBancoProyectos e) {
    		Assert.assertEquals("La iniciativa no tiene toda la informacion necesaria", e.getMessage());
    	}catch (PersistenceException e) {
    		fail();
		}
    }
    
    @Test
    public void deberiaAgregarPalabrasClaveAIniciativa() {
    	try {
    		String[] palabrasTest= {"prueba","test","intento","ayudeme profe"};
    		Iniciativa iniciativaDePrueba2= new Iniciativa("prueba2","prueba2","prueba2");
    		serviciosBancoProyectos.insertarIniciativa(iniciativaDePrueba2);
    		serviciosBancoProyectos.agregarPalabrasClaveAIniciativa(iniciativaDePrueba2, palabrasTest);
    	}catch(ExcepcionServiciosBancoProyectos e) {
    		fail();
    	}catch (PersistenceException e) {
    		fail();
		}
    }
    
    @Test
    public void deberiaLanzarExepecionAlAgregarPalabrasClaveAIniciativaNula() {
    	try {
    		String[] palabrasTest= {"prueba","test","intento","ayudeme profe"};
    		serviciosBancoProyectos.agregarPalabrasClaveAIniciativa(null, palabrasTest);
    	}catch(ExcepcionServiciosBancoProyectos e) {
    		Assert.assertEquals("La iniciativa no existe", e.getMessage());
    		}
    }
    
    @Test
    public void deberiaLanzarExepecionAlAgregarPalabrasClaveNulasAIniciativa() {
    	try {
    		Iniciativa iniciativaDePrueba3= new Iniciativa("prueba3","prueba3","prueba3");
    		serviciosBancoProyectos.insertarIniciativa(iniciativaDePrueba3);
    		serviciosBancoProyectos.agregarPalabrasClaveAIniciativa(iniciativaDePrueba3, null);
    	}catch(ExcepcionServiciosBancoProyectos e) {
    		Assert.assertEquals("No hay palabras Clave", e.getMessage());
    	} catch (PersistenceException e) {
			e.printStackTrace();
		}
    }
    
    @Test
    public void deberiaLanzarExepecionAlAgregarPalabrasClaveVaciaAIniciativa() {
    	try {
    		String[] palabrasTest= {};
    		Iniciativa iniciativaDePrueba4= new Iniciativa("prueba4","prueba4","prueba4");
    		serviciosBancoProyectos.insertarIniciativa(iniciativaDePrueba4);
    		serviciosBancoProyectos.agregarPalabrasClaveAIniciativa(iniciativaDePrueba4, palabrasTest);
    	}catch(ExcepcionServiciosBancoProyectos e) {
    		Assert.assertEquals("No hay palabras Clave", e.getMessage());
    	} catch (PersistenceException e) {
			e.printStackTrace();
		}
    }

    @Test
    public void deberiaRegistrarIniciaivaAUsuario() {
    	try {
    		Iniciativa iniciativaDePrueba5= new Iniciativa("prueba5","prueba5","prueba5");
    		String[] palabrasTest= {"prueba","test","intento","ayudeme profe"};
    		//serviciosBancoProyectos.insertarIniciativa(iniciativaDePrueba5);
    		Usuario usuarioPrueba= serviciosBancoProyectos.consultarUsuario("ernesto.camacho@mail.escuelaing.edu.co");
    		serviciosBancoProyectos.registrarIniciativaAUsuario(new SimpleDateFormat("yyyy/MM/dd").parse("2019/09/28"), 
    														iniciativaDePrueba5, usuarioPrueba, palabrasTest);
    	}catch(ExcepcionServiciosBancoProyectos e) {
    		fail();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    @Test
    public void deberiaLanzarExceptionAlRegistrarIniciaivaNulaAUsuario() {
    	try {
    		String[] palabrasTest= {"prueba","test","intento","ayudeme profe"};
    		//serviciosBancoProyectos.insertarIniciativa(iniciativaDePrueba5);
    		Usuario usuarioPrueba= serviciosBancoProyectos.consultarUsuario("ernesto.camacho@mail.escuelaing.edu.co");
    		serviciosBancoProyectos.registrarIniciativaAUsuario(new SimpleDateFormat("yyyy/MM/dd").parse("2019/09/28"), 
    														null, usuarioPrueba, palabrasTest);
    	}catch(ExcepcionServiciosBancoProyectos e) {
    		Assert.assertEquals("La iniciativa no existe", e.getMessage());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    @Test
    public void deberiaLanzarExceptionAlRegistrarIniciaivaAUsuarioNulo() {
    	try {
    		String[] palabrasTest= {"prueba","test","intento","ayudeme profe"};
    		Iniciativa iniciativaDePrueba5= new Iniciativa("prueba5","prueba5","prueba5");
    		//serviciosBancoProyectos.insertarIniciativa(iniciativaDePrueba5);
    		serviciosBancoProyectos.registrarIniciativaAUsuario(new SimpleDateFormat("yyyy/MM/dd").parse("2019/09/28"), 
    															iniciativaDePrueba5, null, palabrasTest);
    	}catch(ExcepcionServiciosBancoProyectos e) {
    		Assert.assertEquals("El usuario no existe", e.getMessage());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    @Test
    public void deberiaLanzarExceptionAlRegistrarIniciaivaAUsuarioConFechaNula() {
    	try {
    		Iniciativa iniciativaDePrueba5= new Iniciativa("prueba5","prueba5","prueba5");
    		String[] palabrasTest= {"prueba","test","intento","ayudeme profe"};
    		//serviciosBancoProyectos.insertarIniciativa(iniciativaDePrueba5);
    		Usuario usuarioPrueba= serviciosBancoProyectos.consultarUsuario("ernesto.camacho@mail.escuelaing.edu.co");
    		serviciosBancoProyectos.registrarIniciativaAUsuario(null, iniciativaDePrueba5, usuarioPrueba, palabrasTest);
    	}catch(ExcepcionServiciosBancoProyectos e) {
    		Assert.assertEquals("La fecha es nula", e.getMessage());
    	}
    }
}
