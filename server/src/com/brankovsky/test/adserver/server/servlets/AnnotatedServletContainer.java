package com.brankovsky.test.adserver.server.servlets;

import com.brankovsky.test.adserver.server.annotations.Path;
import com.sun.istack.internal.NotNull;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;

import javax.servlet.Servlet;

/**
 * Предназначен для упрощенного добавления сервлетов в контейнер jetty помеченных аннотацией {@link Path} сервлетов
 */
public class AnnotatedServletContainer {
    private ServletContextHandler context;

    /**
     * Создает контейнер
     * @param jettyServer jetty - сервер
     * @param rootPath корневой путь
     */
    public AnnotatedServletContainer(@NotNull Server jettyServer, @NotNull String rootPath ) {
        assert jettyServer != null;
        assert rootPath != null;

        context = new ServletContextHandler(jettyServer, rootPath);
    }

    /**
     * Создает контейнер
     * @param jettyServer jetty - сервер
     */
    public AnnotatedServletContainer(@NotNull Server jettyServer) {
        this(jettyServer, "");
    }

    /**
     * Добавляет сервлет в контейнер
     * @param servletClass класс сервлета
     */
    public void addServlet(@NotNull Class<? extends Servlet> servletClass) {
        assert servletClass != null;

        Path anno = servletClass.getAnnotation(Path.class);

        assert anno != null && !anno.value().isEmpty() : "Annotation [@Path] is not found!";

        context.addServlet(servletClass, anno.value());
    }
}
