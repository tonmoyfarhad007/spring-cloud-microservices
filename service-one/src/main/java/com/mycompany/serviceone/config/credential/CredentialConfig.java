package com.mycompany.serviceone.config.credential;

import com.mycompany.serviceone.domainmodel.IntraServiceCredentialDomainModel;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CredentialConfig {

    @Value("${keycloak.client.intra-service.client-id}")
    private String clientId;

    @Value("${keycloak.client.intra-service.client-secret}")
    private String clientSecret;

    @Value("${keycloak.client.intra-service.grant-type}")
    private String grantType;

    @Bean
    public IntraServiceCredentialDomainModel intraServiceCredentialDomainModel() {
        return IntraServiceCredentialDomainModel.builder()
                .clientSecret(clientSecret)
                .clientId(clientId)
                .grantType(grantType)
                .build();
    }

}
