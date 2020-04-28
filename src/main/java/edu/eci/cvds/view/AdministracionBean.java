package edu.eci.cvds.view;

import com.google.inject.Inject;
import edu.eci.cvds.samples.entities.Usuario;
import edu.eci.cvds.samples.services.ExcepcionServiciosBancoProyectos;
import edu.eci.cvds.samples.services.ServiciosUsuario;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@SuppressWarnings("deprecation")
@ManagedBean(name = "adminBean")
@SessionScoped
public class AdministracionBean extends BasePageBean {
    @Inject
    private ServiciosUsuario serviciosUsuario;
    private String rol;
    private Usuario usuario;
    private String area;
    private String message;
    private List<Usuario> usuariosRegistrados;

    public void redirectActualizarRol() throws IOException {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(true);
        session.setAttribute("usuario", usuario.getEmail());
        facesContext.getExternalContext().redirect("actualizarRol.xhtml");
    }

    /**
     * Metodo que permite modificar rol de un usuario
     * @param rol Rol al que se desea actualizar
     * @throws ExcepcionServiciosBancoProyectos
     */
    public void actualizarRol(String rol) throws ExcepcionServiciosBancoProyectos {
        try {
            FacesContext facesContext = FacesContext.getCurrentInstance();
            HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(true);
            String correo = (String) session.getAttribute("usuario");
            serviciosUsuario.asignarRolUsuario(rol, serviciosUsuario.consultarUsuario(correo));
            this.message = "El rol se actualizo correctamente";
        } catch (Exception e){
            this.message = "El rol no se pudo actualizar";
        }
    }

    /**
     * Metodo que consulta por un usuario dado su correo
     * @param mail Correo electronico del usuario a buscar
     * @return El usuario con el correo ingresado
     * @throws ExcepcionServiciosBancoProyectos
     */
    public Usuario buscarUsuario(String mail) throws ExcepcionServiciosBancoProyectos {
        this.usuario = serviciosUsuario.consultarUsuario(mail);
        return usuario;
    }

    /**
     * Metodo que permite registrar a un usuario
     * @param documento documento del usuario
     * @param email correo electronico del usuario
     * @param nombre nombre del usuario
     * @param apellido apellido del usuario
     * @param password contraseña del usuario
     * @throws ExcepcionServiciosBancoProyectos
     */
    public void registrarUsuario(String documento, String email, String nombre, String apellido, String password) throws ExcepcionServiciosBancoProyectos {
        try {
            Long doc = Long.parseLong(documento);
            serviciosUsuario.registrarUsuario(new Usuario(doc, email, nombre, apellido, password, rol, area));
            this.message = "El usuario se registro correctamente";
        } catch (Exception e){
            this.message = "Hubo un error registrando al usuario, intentelo nuevamente";
        }
    }

    /**
     * @return Lista de usuarios registrados
     * @throws ExcepcionServiciosBancoProyectos
     */
    public List<Usuario> consultarUsuariosBasico() throws ExcepcionServiciosBancoProyectos{
        try{
            if (usuariosRegistrados == null) {
                usuariosRegistrados = serviciosUsuario.consultarUsuarios();
            }
            return usuariosRegistrados;
        } catch (ExcepcionServiciosBancoProyectos e){
            throw new ExcepcionServiciosBancoProyectos("Error al consultar usuarios");
        }
    }

    /**
     * @return Busqueda usuario teniedo email
     * @throws ExcepcionServiciosBancoProyectos
     */
    public Usuario buscarUsuario() throws ExcepcionServiciosBancoProyectos {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(true);
        String correo = (String) session.getAttribute("usuario");
        this.usuario = serviciosUsuario.consultarUsuario(correo);
        return usuario;
    }

    public void info() {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, message, "PrimeFaces Rocks."));
    }

    public void setUsuario(Usuario usuario){
        this.usuario = usuario;
    }

    public Usuario getUsuario(){
        return usuario;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

}

