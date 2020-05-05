package edu.eci.cvds.samples.services;

import com.google.inject.Injector;
import edu.eci.cvds.sampleprj.dao.ComentarioDAO;
import edu.eci.cvds.sampleprj.dao.IniciativaDAO;
import edu.eci.cvds.sampleprj.dao.UsuarioDAO;
import edu.eci.cvds.sampleprj.dao.VotoDAO;
import edu.eci.cvds.sampleprj.dao.mybatis.MyBatisComentarioDAO;
import edu.eci.cvds.sampleprj.dao.mybatis.MyBatisIniciativaDAO;
import edu.eci.cvds.sampleprj.dao.mybatis.MyBatisUsuarioDAO;


import edu.eci.cvds.sampleprj.dao.mybatis.MyBatisVotoDAO;
import edu.eci.cvds.samples.services.impl.ServiciosIniciativaImpl;
import edu.eci.cvds.samples.services.impl.ServiciosUsuarioImpl;
import edu.eci.cvds.security.Logger;
import edu.eci.cvds.security.ShiroLogger;
import edu.eci.cvds.view.*;

import org.mybatis.guice.XMLMyBatisModule;

import java.util.Optional;

import static com.google.inject.Guice.createInjector;

public class ServiciosBancoProyectosFactory {

    private static ServiciosBancoProyectosFactory instance = new ServiciosBancoProyectosFactory();

    private static Optional<Injector> optInjector;

    private Injector myBatisInjector(String env, String pathResource) {
        return createInjector(new XMLMyBatisModule() {
            @Override
            protected void initialize() {
                setEnvironmentId(env);
                setClassPathResource(pathResource);
                bind(UsuarioDAO.class).to(MyBatisUsuarioDAO.class);
                bind(Logger.class).to(ShiroLogger.class);
                bind(IniciativaDAO.class).to(MyBatisIniciativaDAO.class);
                bind(VotoDAO.class).to(MyBatisVotoDAO.class);
                bind(ComentarioDAO.class).to(MyBatisComentarioDAO.class);
                bind(ServiciosUsuario.class).to(ServiciosUsuarioImpl.class);
                bind(ServiciosIniciativa.class).to(ServiciosIniciativaImpl.class);
                bind(BasePageBean.class).to(AdministracionBean.class);
            }
        });
    }

    private ServiciosBancoProyectosFactory() {
        optInjector = Optional.empty();
    }

    public ServiciosUsuario getServiciosUsuario() {
        if (!optInjector.isPresent()) {
            optInjector = Optional.of(myBatisInjector("development", "mybatis-config.xml"));
        }

        return optInjector.get().getInstance(ServiciosUsuario.class);
    }

    public ServiciosIniciativa getServiciosIniciativa() {
        if (!optInjector.isPresent()) {
            optInjector = Optional.of(myBatisInjector("development", "mybatis-config.xml"));
        }

        return optInjector.get().getInstance(ServiciosIniciativa.class);
    }


    public ServiciosUsuario getServiciosUsuarioTesting() {
        if (!optInjector.isPresent()) {
            optInjector = Optional.of(myBatisInjector("test", "mybatis-config-h2.xml"));
        }

        return optInjector.get().getInstance(ServiciosUsuario.class);
    }

    public ServiciosIniciativa getServiciosIniciativaTesting() {
        if (!optInjector.isPresent()) {
            optInjector = Optional.of(myBatisInjector("test", "mybatis-config-h2.xml"));
        }

        return optInjector.get().getInstance(ServiciosIniciativa.class);
    }


    public static ServiciosBancoProyectosFactory getInstance() {
        return instance;
    }
}
