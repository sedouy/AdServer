package com.brankovsky.test.adserver.core.model;

import org.restlet.resource.Delete;
import org.restlet.resource.Get;
import org.restlet.resource.Post;
import org.restlet.resource.Put;

import java.util.Collection;
import java.util.UUID;

/**
 * Created by Алексей on 19.06.2017.
 */
public interface PlacementsResource {
    @Get("json")
    Collection<Placement> fetchPlacements();

    @Post("json")
    UUID createPlacement(Placement placement);

    @Put("json")
    void updatePlacement(Placement placement);

    @Delete
    void removePlacement(UUID id);
}
