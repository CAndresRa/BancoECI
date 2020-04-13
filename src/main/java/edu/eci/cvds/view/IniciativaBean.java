package edu.eci.cvds.view;


import com.google.inject.Inject;
import edu.eci.cvds.samples.entities.Iniciativa;
import edu.eci.cvds.samples.entities.Usuario;
import edu.eci.cvds.samples.services.ExcepcionServiciosBancoProyectos;
import edu.eci.cvds.samples.services.ServiciosBancoProyectos;
import javax.enterprise.context.SessionScoped;
import javax.faces.bean.ManagedBean;
import java.util.Date;
import java.util.List;

@SuppressWarnings("deprecation")
@ManagedBean(name = "iniciativaBean")
@SessionScoped
public class IniciativaBean extends BasePageBean {
    @Inject
    private ServiciosBancoProyectos serviciosBancoProyectos;
    private String estado;
    private Iniciativa iniciativa;
    private Date date;

    public void agregarIniciativa(String nombre, String descripcion, String palabras, String email) throws ExcepcionServiciosBancoProyectos {
        String[] palabrasListas = palabras.split(",");
        this.iniciativa = new Iniciativa(nombre, descripcion, estado);
        this.date = new Date((new java.util.Date()).getTime());
        Usuario usuario = serviciosBancoProyectos.consultarUsuario(email);
        serviciosBancoProyectos.registrarIniciativaAUsuario(date, iniciativa, usuario, palabrasListas);
    }

    public List<Iniciativa> consultarIniciativasBasico() throws ExcepcionServiciosBancoProyectos {
        try {
            return serviciosBancoProyectos.consultarIniciativas();
        } catch (ExcepcionServiciosBancoProyectos excepcionServiciosBancoProyectos) {
            throw new ExcepcionServiciosBancoProyectos("Error al consultar iniciativa");
        }
    }

    public Iniciativa consultarIniciativasPorId(int id) throws ExcepcionServiciosBancoProyectos {
        try{
            this.iniciativa = serviciosBancoProyectos.consultarIniciativasPorId(id);
            return iniciativa;
        }catch (ExcepcionServiciosBancoProyectos excepcionServiciosBancoProyectos){
            throw new ExcepcionServiciosBancoProyectos("Error al consultar iniciativa por id");
        }
    }

    public void cambiarEstadoAiniciativa(String estado, Iniciativa iniciativa) throws ExcepcionServiciosBancoProyectos {
        try{
            serviciosBancoProyectos.cambiarEstadoAiniciativa(estado,iniciativa);
        } catch (ExcepcionServiciosBancoProyectos excepcionServiciosBancoProyectos) {
            throw new ExcepcionServiciosBancoProyectos("Error cambiando el estado de la iniciativa");
        }
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

