package edu.eci.cvds.samples.services.impl;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import edu.eci.cvds.sampleprj.dao.*;
import edu.eci.cvds.samples.entities.Usuario;
import edu.eci.cvds.samples.services.ExcepcionServiciosBancoProyectos;
import edu.eci.cvds.samples.services.ServiciosBancoProyectos;

import java.sql.Date;
import java.util.List;

@Singleton
public class ServiciosBancoProyectosImpl implements ServiciosBancoProyectos {

    @Inject
    private UsuarioDAO usuarioDAO;


    @Override
    public Usuario consultarUsuario(String email) throws ExcepcionServiciosBancoProyectos {
        try {
            return usuarioDAO.consultarUsuario(email);
        } catch (PersistenceException e){
            throw new ExcepcionServiciosBancoProyectos("Error al consultar clientes", e);
        }
    }


}