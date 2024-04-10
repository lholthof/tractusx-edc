package com.sap.it.dsi.connector.bdrsmock;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JettyStarter {

    private static final Logger LOG = LoggerFactory.getLogger(JettyStarter.class);

    public static void main(String[] args) {

        LOG.info("Starting SSI Mock server!");
        BdrsMockServer server = new BdrsMockServer();

        try {
            server.start();
            server.stop();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        LOG.info("Shutting down SSI Mock server!");
    }
}