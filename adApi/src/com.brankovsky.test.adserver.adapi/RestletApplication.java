package com.brankovsky.test.adserver.adapi;

import com.brankovsky.test.adserver.adapi.api.PlacementsServerResource;
import org.restlet.Application;
import org.restlet.Restlet;
import org.restlet.routing.Router;

/**
 * Created by Алексей on 15.06.2017.
 */
public class RestletApplication extends Application {
    @Override
    public Restlet createInboundRoot() {
        Router router = new Router(getContext());

        router.attach("/placements", PlacementsServerResource.class);

        return router;
    }
}
