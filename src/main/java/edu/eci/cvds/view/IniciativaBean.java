package edu.eci.cvds.view;
import com.google.inject.Inject;
import edu.eci.cvds.sampleprj.dao.PersistenceException;
import edu.eci.cvds.samples.entities.Iniciativa;
import edu.eci.cvds.samples.entities.PalabraClave;
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
import javax.persistence.criteria.CriteriaBuilder;
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
    private PieChartModel modelEstado;
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
    public void agregarIniciativa(String nombre, String descripcion, String palabras) throws ExcepcionServiciosBancoProyectos, PersistenceException {
        try {
            FacesContext facesContext = FacesContext.getCurrentInstance();
            HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(true);
            String correoSession = (String) session.getAttribute("username");
            List<String> palabrasListas = Arrays.asList(palabras.split(","));
            Usuario usuario = serviciosUsuario.consultarUsuario(correoSession);
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

    public void redirectAddComentarioUsuario() throws IOException{
        FacesContext facesContext = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(true);
        session.setAttribute("selectedIniciativa", selectedIniciativa.getId());
        facesContext.getExternalContext().redirect("../publico/comentarIniciativaUsuario.xhtml");
    }

    public void redirectConsultaComentario() throws IOException {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(true);
        session.setAttribute("selectedIniciativa", selectedIniciativa.getId());
        facesContext.getExternalContext().redirect("../publico/consultarComentariosUsuarios.xhtml");
    }

    public void redirectConsultarComentarioPublico() throws IOException{
        FacesContext facesContext = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(true);
        session.setAttribute("selectedIniciativa", selectedIniciativa.getId());
        facesContext.getExternalContext().redirect("../publico/consultarComentarios.xhtml");
    }

    public void redirectModificacionDeIniciativa() throws IOException {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(true);
        session.setAttribute("selectedIniciativa", selectedIniciativa.getId());
        if(selectedIniciativa.getEstado().equals("Espera") || selectedIniciativa.getEstado().equals("Revision")){
            facesContext.getExternalContext().redirect("modificarIniciativa.xhtml");
        }
        else{
            this.message = "El estado de la iniciativa no permite cambio";
        }
    }

    public void redirectModificacionEstadoIniciativa() throws IOException{
        FacesContext facesContext = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(true);
        session.setAttribute("selectedIniciativa", selectedIniciativa.getId());
        facesContext.getExternalContext().redirect("cambioEstado.xhtml");
    }

    public void redirectRegistroDeIniciativa() throws IOException{
        FacesContext facesContext = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(true);
        facesContext.getExternalContext().redirect("registrarIniciativa.xhtml");
    }

    public void redirectIniciativaAtributos() throws IOException, ExcepcionServiciosBancoProyectos {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(true);
        String correoSession = (String) session.getAttribute("username");
        facesContext.getExternalContext().redirect("../publico/iniciativaAtributos.xhtml");
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

    public List<Iniciativa> consultarIniciativasDelPropoponente() throws ExcepcionServiciosBancoProyectos {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(true);
        String correoSession = (String) session.getAttribute("username");
        return serviciosIniciativa.consultarIniciativasDelProponente(correoSession);
    }

    public void modificarIniciativa(String nombre, String descripcion, String palabras){
        try{
            FacesContext facesContext = FacesContext.getCurrentInstance();
            HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(true);
            Integer idIniciativa = Integer.parseInt(session.getAttribute("selectedIniciativa").toString());
            this.iniciativa = serviciosIniciativa.consultarIniciativasPorId(idIniciativa);
            if(!nombre.equals("") && !palabras.equals("")) {
                List<String> palabrasListas = Arrays.asList(palabras.split(","));
                serviciosIniciativa.eliminarPalabrasClaveDeUnaIniciativa(iniciativa);
                serviciosIniciativa.modificarIniciativa(nombre, iniciativa);
                this.iniciativa = serviciosIniciativa.consultarIniciativasPorId(idIniciativa);
                serviciosIniciativa.agregarPalabrasClaveAIniciativa(iniciativa, palabrasListas);
            }
            else if (!nombre.equals("") && palabras.equals("")){
                String temporal = "";
                for(PalabraClave palabrasclave : iniciativa.getPalabras_clave()){
                    temporal = temporal + palabrasclave.getPalabra() + ',';
                }
                List<String> palabrasListas = Arrays.asList(temporal.split(","));
                System.out.println(palabrasListas);
                serviciosIniciativa.eliminarPalabrasClaveDeUnaIniciativa(iniciativa);
                serviciosIniciativa.modificarIniciativa(nombre, iniciativa);
                this.iniciativa = serviciosIniciativa.consultarIniciativasPorId(idIniciativa);
                serviciosIniciativa.agregarPalabrasClaveAIniciativa(iniciativa, palabrasListas);
            }
            else if (nombre.equals("") && !palabras.equals("")){
                serviciosIniciativa.eliminarPalabrasClaveDeUnaIniciativa(iniciativa);
                List<String> palabrasListas = Arrays.asList(palabras.split(","));
                serviciosIniciativa.agregarPalabrasClaveAIniciativa(iniciativa, palabrasListas);
            }
            if(!descripcion.equals("")) {
                serviciosIniciativa.modificarDescripcion(descripcion, iniciativa);
            }
            this.message = "La iniciativa se modifico satisfactoriamente";

        } catch (ExcepcionServiciosBancoProyectos excepcionServiciosBancoProyectos) {
            this.message = "Hubo un problema modificando la iniciativa, verifique los datos";
            excepcionServiciosBancoProyectos.printStackTrace();
        }
    }

    public void agregarVoto(String correo) throws ExcepcionServiciosBancoProyectos {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(true);
        Integer idIniciativa = Integer.parseInt(session.getAttribute("selectedIniciativa").toString());
        this.iniciativa = serviciosIniciativa.consultarIniciativasPorId(idIniciativa);
        Usuario usuario = serviciosUsuario.consultarUsuario(correo);
        if(serviciosIniciativa.confirmarSiYaVoto(usuario, iniciativa) == 0) {
            serviciosIniciativa.agregarVoto(usuario, iniciativa);
            this.message = "Su voto ha sido registrado";
        }
        else {
            this.message = "No es posible votar mas de una vez por la misma iniciativa";
        }
    }

    public void agregarVoto() throws ExcepcionServiciosBancoProyectos {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(true);
        Integer idIniciativa = Integer.parseInt(session.getAttribute("selectedIniciativa").toString());
        this.iniciativa = serviciosIniciativa.consultarIniciativasPorId(idIniciativa);
        String correoSession = (String) session.getAttribute("username");
        Usuario usuario = serviciosUsuario.consultarUsuario(correoSession);
        if(serviciosIniciativa.confirmarSiYaVoto(usuario, iniciativa) == 0) {
            serviciosIniciativa.agregarVoto(usuario, iniciativa);
            this.message = "Su voto ha sido registrado";
        }
        else {
            this.message = "No es posible votar mas de una vez por la misma iniciativa";
        }
    }

    public void deleteVoto(String correo) throws ExcepcionServiciosBancoProyectos{
        FacesContext facesContext = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(true);
        Integer idIniciativa = Integer.parseInt(session.getAttribute("selectedIniciativa").toString());
        this.iniciativa = serviciosIniciativa.consultarIniciativasPorId(idIniciativa);
        Usuario usuario = serviciosUsuario.consultarUsuario(correo);
        if(serviciosIniciativa.confirmarSiYaVoto(usuario, iniciativa) > 0) {
            int idIni = serviciosIniciativa.consultarIdDeVotacion(usuario, iniciativa);
            serviciosIniciativa.deleteVoto(idIni);
            this.message = "Su voto ha sido eliminado satisfactoriamente";
        }
        else {
            this.message = "No es posible eliminar el voto si no ha votado";
        }
    }

    public void deleteVoto() throws ExcepcionServiciosBancoProyectos{
        FacesContext facesContext = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(true);
        Integer idIniciativa = Integer.parseInt(session.getAttribute("selectedIniciativa").toString());
        this.iniciativa = serviciosIniciativa.consultarIniciativasPorId(idIniciativa);
        String correoSession = (String) session.getAttribute("username");
        Usuario usuario = serviciosUsuario.consultarUsuario(correoSession);
        if(serviciosIniciativa.confirmarSiYaVoto(usuario, iniciativa) > 0) {
            int idIni = serviciosIniciativa.consultarIdDeVotacion(usuario, iniciativa);
            serviciosIniciativa.deleteVoto(idIni);
            this.message = "Su voto ha sido eliminado satisfactoriamente";
        }
        else {
            this.message = "No es posible eliminar el voto si no ha votado";
        }
    }

    public int numeroDeVotosIniciativa() throws ExcepcionServiciosBancoProyectos {
        try{
            FacesContext facesContext = FacesContext.getCurrentInstance();
            HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(true);
            Integer idIniciativa = Integer.parseInt(session.getAttribute("selectedIniciativa").toString());
            this.iniciativa = serviciosIniciativa.consultarIniciativasPorId(idIniciativa);
            return serviciosIniciativa.numeroDeVotosIniciativa(iniciativa);
        } catch (ExcepcionServiciosBancoProyectos e){
            throw new ExcepcionServiciosBancoProyectos("No se pudo obtener el numero de votos");
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
        model.setSeriesColors("006600,FFFF00,000099,990000");
        return model;
    }

    public PieChartModel generarEstadisticaPorEstado() throws ExcepcionServiciosBancoProyectos {
        modelEstado = new PieChartModel();
        modelEstado.set("Espera", serviciosIniciativa.consultarNumeroDeIniciativasPorEstado("Espera"));
        modelEstado.set("Proyecto", serviciosIniciativa.consultarNumeroDeIniciativasPorEstado("Proyecto"));
        modelEstado.set("Solucionado", serviciosIniciativa.consultarNumeroDeIniciativasPorEstado("Solucionado"));
        modelEstado.set("Revision", serviciosIniciativa.consultarNumeroDeIniciativasPorEstado("Revision"));
        modelEstado.setTitle("");
        modelEstado.setShowDataLabels(true);
        modelEstado.setDataLabelFormatString("%dK");
        modelEstado.setLegendPosition("e");
        modelEstado.setShowDatatip(true);
        modelEstado.setShowDataLabels(true);
        modelEstado.setDataFormat("value");
        modelEstado.setDataLabelFormatString("%d");
        modelEstado.setSeriesColors("006600,FFFF00,000099,990000");
        return modelEstado;
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

