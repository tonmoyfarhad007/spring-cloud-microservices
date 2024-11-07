package com.mycompany.serviceone.config.feingclient;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.mycompany.serviceone.domainmodel.IntraServiceCredentialDomainModel;
import com.mycompany.serviceone.service.keycloak.KeycloakAccessTokenService;
import feign.RequestInterceptor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class IntraServiceFeignClientConfiguration {

    private final KeycloakAccessTokenService keycloakAccessTokenService;
    private final IntraServiceCredentialDomainModel intraServiceCredentialDomainModel;

    @Bean
    public RequestInterceptor requestInterceptor() {
        return requestTemplate -> {
            String token = null;
            try {
                token = keycloakAccessTokenService.getAccessToken(intraServiceCredentialDomainModel);
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
            requestTemplate.header("Authorization", "Bearer " + token);
        };
    }
}
