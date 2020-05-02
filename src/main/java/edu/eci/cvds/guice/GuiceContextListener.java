package edu.eci.cvds.guice;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import edu.eci.cvds.sampleprj.dao.ComentarioDAO;
import edu.eci.cvds.sampleprj.dao.IniciativaDAO;
import edu.eci.cvds.sampleprj.dao.UsuarioDAO;
import edu.eci.cvds.sampleprj.dao.mybatis.MyBatisComentarioDAO;
import edu.eci.cvds.sampleprj.dao.mybatis.MyBatisIniciativaDAO;
import edu.eci.cvds.sampleprj.dao.mybatis.MyBatisUsuarioDAO;

import edu.eci.cvds.samples.services.ServiciosIniciativa;
import edu.eci.cvds.samples.services.ServiciosUsuario;
import edu.eci.cvds.samples.services.impl.ServiciosIniciativaImpl;
import edu.eci.cvds.samples.services.impl.ServiciosUsuarioImpl;
import edu.eci.cvds.security.Logger;
import edu.eci.cvds.security.ShiroLogger;
import edu.eci.cvds.view.*;
import org.mybatis.guice.XMLMyBatisModule;
import org.mybatis.guice.datasource.helper.JdbcHelper;
import com.google.inject.Guice;
import com.google.inject.Injector;

public class GuiceContextListener implements ServletContextListener {

    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        ServletContext servletContext = servletContextEvent.getServletContext();
        servletContext.removeAttribute(Injector.class.getName());
    }

    public void contextInitialized(ServletContextEvent servletContextEvent) {
        Injector injector = Guice.createInjector(new XMLMyBatisModule() {
            @Override
            protected void initialize() {
                install(JdbcHelper.MySQL);
                setEnvironmentId("development");
                setClassPathResource("mybatis-config.xml");

                // TODO Add service class associated to Stub implementation
                bind(UsuarioDAO.class).to(MyBatisUsuarioDAO.class);
                bind(IniciativaDAO.class).to(MyBatisIniciativaDAO.class);
                bind(ComentarioDAO.class).to(MyBatisComentarioDAO.class);
                bind(BasePageBean.class).to(LoginBean.class);
                bind(ServiciosUsuario.class).to(ServiciosUsuarioImpl.class);
                bind(ServiciosIniciativa.class).to(ServiciosIniciativaImpl.class);
                bind(Logger.class).to(ShiroLogger.class);

            }
        });

        servletContextEvent.getServletContext().setAttribute(Injector.class.getName(), injector);
    }
}