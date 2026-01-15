package com.NFR_RECON.Interceptor;

import com.NFR_RECON.Constants.ResponseMessage;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

@Component
public class RequestInterceptor implements HandlerInterceptor {

    public static final Logger LOGGER = Logger.getLogger(RequestInterceptor.class.getName());

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        LOGGER.log(Level.INFO, "START >> CLASS: RequestInterceptor >> METHOD: preHandle");

        String token = request.getHeader("Authorization");

        // ----- NULL Token -----
        if(token == null || !token.startsWith("Basic ")) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().write("Authorization Failed ! Token is null");
            LOGGER.log(Level.INFO, "AUTHORIZATION FAILED >> CLASS: RequestInterceptor >> METHOD: preHandle >> " +
                    "Token is null");
            return false;
        } else {

            // ----- Get USERNAME and PASSWORD from Token-----
            String base64Credentials = token.substring(6);
            byte[] decodedBytes = Base64.getDecoder().decode(base64Credentials);
            String decoded = new String(decodedBytes, StandardCharsets.UTF_8);

            String[] credentials = decoded.split(":", 2);
            String username = credentials[0];
            String password = credentials[1];


            // ----- NULL USERNAME and PASSWORD -----
            if(Objects.equals(username, "") || Objects.equals(password, "")) {
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                response.getWriter().write("Authorization Failed ! Username and password fields are Empty");
                LOGGER.log(Level.INFO, "AUTHORIZATION FAILED >> CLASS: RequestInterceptor >> " +
                        "METHOD: preHandle >> Empty Username and Password fields" +
                        ResponseMessage.AUTHORIZATION_FAIL);
                return false;

            } else {

                // ----- Correct USERNAME and PASSWORD -----
                if(username.equals("Perennial") && password.equals("Pere@12345")) {
                    response.setStatus(HttpServletResponse.SC_OK);
                    LOGGER.log(Level.INFO, "AUTHORIZATION SUCCESSFUL >> CLASS: RequestInterceptor >> " +
                            "METHOD: prehandle >> ");
                    return true;
                } else {

                    // Wrong USERNAME and PASSWORD -----
                    response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                    response.getWriter().write("Authorization Failed ! Wrong Username or Password");
                    LOGGER.log(Level.INFO, "AUTHORIZATION FAILED >> CLASS: RequestInterceptor >> " +
                            "METHOD: prehandle >> Wrong Username and Password. ");
                    return false;
                }
            }
        }
    }
}
