package com.brankovsky.test.adserver.server.servlets;

import com.brankovsky.test.adserver.core.model.Campaign;
import com.brankovsky.test.adserver.core.model.Placement;
import com.brankovsky.test.adserver.server.annotations.Path;
import com.brankovsky.test.adserver.server.storage.InMemoryStorage;
import com.sun.org.apache.regexp.internal.RE;
import org.eclipse.jetty.http.HttpStatus;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

/**
 * Сервлет для получения рекламного слогана по id плейсмента
 */
@Path("/imp")
public class SloganServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UUID placementId;

        try {
            placementId = UUID.fromString(req.getParameter("pl"));
        } catch (Exception ex) {
            ex.printStackTrace();

            resp.sendError(HttpStatus.BAD_REQUEST_400, ex.getMessage());

            return;
        }

        Placement placement = InMemoryStorage.getPlacementById(placementId);

        System.out.println("Placement " + placement);

        if (placement == null) {
            resp.setStatus(HttpStatus.NOT_FOUND_404);
            return;
        }

        String slogan = getSlogan(placement.getCampaigns());

        System.out.println("Slogan " + slogan);

        if (slogan == null) {
            resp.setStatus(HttpStatus.NOT_FOUND_404);
            return;
        }

        resp.setStatus(HttpStatus.OK_200);
        resp.getWriter().write(slogan);
    }

    private String getSlogan(List<? extends Campaign> campaigns) {
        if (campaigns.size() == 1) {
            return campaigns.get(0).getSlogan();
        }

        double weightSum = campaigns.stream().mapToInt(Campaign::getWeight).sum();

        double rand = Math.random();

        double minBound = 0;

        for (int i = 0; i < campaigns.size(); i++) {
            Campaign campaign = campaigns.get(i);

            double maxBound;

            double prob = campaign.getWeight() / weightSum;

            if (i == 0) {
                minBound = 0;
                maxBound = prob;
            } else {
                minBound = minBound + campaigns.get(i - 1).getWeight() / weightSum;
                maxBound = minBound + prob;
            }

            System.out.println("minBound = " + minBound + ", maxBound = " + maxBound + ", rand = " + rand);

            if (rand >= minBound && rand < maxBound) {
                return campaign.getSlogan();
            }
        }

        return null;
    }
}
