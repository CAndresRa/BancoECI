package edu.eci.cvds.view;

import com.google.inject.Inject;
import edu.eci.cvds.samples.entities.Usuario;
import edu.eci.cvds.samples.services.ExcepcionServiciosBancoProyectos;
import edu.eci.cvds.samples.services.ServiciosUsuario;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

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

    /**
     * Metodo que permite modificar rol de un usuario
     * @param rol Rol al que se desea actualizar
     * @param email Correo electronico del usuario al que se desea actualizar
     * @throws ExcepcionServiciosBancoProyectos
     */
    public void actualizarRol(String rol, String email) throws ExcepcionServiciosBancoProyectos {
        try {
            serviciosUsuario.asignarRolUsuario(rol, serviciosUsuario.consultarUsuario(email));
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

    public void info() {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, message, "PrimeFaces Rocks."));
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

