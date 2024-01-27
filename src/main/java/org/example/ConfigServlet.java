package org.example;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.InputStream;

@WebServlet("/config")
public class ConfigServlet extends HttpServlet {
    private static final String CONFIG_FILE_PATH = "/config.json";
    private static final String CONTENT_TYPE_JSON = "application/json";

    private Configuration configuration;

    @Override
    public void init() throws ServletException {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            InputStream inputStream = getClass().getResourceAsStream(CONFIG_FILE_PATH);

            if (inputStream != null) {
                configuration = objectMapper.readValue(inputStream, Configuration.class);
            } else {
                configuration = null;
            }
        } catch (IOException e) {
            e.printStackTrace();
            configuration = null;
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType(CONTENT_TYPE_JSON);

        if (configuration != null) {
            ObjectMapper objectMapper = new ObjectMapper();
            String jsonString = objectMapper.writeValueAsString(configuration);
            resp.getWriter().write(jsonString);
        } else {
            resp.setStatus(HttpServletResponse.SC_FORBIDDEN);
        }
    }
}
