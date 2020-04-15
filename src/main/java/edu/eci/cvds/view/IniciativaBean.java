package edu.eci.cvds.view;


import com.google.inject.Inject;
import edu.eci.cvds.sampleprj.dao.PersistenceException;
import edu.eci.cvds.samples.entities.Iniciativa;
import edu.eci.cvds.samples.entities.Usuario;
import edu.eci.cvds.samples.services.ExcepcionServiciosBancoProyectos;
import edu.eci.cvds.samples.services.ServiciosIniciativa;
import edu.eci.cvds.samples.services.ServiciosUsuario;

import javax.enterprise.context.SessionScoped;
import javax.faces.bean.ManagedBean;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@SuppressWarnings("deprecation")
@ManagedBean(name = "iniciativaBean")
@SessionScoped
public class IniciativaBean extends BasePageBean {
    @Inject
    private ServiciosIniciativa serviciosIniciativa;
    private ServiciosUsuario serviciosUsuario;
    private String estado;
    private Iniciativa iniciativa;
    private List<Iniciativa> iniciativasPorPalabra;
    private Date date;

    public void agregarIniciativa(String nombre, String descripcion, String palabras, String email) throws ExcepcionServiciosBancoProyectos, PersistenceException {
        List<String> palabrasListas = Arrays.asList(palabras.split(","));
        Usuario usuario = serviciosUsuario.consultarUsuario(email);
        this.iniciativa = new Iniciativa(nombre, descripcion, estado,new Date((new java.util.Date()).getTime()),usuario);
        serviciosIniciativa.insertarIniciativa(iniciativa, palabrasListas);
    }

    public List<Iniciativa> consultarIniciativasBasico() throws ExcepcionServiciosBancoProyectos {
        try {
            return serviciosIniciativa.consultarIniciativas();
        } catch (ExcepcionServiciosBancoProyectos excepcionServiciosBancoProyectos) {
            throw new ExcepcionServiciosBancoProyectos("Error al consultar iniciativa");
        }
    }

    public Iniciativa consultarIniciativasPorId(int id) throws ExcepcionServiciosBancoProyectos {
        try{
            this.iniciativa = serviciosIniciativa.consultarIniciativasPorId(id);
            return iniciativa;
        }catch (ExcepcionServiciosBancoProyectos excepcionServiciosBancoProyectos){
            throw new ExcepcionServiciosBancoProyectos("Error al consultar iniciativa por id");
        }
    }

    public List<Iniciativa> consultarIniciativasPorPalabras(String palabras) throws ExcepcionServiciosBancoProyectos{
        try {
            List<String> palabrasListas = Arrays.asList(palabras.split(","));
            this.iniciativasPorPalabra = serviciosIniciativa.consultarIniciativasPorPalabrasClaves(palabrasListas);
            return  iniciativasPorPalabra;
        } catch (ExcepcionServiciosBancoProyectos excepcionServiciosBancoProyectos){
            throw new ExcepcionServiciosBancoProyectos("No se encuentran iniciativas con esas palabras clave");
        }
    }

    public void cambiarEstadoAiniciativa(String estado, Iniciativa iniciativa) throws ExcepcionServiciosBancoProyectos {
        try{
            serviciosIniciativa.cambiarEstadoAiniciativa(estado,iniciativa);
        } catch (ExcepcionServiciosBancoProyectos excepcionServiciosBancoProyectos) {
            throw new ExcepcionServiciosBancoProyectos("Error cambiando el estado de la iniciativa");
        }
    }

    public List<Iniciativa> getIniciativasPorPalabra() {
        return iniciativasPorPalabra;
    }

    public void setIniciativasPorPalabra(List<Iniciativa> iniciativasPorPalabra) {
        this.iniciativasPorPalabra = iniciativasPorPalabra;
    }

    public Iniciativa getIniciativa() {
        return iniciativa;
    }

    public void setIniciativa(Iniciativa iniciativa) {
        this.iniciativa = iniciativa;
    }

    public String getEstado() {
        return estado;
    }
    public void setEstado(String estado) {
        this.estado = estado;
    }
}

