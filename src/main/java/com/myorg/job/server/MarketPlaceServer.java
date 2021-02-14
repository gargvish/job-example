package com.myorg.job.server;

import static org.eclipse.jetty.servlet.ServletContextHandler.NO_SESSIONS;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.glassfish.jersey.servlet.ServletContainer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.net.HostAndPort;

/**
 * Standalone Embedded MarketPlace Jetty server
 * <pre>
 *     - sets up the resource and injection paths
 * </pre>
 *
 * @author vg
 * @since Feb 2021
 */
public class MarketPlaceServer {

    private static final Logger logger = LoggerFactory.getLogger(MarketPlaceServer.class);
    private static final String PATH = "/api/v1/*";

    private static final String PROVIDER_PATH = "jersey.config.server.provider.packages";
    private static final String RESOURCE_PATH = "com.myorg.job.rest";

    private static final String APP_PATH = "javax.ws.rs.Application";
    private static final String APP_INJECT_PATH = "com.myorg.job.app.MarketPlaceApp";

    public static void main(String[] args) {

        final String localEndPoint = "localhost:8081";

        HostAndPort local = HostAndPort.fromString(localEndPoint);

        Server server = createServer(local);

        try {
            logger.info("Starting -- server: " + server);
            server.start();

            logger.info("[MP Server] Balance: " + 0);
            server.join();

        }
        catch (Exception ex) {
            logger.error("Error occurred while starting Jetty", ex);
            System.exit(1);
        }

        finally {
            server.destroy();
        }
    }

    public static Server createServer(final HostAndPort hostAndPort) {
        final ServletContextHandler handler = createContextHandler();
        Server server = new Server(hostAndPort.getPort());
        server.setHandler(handler);
        logger.info("Initializing -- server: " + server + " port: " + hostAndPort.getPort());
        return server;
    }

    public static ServletContextHandler createContextHandler() {
        ServletContextHandler servletContextHandler = new ServletContextHandler(NO_SESSIONS);
        servletContextHandler.setContextPath("/");
        ServletHolder servletHolder = servletContextHandler.addServlet(ServletContainer.class, PATH);
        servletHolder.setInitOrder(0);
        servletHolder.setInitParameter(PROVIDER_PATH, RESOURCE_PATH);
        servletHolder.setInitParameter(APP_PATH, APP_INJECT_PATH);
        return servletContextHandler;
    }
}
