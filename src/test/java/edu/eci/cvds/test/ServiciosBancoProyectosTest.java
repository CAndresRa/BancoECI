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

public class ServiciosBancoProyectosTest {
    @Inject
    private SqlSession sqlSession;

    ServiciosUsuario serviciosUsuario;
	ServiciosIniciativa serviciosIniciativa;
    
    public ServiciosBancoProyectosTest() {
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
    
    @Test
    public void deberiaInsertarIniciativa() {
    	try {
    		List<PalabraClave> palabrasPrueba= new ArrayList<PalabraClave>();
    		List<String> palabrasVacio= new ArrayList<String>();
    		palabrasPrueba.add(new PalabraClave("prueba"));
    		palabrasVacio.add("prueba");
    		Iniciativa iniciativaDePrueba= new Iniciativa("iniciativaDePrueba","prueba","prueba",
    				new SimpleDateFormat("yyyy/MM/dd").parse("2020/04/13"), palabrasPrueba
    				,serviciosUsuario.consultarUsuario("ernesto.camacho@mail.escuelaing.edu.co"));
    		int iniciativasInicio= serviciosIniciativa.consultarIniciativas().size();
    		serviciosIniciativa.insertarIniciativa(iniciativaDePrueba,palabrasVacio);
    		int iniciativasFin= serviciosIniciativa.consultarIniciativas().size();
    		Assert.assertEquals((iniciativasInicio + 1), iniciativasFin);
    	}catch(ExcepcionServiciosBancoProyectos e) {
    		fail();
    	}catch (PersistenceException e) {
    		fail();
		} catch (ParseException e) {
			fail();
		}
    }
    
    @Test(expected = ExcepcionServiciosBancoProyectos.class)
    public void deberiaLanzarExcepticonAlIntnentarInsertarIniciativaDescripcionNula() throws ExcepcionServiciosBancoProyectos, PersistenceException {
		List<PalabraClave> palabrasPrueba= new ArrayList<PalabraClave>();
		List<String> palabrasVacio= new ArrayList<String>();
		palabrasPrueba.add(new PalabraClave("prueba"));
		Iniciativa iniciativaDePrueba;
		try {
			iniciativaDePrueba = new Iniciativa(000,"prueba",null,"prueba",
					new SimpleDateFormat("yyyy/MM/dd").parse("2020/04/13"), palabrasPrueba);
			serviciosIniciativa.insertarIniciativa(iniciativaDePrueba,palabrasVacio);
		} catch (ParseException e) {
			e.printStackTrace();
		}		
    }
    
    @Test(expected = ExcepcionServiciosBancoProyectos.class)
    public void deberiaLanzarExcepticonAlIntnentarInsertarIniciativaFechaNula() throws ExcepcionServiciosBancoProyectos, PersistenceException {
    	List<PalabraClave> palabrasPrueba= new ArrayList<PalabraClave>();
		List<String> palabrasVacio= new ArrayList<String>();
		palabrasPrueba.add(new PalabraClave("prueba"));
		Iniciativa iniciativaDePrueba= new Iniciativa(000,"prueba","prueba","prueba", null, palabrasPrueba);
		serviciosIniciativa.insertarIniciativa(iniciativaDePrueba,palabrasVacio);
    }
    
    @Test(expected = ExcepcionServiciosBancoProyectos.class)
    public void deberiaLanzarExcepticonAlIntnentarInsertarIniciativaUsuarioNulo() throws ExcepcionServiciosBancoProyectos, PersistenceException {
    	List<PalabraClave> palabrasPrueba= new ArrayList<PalabraClave>();
		List<String> palabrasVacio= new ArrayList<String>();
		palabrasPrueba.add(new PalabraClave("prueba"));
    	try {
    		Iniciativa iniciativaDePrueba= new Iniciativa(000,"prueba","prueba","prueba",
    				new SimpleDateFormat("yyyy/MM/dd").parse("2020/04/13"), palabrasPrueba);
    		serviciosIniciativa.insertarIniciativa(iniciativaDePrueba,palabrasVacio);
		} catch (ParseException e) {
			fail();
		}
    }   	
    
    @Test
    public void deberiaConsultarIniciativaPorId() {
    	try {    		
    		int iniciativaDePruebaid= serviciosIniciativa.consultarIniciativas().get(0).getId();
    		Iniciativa iniciativaDePrueba= serviciosIniciativa.consultarIniciativasPorId(iniciativaDePruebaid);
    		Assert.assertEquals(serviciosUsuario.consultarUsuario("ernesto.camacho@mail.escuelaing.edu.co"), iniciativaDePrueba.getUsuario());
    	}catch(ExcepcionServiciosBancoProyectos e) {
    		fail();
    	}
    }
    
    @Test
    public void deberiaAgregarPalabrasClaveAIniciativa() {
    	try {
    		List<String> palabrasTest= new ArrayList<String>();
    		palabrasTest.add("test");
    		palabrasTest.add("intento");
    		palabrasTest.add("ayudeme profe");
    		int iniciativaDePruebaid= serviciosIniciativa.consultarIniciativas().get(0).getId();
    		Iniciativa iniciativaDePrueba= serviciosIniciativa.consultarIniciativasPorId(iniciativaDePruebaid);
    		serviciosIniciativa.agregarPalabrasClaveAIniciativa(iniciativaDePrueba, palabrasTest);
    	}catch(ExcepcionServiciosBancoProyectos e) {
    		fail();
    	}
    }
   
    @Test(expected = ExcepcionServiciosBancoProyectos.class)
    public void deberiaLanzarExepecionAlAgregarPalabrasClaveAIniciativaNula() throws ExcepcionServiciosBancoProyectos {
    	List<String> palabrasTest= new ArrayList<String>();
		palabrasTest.add("test");
		palabrasTest.add("intento");
		palabrasTest.add("ayudeme profe");
		serviciosIniciativa.agregarPalabrasClaveAIniciativa(null, palabrasTest);
    }    
    
    @Test
    public void deberiaConsultarIniciativaPorPalabrasClave() {
    	try {    		
    		int iniciativaDePruebaid= serviciosIniciativa.consultarIniciativas().get(0).getId();
    		Iniciativa iniciativaDePrueba= serviciosIniciativa.consultarIniciativasPorId(iniciativaDePruebaid);
    		List<String> palabrasBusqueda= new ArrayList<String>();
    		palabrasBusqueda.add("prueba");
    		List<Iniciativa> iniciativaDeBusqueda= serviciosIniciativa.consultarIniciativasPorPalabrasClaves(palabrasBusqueda);
    		Assert.assertEquals(1,iniciativaDeBusqueda.size());
    		Assert.assertEquals(serviciosUsuario.consultarUsuario("ernesto.camacho@mail.escuelaing.edu.co"), iniciativaDeBusqueda.get(0).getUsuario());
    	}catch(ExcepcionServiciosBancoProyectos e) {
    		fail();
    	}
    }
    
    @Test
    public void deberiaPermitirCambiarElestadoDeLaIniciativa() {
    	try {
    		int iniciativaDePruebaid= serviciosIniciativa.consultarIniciativas().get(0).getId();
    		Iniciativa iniciativaDePrueba= serviciosIniciativa.consultarIniciativasPorId(iniciativaDePruebaid);
    		serviciosIniciativa.cambiarEstadoAiniciativa("nuevo", iniciativaDePrueba);
    		int iniciativaDePruebaid2= serviciosIniciativa.consultarIniciativas().get(0).getId();
    		Iniciativa iniciativaDePrueba2= serviciosIniciativa.consultarIniciativasPorId(iniciativaDePruebaid2);
    		Assert.assertEquals("nuevo", iniciativaDePrueba2.getEstado());
    	}catch(ExcepcionServiciosBancoProyectos e) {
    		fail();
    	}
    }
    
    @Test(expected = ExcepcionServiciosBancoProyectos.class)
    public void deberiaLanzarExcepcionAlCambiarElestadoDeLaIniciativaANulo() throws ExcepcionServiciosBancoProyectos {
    	int iniciativaDePruebaid= serviciosIniciativa.consultarIniciativas().get(0).getId();
		Iniciativa iniciativaDePrueba= serviciosIniciativa.consultarIniciativasPorId(iniciativaDePruebaid);
		serviciosIniciativa.cambiarEstadoAiniciativa(null, iniciativaDePrueba);
    }
  
    @Test
    public void deberiaConsultarIniciativasPorArea() {
    	try {
    		List<Iniciativa> lista= serviciosIniciativa.consultarIniciativasPorArea("Pruebas");
    	}catch(ExcepcionServiciosBancoProyectos e) {
    		fail();
    	}
    }
    
    @Test
    public void deberiaConsultarNumeroIniciativasPorArea() {
    	try {
       		int numero= serviciosIniciativa.consultarNumeroDeIniciativasPorArea("Pruebas");
    		Assert.assertEquals(4, numero);
    	}catch(ExcepcionServiciosBancoProyectos e) {
    		fail();
    	}
    }
    
    @Test
    public void deberiaAgregarIniciativaRelacionadaAIniciativa() {
    	try {
    		List<PalabraClave> palabrasPrueba= new ArrayList<PalabraClave>();
    		List<String> palabrasVacio= new ArrayList<String>();
    		palabrasPrueba.add(new PalabraClave("PruebaRelacion"));
    		palabrasVacio.add("PruebaRelacion");
    		Iniciativa iniciativaDePruebaRelacion= new Iniciativa("iniciativaDePruebaRelacion","PruebaRelacion","PruebaRelacion",
    				new SimpleDateFormat("yyyy/MM/dd").parse("2020/04/13"), palabrasPrueba
    				,serviciosUsuario.consultarUsuario("no.rol@mail.escuelaing.edu.co"));
    		serviciosIniciativa.insertarIniciativa(iniciativaDePruebaRelacion, palabrasVacio);
    		/*#######################################################################################*/
    		serviciosIniciativa.agregarIniciativaRelacionadaAIniciativa(1, 2);
    	}catch(ExcepcionServiciosBancoProyectos e) {
    		fail();
    	} catch (ParseException e) {
    		fail();
		} catch (PersistenceException e) {
			fail();
		}
    }
    
    @Test
    public void deberiaConsultarNumeroIniciativasPorEstado() {
    	try {
       		int numero= serviciosIniciativa.consultarNumeroDeIniciativasPorEstado("Espera");
    		Assert.assertEquals(1, numero);
    	}catch(ExcepcionServiciosBancoProyectos e) {
    		fail();
    	}
    }
    
    @Test
    public void deberiaCambiarEstadoAiniciativa() {
    	try {
       		Iniciativa iniciativa= serviciosIniciativa.consultarIniciativasPorId((3));
       		serviciosIniciativa.cambiarEstadoAiniciativa("nuevo", iniciativa);
       		iniciativa= serviciosIniciativa.consultarIniciativasPorId((3));
    		Assert.assertEquals("nuevo", iniciativa.getEstado());
    	}catch(ExcepcionServiciosBancoProyectos e) {
    		fail();
    	}
    }
    
    @Test(expected = ExcepcionServiciosBancoProyectos.class)
    public void deberiaLanzarExceptionCambiarEstadoNuloAiniciativa() throws ExcepcionServiciosBancoProyectos {
   		Iniciativa iniciativa= serviciosIniciativa.consultarIniciativasPorId((3));
   		serviciosIniciativa.cambiarEstadoAiniciativa(null, iniciativa);
    }
    
   /* @Test
    public void deberiaConsultarIniciativasOrdenadasPorColumna() throws ExcepcionServiciosBancoProyectos {
   		serviciosIniciativa.consultarIniciativasOrdenadasPorColumna("nombre");
    }*/
    
    @Test
    public void deberiaAgregarComentarioAIniciativa() {
    	try {
			//Iniciativa iniciativa= serviciosIniciativa.consultarIniciativasPorId(1);
			Comentario comentario= new Comentario("esto es un comentario", new SimpleDateFormat("yyyy/MM/dd").parse("2020/04/13"), "ernesto.camacho@mail.escuelaing.edu.co", "ernesto", "camacho");
			serviciosIniciativa.agregarComentarioAIniciativa(comentario, 1);
		} catch (ExcepcionServiciosBancoProyectos e) {
			fail();
		} catch (ParseException e) {
			fail();
		}
    	
    }
    
    /*@Test(expected = ExcepcionServiciosBancoProyectos.class)
    public void deberiaLanzarExcepcionAgregarComentarioAIniciativa() {
    	try {
			//Iniciativa iniciativa= serviciosIniciativa.consultarIniciativasPorId(1);
			Comentario comentario= new Comentario();
			serviciosIniciativa.agregarComentarioAIniciativa(comentario, 1);
		} catch (ExcepcionServiciosBancoProyectos e) {
			fail();
		}
    	
    }*/
    
    @Test
    public void deberiaConsultarComentariosPorIniciativa() {
    	try {
			Comentario comentario= new Comentario("esto es un comentario", new SimpleDateFormat("yyyy/MM/dd").parse("2020/04/13"), "ernesto.camacho@mail.escuelaing.edu.co", "ernesto", "camacho");
			serviciosIniciativa.agregarComentarioAIniciativa(comentario, 1);
			Assert.assertTrue(serviciosIniciativa.consultarComentariosPorIniciativa(1).size()>= 1);
		} catch (ExcepcionServiciosBancoProyectos e) {
			fail();
		} catch (ParseException e) {
			fail();
		}
    	
    }
    
    @Test
    public void deberiaModificarIniciativaCuandoIngresaNombreYPalabrasValidasValidacionNombre() throws ExcepcionServiciosBancoProyectos {
    	String nombre= "prueba";
    	String palabras= "uno,dos,tres";
    	List<String> palabrasListas = Arrays.asList(palabras.split(","));
    	Iniciativa iniciativa= serviciosIniciativa.consultarIniciativasPorId(1);
    	serviciosIniciativa.eliminarPalabrasClaveDeUnaIniciativa(iniciativa);
    	serviciosIniciativa.modificarIniciativa(nombre, iniciativa);
    	iniciativa= serviciosIniciativa.consultarIniciativasPorId(1);
    	serviciosIniciativa.agregarPalabrasClaveAIniciativa(iniciativa, palabrasListas);
    	Assert.assertEquals("prueba", iniciativa.getNombre());
    }
    
    @Test
    public void deberiaModificarIniciativaCuandoIngresaNombreYPalabrasValidasValidacionPalabras() throws ExcepcionServiciosBancoProyectos {
    	String nombre= "prueba";
    	String palabras= "uno,dos,tres";
    	List<String> palabrasListas = Arrays.asList(palabras.split(","));
    	Iniciativa iniciativa= serviciosIniciativa.consultarIniciativasPorId(1);
    	serviciosIniciativa.eliminarPalabrasClaveDeUnaIniciativa(iniciativa);
    	serviciosIniciativa.modificarIniciativa(nombre, iniciativa);
    	iniciativa= serviciosIniciativa.consultarIniciativasPorId(1);
    	serviciosIniciativa.agregarPalabrasClaveAIniciativa(iniciativa, palabrasListas);
    	iniciativa= serviciosIniciativa.consultarIniciativasPorId(1);
    	Assert.assertEquals(3, iniciativa.getPalabras_clave().size());
    }
    
    /*@Test
    public void deberiaModificarIniciativaCuandoIngresaNombreValidoValidacionConservacionPalabrasClave() throws ExcepcionServiciosBancoProyectos {
    	Iniciativa iniciativa= serviciosIniciativa.consultarIniciativasPorId(2);
    	String temporal = "";
        for(PalabraClave palabrasclave : iniciativa.getPalabras_clave()){
            temporal = temporal + palabrasclave.getPalabra() + ',';
        }
        List<String> palabrasListas = Arrays.asList(temporal.split(","));
    	String nombre= "prueba";
    	iniciativa= serviciosIniciativa.consultarIniciativasPorId(2);
    	serviciosIniciativa.modificarIniciativa(nombre, iniciativa);
    	iniciativa= serviciosIniciativa.consultarIniciativasPorId(2);
    	Assert.assertEquals("Prueba 1", iniciativa.getPalabras_clave().get(0));
    }*/
    
    @Test
    public void deberiaModificarIniciativaCuandoIngresaDescripcion() throws ExcepcionServiciosBancoProyectos {
    	Iniciativa iniciativa= serviciosIniciativa.consultarIniciativasPorId(1);
    	String descripcion= "nueva descripcion";
    	serviciosIniciativa.modificarDescripcion(descripcion, iniciativa);
    	iniciativa= serviciosIniciativa.consultarIniciativasPorId(1);
    	Assert.assertEquals(descripcion, iniciativa.getDescripcion());
    }
}