package com.brankovsky.test.adserver.adapi;

import org.restlet.Component;
import org.restlet.data.Protocol;


/**
 * Главный класс сервера API
 */
public class AdApiServerMain {
    public static void main(String[] args) throws Exception {
        Component component = new Component();

        component.getServers().add(Protocol.HTTP, 8182);

        component.getDefaultHost().attach("/api", new RestletApplication());

        component.start();
    }
}
