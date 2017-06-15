package com.brankovsky.test.adserver.adapi.api;

import com.brankovsky.test.adserver.adapi.HibernateSessionFactory;
import com.brankovsky.test.adserver.adapi.model.Placement;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.restlet.data.Status;
import org.restlet.resource.Get;
import org.restlet.resource.Post;
import org.restlet.resource.Resource;
import org.restlet.resource.ServerResource;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by Алексей on 15.06.2017.
 */
public class PlacementsResource extends ServerResource {

    @Get("json")
    public Collection<Placement> getPlacements() {
        Session session = HibernateSessionFactory.getSessionFactory().openSession();

        String paramId = getQueryValue("id");

        if (paramId == null) {
            return session.createQuery("FROM Placement P").getResultList();
        }

        int id;

        try {
            id = Integer.parseInt(paramId);
        } catch (Exception e) {
            e.printStackTrace();
            doError(Status.CLIENT_ERROR_BAD_REQUEST);
            return new ArrayList<Placement>();
        }

        Query query = session.createQuery("FROM Placement P WHERE P.id = :placementId");

        query.setParameter("placementId", id);

        return query.getResultList();
    }

    /*@Post("json")
    public int postPlacement(Placement placement) {
        Session session = HibernateSessionFactory.getSessionFactory().openSession();

        Serializable res = session.save(placement);

        return 3;
    }*/
}
