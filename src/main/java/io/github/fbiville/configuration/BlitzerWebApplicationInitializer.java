package io.github.fbiville.configuration;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

public class BlitzerWebApplicationInitializer implements WebApplicationInitializer {

    @Override
    public void onStartup(ServletContext container) throws ServletException {
        container.addListener(contextListener(webContext(BlitzerDataModule.class)));
        ServletRegistration.Dynamic registration = container.addServlet("dispatcher", dispatcherServlet(webContext(BlitzerWebModule.class)));
        registration.setLoadOnStartup(1);
        registration.addMapping("/*");
    }

    private ContextLoaderListener contextListener(WebApplicationContext root) {
        return new ContextLoaderListener(root);
    }

    private DispatcherServlet dispatcherServlet(WebApplicationContext webApplicationContext) {
        return new DispatcherServlet(webApplicationContext);
    }

    private WebApplicationContext webContext(Class<?> configurationClass) {
        AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
        context.setConfigLocation(configurationClass.getPackage().getName());
        return context;
    }
}
