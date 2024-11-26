package com.dissertationproject.plant_stories.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;

@Configuration
public class SecurityConfig {

    /**
     * Configures the security filter chain for the application.
     * Defines rules for access control, login/logout behavior, and CSRF protection.
     *
     * @param http the HttpSecurity object to configure security settings
     * @return the configured SecurityFilterChain
     * @throws Exception if an error occurs during configuration
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            // Disable CSRF protection (use with caution in production; only for specific use cases)
            .csrf().disable() 
            .authorizeHttpRequests(authorize -> authorize
                // Allow public access to static resources and the signup/login pages
                .requestMatchers("/css/**", "/js/**", "/images/**", "/signup", "/login", "/forgot-password", "/reset-password", "/showForgotPasswordPage").permitAll()
                // Restrict access to /admin/** to users with the ADMIN role
                .requestMatchers("/admin/**").hasRole("ADMIN")
                // Restrict access to /user/** to users with the USER role
                .requestMatchers("/user/**").hasRole("USER")
                // Require authentication for all other requests
                .anyRequest().authenticated()
            )
            .formLogin(form -> form
                // Specify the custom login page URL
                .loginPage("/login")
                .successHandler((request, response, authentication) -> {
                    HttpSessionRequestCache requestCache = new HttpSessionRequestCache();
                    SavedRequest savedRequest = requestCache.getRequest(request, response);

                    if (savedRequest != null) {
                        // Get the original URL with query parameters
                        String targetUrl = savedRequest.getRedirectUrl();
                        System.out.println("Saved URL: " + savedRequest.getRedirectUrl());
                        response.sendRedirect(targetUrl);
                    } else {
                        // Handle default redirection with parameters if provided
                        String page = request.getParameter("page");
                        String fragment = request.getParameter("fragment");

                        StringBuilder redirectUrl = new StringBuilder("/?");
                        if (page != null) {
                            redirectUrl.append("page=").append(page);
                        }
                        if (fragment != null) {
                            if (page != null) {
                                redirectUrl.append("&");
                            }
                            redirectUrl.append("fragment=").append(fragment);
                        }

                        response.sendRedirect(redirectUrl.toString());
                    }
                })

                // Use a custom handler to handle login failures
                .failureHandler(customAuthenticationFailureHandler())
                // Allow unauthenticated access to the login page
                .permitAll()
            )
            .logout(logout -> logout
                // Specify the logout URL
                .logoutUrl("/logout")
                // Redirect to the login page with a logout flag after successful logout
                .logoutSuccessUrl("/login?logout")
                // Allow unauthenticated access to the logout URL
                .permitAll()
            )
            .csrf(csrf -> csrf
                // Exclude the logout endpoint from CSRF protection
                .ignoringRequestMatchers("/logout")
            );

        // Build and return the configured SecurityFilterChain
        return http.build();
    }

    /**
     * Configures a password encoder bean.
     * Uses BCrypt for hashing passwords securely.
     *
     * @return a BCryptPasswordEncoder instance
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * Provides a custom authentication failure handler bean.
     * Used to handle login failures with custom logic (e.g., error messages or redirects).
     *
     * @return an instance of CustomAuthenticationFailureHandler
     */
    @Bean
    public AuthenticationFailureHandler customAuthenticationFailureHandler() {
        return new CustomAuthenticationFailureHandler();
    }
}

