package edu.eci.cvds.view;

import com.google.inject.Inject;
import edu.eci.cvds.samples.entities.Comentario;
import edu.eci.cvds.samples.services.ExcepcionServiciosBancoProyectos;
import edu.eci.cvds.samples.services.ServiciosIniciativa;
import edu.eci.cvds.samples.services.ServiciosUsuario;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@SuppressWarnings("deprecation")
@ManagedBean(name = "comentarioBean")
@RequestScoped
public class ComentarioBean extends BasePageBean implements Serializable {
    @Inject
    private ServiciosIniciativa serviciosIniciativa;
    @Inject
    private ServiciosUsuario serviciosUsuario;
    private String message;
    private List<Comentario> comentarios;

    public void agregarComentarioAIniciativa(String comentario) throws ExcepcionServiciosBancoProyectos {
        try {
            FacesContext facesContext = FacesContext.getCurrentInstance();
            HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(true);
            long docu = serviciosUsuario.consultarUsuario(session.getAttribute("correo").toString()).getDocumento();
            Integer idIniciativa = Integer.parseInt(session.getAttribute("selectedIniciativa").toString());
            Date fecha = new Date((new java.util.Date()).getTime());
            serviciosIniciativa.agregarComentarioAIniciativa(fecha, comentario, docu, idIniciativa);
            this.message = "Comentario agregado exitosamente";
        } catch (ExcepcionServiciosBancoProyectos e){
            this.message = "Hubo un error agregando el comentario";
        }
    }

    public void info() {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, message, "PrimeFaces Rocks."));
    }

    public List<Comentario> consultarComentariosDeIniciativa() throws ExcepcionServiciosBancoProyectos {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(true);
        Integer idIniciativa = Integer.parseInt(session.getAttribute("selectedIniciativa").toString());
        this.comentarios = serviciosIniciativa.consultarComentariosPorIniciativa(idIniciativa);
        return comentarios;
    }

    public List<Comentario> getComentarios() {
        return comentarios;
    }

    public void setComentarios(List<Comentario> comentarios) {
        this.comentarios = comentarios;
    }
}
