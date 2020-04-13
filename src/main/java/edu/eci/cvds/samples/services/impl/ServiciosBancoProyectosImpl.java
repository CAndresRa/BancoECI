package edu.eci.cvds.samples.services.impl;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import edu.eci.cvds.sampleprj.dao.*;
import edu.eci.cvds.samples.entities.Iniciativa;
import edu.eci.cvds.samples.entities.Usuario;
import edu.eci.cvds.samples.services.ExcepcionServiciosBancoProyectos;
import edu.eci.cvds.samples.services.ServiciosBancoProyectos;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Singleton
public class ServiciosBancoProyectosImpl implements ServiciosBancoProyectos {


    @Inject
    private UsuarioDAO usuarioDAO;

    @Inject
    private IniciativaDAO iniciativaDAO;

    @Override
    public List<Iniciativa> consultarIniciativasPorPalabrasClaves(List<String> palabras) throws ExcepcionServiciosBancoProyectos{
        try{
            List<Iniciativa> iniciativas = new ArrayList<>();
            List<Integer> idIniciativas = new ArrayList<>();
            for(int i=0 ; i<palabras.size() ; i++){
                List<Iniciativa> iniciativasTemporales = iniciativaDAO.consultarIniciativasPorPalabraClave(palabras.get(i));
                for(int j=0 ; j<iniciativasTemporales.size() ; j++){
                    Iniciativa iniciativa = iniciativasTemporales.get(j);
                    if(!idIniciativas.contains(iniciativa.getId())){
                        iniciativas.add(consultarIniciativasPorId(iniciativa.getId()));
                        idIniciativas.add(iniciativa.getId());
                    }
                }
            }
            return iniciativas;
        } catch (javax.persistence.PersistenceException | PersistenceException e) {
            throw new ExcepcionServiciosBancoProyectos(e.getMessage(), e);
        }
    }
    @Override
    public void insertarIniciativa(Iniciativa iniciativa , List<String> palabras) throws ExcepcionServiciosBancoProyectos, PersistenceException {
        try{
        	if(iniciativa.getDescripcion() == null) {
        		throw new ExcepcionServiciosBancoProyectos("La iniciativa no tiene toda la informacion necesaria");
        	}
            iniciativaDAO.insertarIniciativa(iniciativa);
        	agregarPalabrasClaveAIniciativa(iniciativa , palabras );
        } catch (javax.persistence.PersistenceException e){
            throw new ExcepcionServiciosBancoProyectos(e.getMessage(), e);
        }
    }

    @Override
    public void agregarPalabrasClaveAIniciativa(Iniciativa iniciativa, List < String > palabras) throws ExcepcionServiciosBancoProyectos {
        try{
            if(iniciativa == null){
                throw new ExcepcionServiciosBancoProyectos("La iniciativa no existe");
            }
            if(palabras == null || palabras.size() == 0){
                throw new ExcepcionServiciosBancoProyectos("No hay palabras Clave");
            }
            for(int i=0 ; i < palabras.size() ; i++){
                iniciativaDAO.agregarPalabraClaveAIniciativa(iniciativa , palabras.get(i));
            }
        } catch (ExcepcionServiciosBancoProyectos | PersistenceException e){
            throw new ExcepcionServiciosBancoProyectos(e.getMessage(), e);
        }
    }

    @Override
    public void asignarRolUsuario(String rol, Usuario usuario) throws ExcepcionServiciosBancoProyectos {
        try {
            if(usuario == null){
                throw new ExcepcionServiciosBancoProyectos("El usuario no existe");
            }
            if(rol == null){
                throw new ExcepcionServiciosBancoProyectos("El rol es nulo");
            }
            usuarioDAO.asignarRolUsuario(rol,usuario);
        } catch (PersistenceException e){
            throw new ExcepcionServiciosBancoProyectos(e.getMessage(), e);
        }
    }

    @Override
    public List<Iniciativa> consultarIniciativas() throws ExcepcionServiciosBancoProyectos {
        try {
            return iniciativaDAO.consultarIniciativas();
        } catch (PersistenceException e){
            throw new ExcepcionServiciosBancoProyectos(e.getMessage(), e);
        }
    }

    @Override
    public Iniciativa consultarIniciativasPorId(int id) throws ExcepcionServiciosBancoProyectos {
        try{
            return  iniciativaDAO.consultarIniciativasPorId(id);
        }catch (PersistenceException e){
            throw new  ExcepcionServiciosBancoProyectos(e.getMessage(), e);
        }
    }

    @Override
    public void cambiarEstadoAiniciativa(String estado, Iniciativa iniciativa) throws ExcepcionServiciosBancoProyectos {
        try{
            iniciativaDAO.cambiarEstadoAiniciativa(estado,iniciativa);
        } catch (PersistenceException e){
            throw new ExcepcionServiciosBancoProyectos(e.getMessage(), e);
        }
    }

    @Override
    public List<Usuario> consultarUsuarios() throws ExcepcionServiciosBancoProyectos {
        try {
            return usuarioDAO.consultarUsuarios();
        } catch (PersistenceException e){
            throw new ExcepcionServiciosBancoProyectos(e.getMessage(), e);
        }
    }

    @Transactional
    @Override
    public void registrarUsuario(Usuario u) throws ExcepcionServiciosBancoProyectos {
        try{
            usuarioDAO.registrarUsuario(u);
        } catch (PersistenceException e) {
            throw new ExcepcionServiciosBancoProyectos("Rol invalido");
        }
    }

    @Override
    public Usuario consultarUsuario(String email) throws ExcepcionServiciosBancoProyectos {
        try {
            Usuario usuario = usuarioDAO.consultarUsuario(email);
            /*if(usuario == null){
                throw new ExcepcionServiciosBancoProyectos("El usuario no existe");
            }*/
            return usuarioDAO.consultarUsuario(email);
        } catch (PersistenceException e){
            /*throw new ExcepcionServiciosBancoProyectos(e.getMessage(), e);*/
            throw new ExcepcionServiciosBancoProyectos("El usuario no existe");
        }
    }
}