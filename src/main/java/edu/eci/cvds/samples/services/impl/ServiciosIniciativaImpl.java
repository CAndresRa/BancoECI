package edu.eci.cvds.samples.services.impl;

import com.google.inject.Inject;
import edu.eci.cvds.sampleprj.dao.ComentarioDAO;
import edu.eci.cvds.sampleprj.dao.IniciativaDAO;
import edu.eci.cvds.sampleprj.dao.PersistenceException;
import edu.eci.cvds.sampleprj.dao.UsuarioDAO;
import edu.eci.cvds.samples.entities.Comentario;
import edu.eci.cvds.samples.entities.Iniciativa;
import edu.eci.cvds.samples.entities.PalabraClave;
import edu.eci.cvds.samples.services.ExcepcionServiciosBancoProyectos;
import edu.eci.cvds.samples.services.ServiciosIniciativa;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ServiciosIniciativaImpl implements ServiciosIniciativa {
    @Inject
    private UsuarioDAO usuarioDAO;

    @Inject
    private IniciativaDAO iniciativaDAO;

    @Inject
    private ComentarioDAO comentarioDAO;

    @Override
    public List<Comentario> consultarComentariosPorIniciativa(int idIniciativa) throws ExcepcionServiciosBancoProyectos {
        try {
            if (iniciativaDAO.consultarIniciativasPorId(idIniciativa) == null) {
                throw new ExcepcionServiciosBancoProyectos("la iniciativa es nula");
            }
            return comentarioDAO.consultarComentariosPorIniciativa(idIniciativa);
        } catch (PersistenceException e) {
            throw new javax.persistence.PersistenceException(e.getMessage(), e);
        }
    }
    @Override
    public void agregarComentarioAIniciativa(Comentario comentario, int idIniciativa) throws ExcepcionServiciosBancoProyectos{
        try {
            iniciativaDAO.agregarComentarioAIniciativa(comentario,idIniciativa);
        } catch (PersistenceException e) {
            throw new javax.persistence.PersistenceException(e.getMessage(), e);
        }
    }
    @Override
    public List<Iniciativa> consultarIniciativasOrdenadasPorColumna(String columna) throws ExcepcionServiciosBancoProyectos {
        try {
            return iniciativaDAO.consultarIniciativasOrdenadasPorColumna(columna);
        } catch (PersistenceException e) {
            throw new javax.persistence.PersistenceException(e.getMessage(), e);
        }
    }

    @Override
    public List<Iniciativa> consultarIniciativasPorPalabrasClaves(List<String> palabras) throws ExcepcionServiciosBancoProyectos {
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
    public void insertarIniciativa(Iniciativa iniciativa, List<String> palabras) throws ExcepcionServiciosBancoProyectos, PersistenceException {
        try{
            if(iniciativa.getDescripcion() == null) {
                throw new ExcepcionServiciosBancoProyectos("La iniciativa no tiene toda la informacion necesaria");
            }
            if(iniciativa.getFecha_registro() == null) {
                throw new ExcepcionServiciosBancoProyectos("La iniciativa no tiene toda la informacion necesaria");
            }
            if(iniciativa.getUsuario() == null) {
                throw new ExcepcionServiciosBancoProyectos("La iniciativa no tiene toda la informacion necesaria");
            }
            iniciativaDAO.insertarIniciativa(iniciativa);
            agregarPalabrasClaveAIniciativa(iniciativa , palabras );
        } catch (javax.persistence.PersistenceException e){
            throw new ExcepcionServiciosBancoProyectos(e.getMessage(), e);
        }
    }

    @Override
    public void agregarPalabrasClaveAIniciativa(Iniciativa iniciativa, List<String> palabras) throws ExcepcionServiciosBancoProyectos {
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
            if(estado == null) {
                throw new ExcepcionServiciosBancoProyectos("el estado es nulo");
            }
            iniciativaDAO.cambiarEstadoAiniciativa(estado,iniciativa);
        } catch (PersistenceException e){
            throw new ExcepcionServiciosBancoProyectos(e.getMessage(), e);
        }
    }

    @Override
    public int consultarNumeroDeIniciativasPorArea(String area) throws ExcepcionServiciosBancoProyectos {
        try{
            return iniciativaDAO.consultarNumeroDeIniciativasPorArea(area);
        } catch (PersistenceException e) {
            throw new ExcepcionServiciosBancoProyectos(e.getMessage(), e);
        }
    }

    @Override
    public List<Iniciativa> consultarIniciativasPorArea(String area) throws ExcepcionServiciosBancoProyectos {
        try{
            return  iniciativaDAO.consultarIniciativasPorArea(area);
        } catch (PersistenceException e) {
            throw new ExcepcionServiciosBancoProyectos(e.getMessage(), e);
        }
    }

    @Override
    public void agregarIniciativaRelacionadaAIniciativa(int idIni, int idIniRelacionada) throws ExcepcionServiciosBancoProyectos {
        try{
            iniciativaDAO.agregarIniciativaRelacionadaAIniciativa(idIni, idIniRelacionada);
        } catch (PersistenceException e){
            throw new ExcepcionServiciosBancoProyectos(e.getMessage(), e);
        }
    }

    @Override
    public List<Iniciativa> consultarIniciativasDelProponente(String email) throws ExcepcionServiciosBancoProyectos {
        try{
            return iniciativaDAO.consultarIniciativasDelProponente(email);
        } catch (PersistenceException e){
            throw new  ExcepcionServiciosBancoProyectos(e.getMessage(), e);
        }
    }

    @Override
    public void modificarIniciativa(String nombre, Iniciativa iniciativa) throws ExcepcionServiciosBancoProyectos {
        iniciativaDAO.modificarIniciativa(nombre, iniciativa);
    }

    @Override
    public void modificarDescripcion(String descripcion, Iniciativa iniciativa) throws ExcepcionServiciosBancoProyectos {
        iniciativaDAO.modificarDescripcion(descripcion, iniciativa);
    }

    @Override
    public void deletePalabraClave(int id) throws ExcepcionServiciosBancoProyectos {
        iniciativaDAO.deletePalabraClave(id);
    }

    @Override
    public int consultarNumeroDeIniciativasPorEstado(String estado) throws ExcepcionServiciosBancoProyectos {
        return iniciativaDAO.consultarNumeroDeIniciativasPorEstado(estado);
    }

    @Override
    public void eliminarPalabrasClaveDeUnaIniciativa(Iniciativa iniciativa) throws ExcepcionServiciosBancoProyectos {
        for(PalabraClave palabraClave : iniciativa.getPalabras_clave()){
            deletePalabraClave(palabraClave.getId());
        }
    }
}