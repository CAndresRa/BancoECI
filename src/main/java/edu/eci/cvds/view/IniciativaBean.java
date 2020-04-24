package edu.eci.cvds.view;


import com.google.inject.Inject;
import edu.eci.cvds.sampleprj.dao.PersistenceException;
import edu.eci.cvds.samples.entities.Iniciativa;
import edu.eci.cvds.samples.entities.Usuario;
import edu.eci.cvds.samples.services.ExcepcionServiciosBancoProyectos;
import edu.eci.cvds.samples.services.ServiciosIniciativa;
import edu.eci.cvds.samples.services.ServiciosUsuario;
import org.primefaces.model.chart.PieChartModel;
//import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@SuppressWarnings("deprecation")
@ManagedBean(name = "iniciativaBean")
@ViewScoped
public class IniciativaBean extends BasePageBean implements Serializable {
    @Inject
    private ServiciosIniciativa serviciosIniciativa;
    @Inject
    private ServiciosUsuario serviciosUsuario;
    private String estado;
    private Iniciativa iniciativa;
    private List<Iniciativa> iniciativasPorPalabra;
    private String message;
    private PieChartModel model;

    public String[] getIniciativasRelacionadas() {
        return iniciativasRelacionadas;
    }

    public void setIniciativasRelacionadas(String[] iniciativasRelacionadas) {
        this.iniciativasRelacionadas = iniciativasRelacionadas;
    }

    public List<Iniciativa> getIniciativasRelacionadasList() {
        return iniciativasRelacionadasList;
    }

    public void setIniciativasRelacionadasList(List<Iniciativa> iniciativasRelacionadasList) {
        this.iniciativasRelacionadasList = iniciativasRelacionadasList;
    }

    private String[] iniciativasRelacionadas;
    private List<Iniciativa> iniciativasRelacionadasList;

    public void agregarIniciativa(String nombre, String descripcion, String palabras, String email) throws ExcepcionServiciosBancoProyectos, PersistenceException {
        try {
            List<String> palabrasListas = Arrays.asList(palabras.split(","));
            Usuario usuario = serviciosUsuario.consultarUsuario(email);
            this.iniciativa = new Iniciativa(nombre, descripcion, estado, new Date((new java.util.Date()).getTime()), usuario);
            serviciosIniciativa.insertarIniciativa(iniciativa, palabrasListas);
            this.message = "La iniciativa se registro correctamente";
        }
        catch (Exception e) {
            this.message = "Hubo un error registrando iniciativa, intente nuevamente";
        }

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
            this.message = "El estado se actualizo satisfactoriamente, consulte nuevamente para confirmar los cambios";
        } catch (ExcepcionServiciosBancoProyectos excepcionServiciosBancoProyectos) {
            this.message = "El estado no se actualizo correctamente, intentelo nuevamente";
            throw new ExcepcionServiciosBancoProyectos("Error cambiando el estado de la iniciativa");
        }
    }

    public void agregarIniciativaRelacionadaAIniciativa() throws ExcepcionServiciosBancoProyectos {
        try {
            if (iniciativasRelacionadasList.size() > 1) {
                Iniciativa iniciativaPrincipal = iniciativasRelacionadasList.get(0);
                for (int i = 1; i < iniciativasRelacionadasList.size(); i++) {
                    serviciosIniciativa.agregarIniciativaRelacionadaAIniciativa(iniciativaPrincipal.getId(), iniciativasRelacionadasList.get(i).getId());
                }
                this.message = "Las iniciativas se asociaron correctamente";
            }
            else{
                this.message = "Seleccione 2 o mas iniciativas";
            }
        } catch (ExcepcionServiciosBancoProyectos excepcionServiciosBancoProyectos){
            this.message = "Hubo un error asociando iniciativas, intentelo nuevamente";
        }


    }

    public PieChartModel generarEstadistica() throws ExcepcionServiciosBancoProyectos {
        model = new PieChartModel();
        model.set("Finanzas", serviciosIniciativa.consultarNumeroDeIniciativasPorArea("Finanzas"));
        model.set("Ventas", serviciosIniciativa.consultarNumeroDeIniciativasPorArea("Ventas"));
        model.set("Proyectos", serviciosIniciativa.consultarNumeroDeIniciativasPorArea("Proyectos"));
        model.set("Innovacion", serviciosIniciativa.consultarNumeroDeIniciativasPorArea("Innovacion"));
        model.setTitle("");
        model.setShowDataLabels(true);
        model.setDataLabelFormatString("%dK");
        model.setLegendPosition("e");
        model.setShowDatatip(true);
        model.setShowDataLabels(true);
        model.setDataFormat("value");
        model.setDataLabelFormatString("%d");
        model.setSeriesColors("aaf,afa,faa,ffa");
        return model;
    }

    public void info() {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, message, "PrimeFaces Rocks."));
    }

    public List<Iniciativa> getIniciativasPorPalabra() {
        return iniciativasPorPalabra;
    }

    public void setIniciativasPorPalabra(List<Iniciativa> iniciativasPorPalabra) {
        this.iniciativasPorPalabra = iniciativasPorPalabra;
    }

    public PieChartModel getModel() {
        return model;
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

