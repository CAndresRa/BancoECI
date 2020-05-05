package edu.eci.cvds.samples.entities;

import java.util.Date;

public class Voto {
    private long documentoUsuario;
    private String nombreIniciativa;

    public Voto(){
    }

    public Voto( long documentoUsuario, String nombreIniciativa){
        this.documentoUsuario = documentoUsuario;
        this.nombreIniciativa = nombreIniciativa;
    }

    public long getDocumentoUsuario() {
        return documentoUsuario;
    }

    public void setDocumentoUsuario(long documentoUsuario) {
        this.documentoUsuario = documentoUsuario;
    }

    public String getNombreIniciativa() {
        return nombreIniciativa;
    }

    public void setNombreIniciativa(String nombreIniciativa) {
        this.nombreIniciativa = nombreIniciativa;
    }

    @Override
    public String toString() {
        return "Voto{" + "documento=" + documentoUsuario + ", NombreIniciativa=" + nombreIniciativa + '}';
    }
}
