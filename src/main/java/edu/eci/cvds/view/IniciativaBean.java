package edu.eci.cvds.view;


import com.google.inject.Inject;
import edu.eci.cvds.sampleprj.dao.PersistenceException;
import edu.eci.cvds.samples.entities.Iniciativa;
import edu.eci.cvds.samples.entities.Usuario;
import edu.eci.cvds.samples.services.ExcepcionServiciosBancoProyectos;
import edu.eci.cvds.samples.services.ServiciosIniciativa;
import edu.eci.cvds.samples.services.ServiciosUsuario;
import org.primefaces.model.chart.PieChartModel;
import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@SuppressWarnings("deprecation")
@ManagedBean(name = "iniciativaBean")
@RequestScoped
public class IniciativaBean extends BasePageBean implements Serializable {


    @Inject
    private ServiciosIniciativa serviciosIniciativa;
    @Inject
    private ServiciosUsuario serviciosUsuario;
    private String estado;
    private Iniciativa iniciativa;
    private Iniciativa selectedIniciativa;
    private List<Iniciativa> iniciativasPorPalabra;
    private String message;
    private PieChartModel model;
    private List<Iniciativa> iniciativasRelacionadasList;
    private List<Iniciativa> iniciativasBusquedaBasica;

    @PostConstruct
    public void init(){
        super.init();
        try{
            iniciativasBusquedaBasica = new ArrayList<>();
            iniciativasBusquedaBasica = serviciosIniciativa.consultarIniciativas();
        } catch (ExcepcionServiciosBancoProyectos e) {
            e.printStackTrace();
        }
    }
    public void agregarIniciativa(String nombre, String descripcion, String palabras, String email) throws ExcepcionServiciosBancoProyectos, PersistenceException {
        try {
            List<String> palabrasListas = Arrays.asList(palabras.split(","));
            Usuario usuario = serviciosUsuario.consultarUsuario(email);
            this.iniciativa = new Iniciativa(nombre, descripcion, "Espera", new Date((new java.util.Date()).getTime()), usuario);
            serviciosIniciativa.insertarIniciativa(iniciativa, palabrasListas);
            this.message = "La iniciativa se registro correctamente";
        }
        catch (Exception e) {
            this.message = "Hubo un error registrando iniciativa, intente nuevamente";
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

    public Iniciativa consultarIniciativaSolita() throws ExcepcionServiciosBancoProyectos {
        try{
            FacesContext facesContext = FacesContext.getCurrentInstance();
            HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(true);
            Integer idIniciativa = Integer.parseInt(session.getAttribute("selectedIniciativa").toString());
            this.iniciativa = serviciosIniciativa.consultarIniciativasPorId(idIniciativa);
            return iniciativa;
        }catch (ExcepcionServiciosBancoProyectos excepcionServiciosBancoProyectos){
            throw new ExcepcionServiciosBancoProyectos("Error al consultar iniciativa");
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

    public void cambiarEstadoAiniciativa(String estado) throws ExcepcionServiciosBancoProyectos {
        try{
            FacesContext facesContext = FacesContext.getCurrentInstance();
            HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(true);
            Integer idIniciativa = Integer.parseInt(session.getAttribute("selectedIniciativa").toString());
            this.iniciativa = serviciosIniciativa.consultarIniciativasPorId(idIniciativa);
            serviciosIniciativa.cambiarEstadoAiniciativa(estado,iniciativa);
            this.message = "El estado se actualizo satisfactoriamente, consulte nuevamente para confirmar los cambios";
        } catch (ExcepcionServiciosBancoProyectos excepcionServiciosBancoProyectos) {
            this.message = "El estado no se actualizo correctamente, intentelo nuevamente";
            throw new ExcepcionServiciosBancoProyectos("Error cambiando el estado de la iniciativa");
        }
    }

    public void redirectAddComentario() throws IOException {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(true);
        session.setAttribute("selectedIniciativa", selectedIniciativa.getId());
        facesContext.getExternalContext().redirect("../publico/comentariosIniciativa.xhtml");
    }

    public void redirectConsultaComentario() throws IOException {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(true);
        session.setAttribute("selectedIniciativa", selectedIniciativa.getId());
        facesContext.getExternalContext().redirect("../publico/consultarComentarios.xhtml");
    }

    public void redirectModificacionEstadoIniciativa() throws IOException{
        FacesContext facesContext = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(true);
        session.setAttribute("selectedIniciativa", selectedIniciativa.getId());
        facesContext.getExternalContext().redirect("cambioEstado.xhtml");
    }


    public void agregarIniciativaRelacionadaAIniciativa() throws ExcepcionServiciosBancoProyectos {
        int longitudAsosiciaciones = iniciativasRelacionadasList.size();
        try {
            if (longitudAsosiciaciones > 1) {
                for (int i = 0; i < longitudAsosiciaciones; i++) {
                    for(int j = 0; j < longitudAsosiciaciones; j++){
                        if(i != j){
                            serviciosIniciativa.agregarIniciativaRelacionadaAIniciativa(iniciativasRelacionadasList.get(i).getId(),iniciativasRelacionadasList.get(j).getId());
                        }
                    }
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

    public List<Iniciativa> consultarIniciativasRelacionadas() throws ExcepcionServiciosBancoProyectos {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(true);
        Integer idIniciativa = Integer.parseInt(session.getAttribute("selectedIniciativa").toString());
        this.iniciativa = serviciosIniciativa.consultarIniciativasPorId(idIniciativa);
        this.iniciativasRelacionadasList = iniciativa.getIniciativasRelacionadas();
        List<Iniciativa> resultante = new ArrayList<Iniciativa>() {
        };
        for(Iniciativa i : iniciativasRelacionadasList){
            Iniciativa nuevaIni = serviciosIniciativa.consultarIniciativasPorId(i.getId());
            if(!resultante.contains(nuevaIni)) {
                resultante.add(nuevaIni);
            }
        }
        return resultante;
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

    public Iniciativa getSelectedIniciativa() {
        return selectedIniciativa;
    }

    public void setSelectedIniciativa(Iniciativa selectedIniciativa) {
        this.selectedIniciativa = selectedIniciativa;
    }

    public List<Iniciativa> getIniciativasRelacionadasList() {
        return iniciativasRelacionadasList;
    }

    public List<Iniciativa> getIniciativasBusquedaBasica() {
        return iniciativasBusquedaBasica;
    }

    public void setIniciativasBusquedaBasica(List<Iniciativa> iniciativasBusquedaBasica) {
        this.iniciativasBusquedaBasica = iniciativasBusquedaBasica;
    }

    public void setIniciativasRelacionadasList(List<Iniciativa> iniciativasRelacionadasList) {
        this.iniciativasRelacionadasList = iniciativasRelacionadasList;
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

