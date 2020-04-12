package edu.eci.cvds.view;

import com.google.inject.Inject;
import edu.eci.cvds.samples.entities.Usuario;
import edu.eci.cvds.samples.services.ExcepcionServiciosBancoProyectos;
import edu.eci.cvds.samples.services.ServiciosBancoProyectos;
import javax.enterprise.context.SessionScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import java.util.ArrayList;

@SuppressWarnings("deprecation")
@ManagedBean(name = "adminBean")
@SessionScoped
public class AdministracionBean extends BasePageBean {
    @Inject
    private ServiciosBancoProyectos serviciosBancoProyectos;
    private String rol;
    private Usuario usuario;

    /**
     * Metodo que permite modificar rol de un usuario
     * @param rol Rol al que se desea actualizar
     * @param email Correo electronico del usuario al que se desea actualizar
     * @throws ExcepcionServiciosBancoProyectos
     */
    public void actualizarRol(String rol, String email) throws ExcepcionServiciosBancoProyectos {
            serviciosBancoProyectos.asignarRolUsuario(rol, serviciosBancoProyectos.consultarUsuario(email));
    }

    /**
     * Metodo que consulta por un usuario dado su correo
     * @param mail Correo electronico del usuario a buscar
     * @return El usuario con el correo ingresado
     * @throws ExcepcionServiciosBancoProyectos
     */
    public Usuario buscarUsuario(String mail) throws ExcepcionServiciosBancoProyectos {
        this.usuario  = serviciosBancoProyectos.consultarUsuario(mail);
        return usuario;
    }

    /**
     * Metodo que permite registrar a un usuario
     * @param documento documento del usuario
     * @param email correo electronico del usuario
     * @param nombre nombre del usuario
     * @param apellido apellido del usuario
     * @param password contraseña del usuario
     * @param rol rol del usuario
     * @throws ExcepcionServiciosBancoProyectos
     */
    public void registrarUsuario(String documento, String email, String nombre, String apellido, String password, String rol) throws ExcepcionServiciosBancoProyectos {
    	Long doc = Long.parseLong(documento);
        serviciosBancoProyectos.registrarUsuario(new Usuario(doc, email, nombre, apellido, password, rol));
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
}

