package com.brankovsky.test.adserver.adapi;

import com.brankovsky.test.adserver.adapi.api.PlacementsResource;
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

        router.attach("/placements", PlacementsResource.class);

        return router;
    }
}
