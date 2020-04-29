package edu.eci.cvds.view;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.bean.SessionScoped;
import javax.servlet.http.HttpSession;
import com.google.inject.Inject;
import edu.eci.cvds.samples.services.ExcepcionServiciosBancoProyectos;
import edu.eci.cvds.samples.services.ServiciosUsuario;
import java.io.IOException;

@SuppressWarnings("deprecation")
@ManagedBean(name = "loginBean")
@SessionScoped
public class LoginBean extends BasePageBean {
    @Inject
    private ServiciosUsuario serviciosUsuario;
    private String password;

    /**
     * Metodo que permite iniciar sesion a un usuario validando sus credenciales mediante
     * protocolo HTML
     * @param username Corresponde al correo electronico
     * @param password Contraseña ingresada por el usuario que intenta ingresar
     * @throws IOException
     * @throws ExcepcionServiciosBancoProyectos
     */
    public void iniciarSesion(String username, String password) throws IOException, ExcepcionServiciosBancoProyectos {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        if (serviciosUsuario.consultarUsuario(username) != null && serviciosUsuario.consultarUsuario(username).getPassword().equals(password)) {
            HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(true);
            session.setAttribute("correo", username);
            if (serviciosUsuario.consultarUsuario(username).getRol().equals("Administrador")) {
                FacesContext.getCurrentInstance().getExternalContext().redirect("administrador.xhtml");
            }
            else if (serviciosUsuario.consultarUsuario(username).getRol().equals("Proponente")) {
                FacesContext.getCurrentInstance().getExternalContext().redirect("registrarIniciativa.xhtml");
            }
            else if (serviciosUsuario.consultarUsuario(username).getRol().equals("PMO")) {
                FacesContext.getCurrentInstance().getExternalContext().redirect("pmo.xhtml");
            }
        }
        else {
            FacesContext.getCurrentInstance().getExternalContext().redirect("login.xhtml");
        }
    }

    public void redirectBusquedaIniciativas() throws IOException{
        FacesContext facesContext = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(true);
        facesContext.getExternalContext().redirect("busquedaIniciativaPalabras.xhtml");
    }

    /**
     * Metodo que permite cerrar sesion de usuario
     * @throws IOException
     */
    public void logOut() throws  IOException{
        FacesContext facesContext = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(true);
        session.removeAttribute("correo");
        facesContext.getExternalContext().redirect("login.xhtml");
    }


    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
}

