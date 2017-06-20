package com.brankovsky.test.adserver.server;

import com.brankovsky.test.adserver.server.servlets.AnnotatedServletContainer;
import com.brankovsky.test.adserver.server.servlets.SloganServlet;
import com.brankovsky.test.adserver.server.storage.InMemoryStorage;
import org.eclipse.jetty.server.Server;

import java.util.UUID;

/**
 * Главный класс сервера
 */
///ToDo: добавить логгирование
public class AdServerMain {

    public static void main(String[] args) throws Exception {

        InMemoryStorage.initialize();

        Server server = new Server(7070); ///ToDo: в настройки!
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
