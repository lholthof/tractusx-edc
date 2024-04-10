package com.sap.it.dsi.connector.bdrsmock;


import org.eclipse.jetty.ee10.servlet.ServletContextHandler;
import org.eclipse.jetty.server.Connector;
import org.eclipse.jetty.server.CustomRequestLog;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;
import org.eclipse.jetty.server.Slf4jRequestLogWriter;


public class BdrsMockServer {
    private Server server;

    public void start() throws Exception {
        server = new Server();
        server.setRequestLog(new CustomRequestLog(new Slf4jRequestLogWriter(), CustomRequestLog.EXTENDED_NCSA_FORMAT));
        try (ServerConnector connector = new ServerConnector(server)) {
            connector.setPort(8890);
            server.setConnectors(new Connector[] {connector});

            ServletContextHandler servletHandler = new ServletContextHandler();
            var servletHandlerTmp = servletHandler.getServletHandler();

            servletHandlerTmp.addServletWithMapping(BdrsServlet.class, "/api/bpn-directory");

            server.setHandler(servletHandler);
            server.start();
            server.join();
        }

    }

    void stop() throws Exception {
        server.stop();
    }
}
