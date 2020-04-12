package edu.eci.cvds.view;


import com.google.inject.Inject;
import edu.eci.cvds.samples.entities.Iniciativa;
import edu.eci.cvds.samples.entities.Usuario;
import edu.eci.cvds.samples.services.ExcepcionServiciosBancoProyectos;
import edu.eci.cvds.samples.services.ServiciosBancoProyectos;
import javax.enterprise.context.SessionScoped;
import javax.faces.bean.ManagedBean;
import java.util.Date;

@SuppressWarnings("deprecation")
@ManagedBean(name = "iniciativaBean")
@SessionScoped
public class IniciativaBean extends BasePageBean{
    @Inject
    private ServiciosBancoProyectos serviciosBancoProyectos;
    private String estado;
    private Iniciativa iniciativa;
    private Date date;
    public void agregarIniciativa(String nombre, String descripcion, String palabras, String email) throws ExcepcionServiciosBancoProyectos {
        String[] palabrasListas = palabras.split(",");
        this.iniciativa = new Iniciativa(nombre,descripcion,estado);
        this.date = new Date((new java.util.Date()).getTime());
        Usuario usuario = serviciosBancoProyectos.consultarUsuario(email);
        serviciosBancoProyectos.registrarIniciativaAUsuario(date, iniciativa, usuario, palabrasListas);
    }
    public String getEstado() {
        return estado;
    }
    public void setEstado(String estado) {
        this.estado = estado;
    }
}

