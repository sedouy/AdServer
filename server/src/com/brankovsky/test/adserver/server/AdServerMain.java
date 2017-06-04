package com.brankovsky.test.adserver.server;

import com.brankovsky.test.adserver.server.servlets.AnnotatedServletContainer;
import com.brankovsky.test.adserver.server.servlets.SloganServlet;
import org.eclipse.jetty.server.Server;

/**
 * Главный класс сервера
 */
public class AdServerMain {

    public static void main(String[] args) throws Exception {

        Server server = new Server(7070);
        AnnotatedServletContainer servletContainer = new AnnotatedServletContainer(server);

        servletContainer.addServlet(SloganServlet.class);

        try {
            server.start();
            server.join();
        } finally {
            server.destroy();
        }
    }
}
