package com.sap.it.dsi.connector.bdrsmock;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.zip.GZIPOutputStream;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class BdrsServlet extends HttpServlet {

    private static final ObjectMapper MAPPER = new ObjectMapper();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setHeader("Content-Encoding", "gzip");

        var bas = new ByteArrayOutputStream();
        Map map = new HashMap();
        map.put("bpn1", "did1");
        map.put("provider", "did:web:dim-static-qa.dis-cloud-qa.cfapps.eu12.hana.ondemand.com:dim-hosted:8f15f73b-827c-4447-bcad-91b71642a636:verifier-company");
        map.put("consumer", "did:web:dim-static-qa.dis-cloud-qa.cfapps.eu12.hana.ondemand.com:dim-hosted:1b19db64-914f-4ded-8824-fb698e8489d7:holder-company");
        try (var gzip = new GZIPOutputStream(bas)) {
            gzip.write(MAPPER.writeValueAsBytes(map));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        ServletOutputStream stream = response.getOutputStream();
        stream.write(bas.toByteArray());
        stream.flush();
        response.setStatus(HttpServletResponse.SC_OK);
    }
}
