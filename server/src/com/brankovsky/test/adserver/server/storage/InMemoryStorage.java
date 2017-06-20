package com.brankovsky.test.adserver.server.storage;

import com.brankovsky.test.adserver.core.helpers.JsonHelper;
import com.brankovsky.test.adserver.core.model.Placement;
import com.sun.istack.internal.NotNull;
import org.restlet.data.MediaType;
import org.restlet.resource.ClientResource;

import java.util.*;

public final class InMemoryStorage {

    private static final long UPDATING_PERIOD_SEC = 300;

    private static final String API_URI = "http://localhost:8182/api/placements"; ///ToDo: вынести в настройки

    private static final Timer updater = new Timer();
    private static final Map<UUID, Placement> storage = new HashMap<>(10000);

    private InMemoryStorage() {
    }

    public static Placement getPlacementById(@NotNull UUID placementId) {
        return storage.get(placementId);
    }

    public static void initialize() {
        updater.schedule(new TimerTask() {
            @Override
            public void run() {
                update();
            }
        }, 0, UPDATING_PERIOD_SEC * 1000);
    }

    private static void update() {
        Collection<Placement> placements = fetchInfo();

        int storSize = storage.size();

        for (Placement placement : placements) {
            storage.put(placement.getId(), placement);
        }

        if (storSize == 0) {
            return;
        }

        ArrayList<UUID> forRemove = new ArrayList<>(storage.size());

        for (Placement placement : storage.values()) {
            if (!placements.contains(placement)) {
                forRemove.add(placement.getId());
            }
        }

        for (UUID plId : forRemove) {
            storage.remove(plId);
        }
    }

    private static Collection<Placement> fetchInfo() {
        ClientResource clientResource = new ClientResource(API_URI);

        String json;

        try {
           json = clientResource.get(MediaType.APPLICATION_JSON).getText();
        } catch (Exception ex) {
            ex.printStackTrace();
            return Collections.emptyList();
        }

        Placement[] placements = JsonHelper.fromJson(json, Placement[].class);

        if (placements == null) {
            return  Collections.emptyList();
        }

        return Arrays.asList(placements);
    }
}
