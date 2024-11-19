package com.dissertationproject.plant_stories.security;


import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import java.io.IOException;

/**
 * CustomAuthenticationFailureHandler handles login failures for the application.
 * It customizes the behavior after a failed authentication attempt, such as adding an error message
 * and redirecting the user back to the login page with appropriate feedback.
 */
public class CustomAuthenticationFailureHandler implements AuthenticationFailureHandler {

    /**
     * Handles authentication failure by setting an error message in the session and redirecting
     * the user to the login page with an error flag.
     *
     * @param request   the HttpServletRequest that resulted in a failed authentication
     * @param response  the HttpServletResponse to send the error response
     * @param exception the exception that was thrown due to the failed authentication
     * @throws IOException      if an input or output error occurs during redirection
     * @throws ServletException if a servlet error occurs during redirection
     */
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
                                        AuthenticationException exception) throws IOException, ServletException {
        // Add an error message to the HTTP session to display on the login page
        request.getSession().setAttribute("loginError", "Invalid username or password. Please try again.");
        
        // Redirect the user to the login page with an error flag in the URL query parameters
        response.sendRedirect(request.getContextPath() + "/login?error");
    }
}

