package edu.eci.cvds.samples.services;

import com.google.inject.Injector;
import edu.eci.cvds.sampleprj.dao.IniciativaDAO;
import edu.eci.cvds.sampleprj.dao.UsuarioDAO;
import edu.eci.cvds.sampleprj.dao.mybatis.MyBatisIniciativaDAO;
import edu.eci.cvds.sampleprj.dao.mybatis.MyBatisUsuarioDAO;


import edu.eci.cvds.samples.services.impl.ServiciosBancoProyectosImpl;
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
                bind(IniciativaDAO.class).to(MyBatisIniciativaDAO.class);
                bind(ServiciosBancoProyectos.class).to(ServiciosBancoProyectosImpl.class);
                bind(BasePageBean.class).to(AdministracionBean.class);

            }
        });
    }

    private ServiciosBancoProyectosFactory() {
        optInjector = Optional.empty();
    }

    public ServiciosBancoProyectos getServiciosBancoProyectos() {
        if (!optInjector.isPresent()) {
            optInjector = Optional.of(myBatisInjector("development", "mybatis-config.xml"));
        }

        return optInjector.get().getInstance(ServiciosBancoProyectos.class);
    }


    public ServiciosBancoProyectos getServiciosBancoProyectosTesting() {
        if (!optInjector.isPresent()) {
            optInjector = Optional.of(myBatisInjector("test", "mybatis-config-h2.xml"));
        }

        return optInjector.get().getInstance(ServiciosBancoProyectos.class);
    }


    public static ServiciosBancoProyectosFactory getInstance() {
        return instance;
    }
}
