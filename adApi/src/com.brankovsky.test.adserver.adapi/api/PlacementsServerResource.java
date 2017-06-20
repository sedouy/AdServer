package com.brankovsky.test.adserver.adapi.api;

import com.brankovsky.test.adserver.adapi.HibernateSessionFactory;
import com.brankovsky.test.adserver.adapi.model.PlacementEntity;
import com.brankovsky.test.adserver.core.model.Placement;
import com.brankovsky.test.adserver.core.model.PlacementsResource;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.restlet.data.Status;
import org.restlet.resource.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.UUID;

public class PlacementsServerResource extends ServerResource implements PlacementsResource {

    @Get("json")
    public Collection<Placement> fetchPlacements() {
        try(Session session = HibernateSessionFactory.getSessionFactory().openSession()) {
            String paramId = getQueryValue("id");

            try {
                if (paramId == null) {
                    return session.createQuery("FROM PlacementEntity P").getResultList();
                }

                UUID id = UUID.fromString(paramId);

                Query query = session.createQuery("FROM PlacementEntity P WHERE P.id = :placementId");

                query.setParameter("placementId", id);

                return query.getResultList();
            } catch (Exception e) {
                e.printStackTrace();
                doError(Status.CLIENT_ERROR_BAD_REQUEST);
                return Collections.emptyList();
            }
        }
    }

    @Post("json")
    public UUID createPlacement(Placement placement) {
        try(Session session = HibernateSessionFactory.getSessionFactory().openSession()) {
            placement.setId(UUID.randomUUID());

            Transaction tx = session.beginTransaction();

            try {
                session.save(new PlacementEntity(placement));
                tx.commit();
            } catch (Exception ex) {
                tx.rollback();
                ex.printStackTrace();
                doError(Status.CLIENT_ERROR_BAD_REQUEST);
                return null;
            }

            return placement.getId();
        }
    }

    @Put("json")
    public void updatePlacement(Placement placement) {
        try(Session session = HibernateSessionFactory.getSessionFactory().openSession()) {

            Transaction tx = session.beginTransaction();

            try {
                Query query = session.createQuery("UPDATE PlacementEntity P SET P.name = :placementName, P.campaignsEntities = :campaigns WHERE P.id = :placementId");

                query.setParameter("placementId", placement.getId());

                query.setParameter("placementName", placement.getName());

                query.setParameter("campaigns", placement.getCampaigns());

                query.executeUpdate();

                tx.commit();
            } catch (Exception ex) {
                tx.rollback();
                ex.printStackTrace();
                doError(Status.CLIENT_ERROR_BAD_REQUEST);
            }
        }
    }

    @Delete
    public void removePlacement(UUID id) {
        try(Session session = HibernateSessionFactory.getSessionFactory().openSession()) {

            Transaction tx = session.beginTransaction();

            try {
                Query query = session.createQuery("DELETE FROM PlacementEntity P WHERE P.id = :placementId");

                query.setParameter("placementId", id);

                query.executeUpdate();

                tx.commit();
            } catch (Exception ex) {
                tx.rollback();
                ex.printStackTrace();
                doError(Status.CLIENT_ERROR_BAD_REQUEST);
            }
        }
    }
}
