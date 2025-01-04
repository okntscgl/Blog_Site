package com.okntscgl.Blog.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.util.UriComponentsBuilder;

@Controller
public class LogoutHandler extends SecurityContextLogoutHandler {

    private final ClientRegistrationRepository registrationRepository;

    public LogoutHandler(ClientRegistrationRepository registrationRepository) {
        this.registrationRepository = registrationRepository;
    }

    @Override
    public void logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
        super.logout(request, response, authentication);

        ClientRegistration registration = registrationRepository.findByRegistrationId("auth0");

        if (registration != null) {
            String issuer = (String) registration.getProviderDetails().getConfigurationMetadata().get("issuer");
            String clientId = registration.getClientId();
            String returnTo = ServletUriComponentsBuilder.fromCurrentContextPath().build().toString();

            String logoutUrl = UriComponentsBuilder
                    .fromHttpUrl(issuer + "/v2/logout?client_id={client_id}&returnTo={returnTo}")
                    .encode()
                    .buildAndExpand(clientId, returnTo)
                    .toUriString();

            try {
                response.sendRedirect(logoutUrl);
            } catch (Exception e) {
                e.printStackTrace();
                response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            }
        } else {
            // Handle error if auth0 registration not found
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }
}
