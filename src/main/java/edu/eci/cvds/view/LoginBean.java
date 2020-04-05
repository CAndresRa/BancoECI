package edu.eci.cvds.view;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;

import java.io.Serializable;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import com.google.inject.Inject;
import edu.eci.cvds.samples.entities.Usuario;
import edu.eci.cvds.samples.services.ExcepcionServiciosBancoProyectos;
import edu.eci.cvds.samples.services.ServiciosBancoProyectos;
import org.h2.engine.User;


import java.io.IOException;
import java.io.Serializable;



@SuppressWarnings("deprecation")
@ManagedBean(name = "loginBean")
@SessionScoped
public class LoginBean extends BasePageBean {
    @Inject
    private ServiciosBancoProyectos serviciosBancoProyectos;
    private String username;
    private String password;

    public void iniciarSesion(String username, String password) throws IOException, ExcepcionServiciosBancoProyectos {

        FacesContext facesContext = FacesContext.getCurrentInstance();
        if (serviciosBancoProyectos.consultarUsuario(username) != null && serviciosBancoProyectos.consultarUsuario(username).getPassword().equals(password)) {
            HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(true);
            session.setAttribute("correo", username);
            FacesContext.getCurrentInstance().getExternalContext().redirect("singup.xhtml");

            /* Cuando se agreguen los roles
            if (serviciosBancoProyectos.consultarUsuario(username).getRol().equals("administrador")) {
                FacesContext.getCurrentInstance().getExternalContext().redirect("administracion.xhtml");
            }
            */
        }
        else {
            FacesContext.getCurrentInstance().getExternalContext().redirect("login.xhtml");
        }
    }

    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
}

