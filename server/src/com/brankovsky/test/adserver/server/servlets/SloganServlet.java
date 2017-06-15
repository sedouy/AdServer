package com.brankovsky.test.adserver.server.servlets;

import com.brankovsky.test.adserver.server.annotations.Path;
import org.eclipse.jetty.http.HttpStatus;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Сервлет для получения рекламного слогана по id плейсмента
 */
@Path("/imp")
public class SloganServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int placementId;

        try {
            placementId = Integer.parseInt(req.getParameter("pl"));
        } catch (Exception ex) {
            ex.printStackTrace();

            resp.sendError(HttpStatus.BAD_REQUEST_400, ex.getMessage());

            return;
        }

        resp.setContentType("application/json");
        resp.setStatus(HttpStatus.OK_200);
        resp.getWriter().write("");
    }
}
