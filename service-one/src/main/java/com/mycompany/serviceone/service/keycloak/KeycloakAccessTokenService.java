package com.mycompany.serviceone.service.keycloak;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mycompany.serviceone.domainmodel.IntraServiceCredentialDomainModel;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestClient;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class KeycloakAccessTokenService {
    @Value("${keycloak.base-url}")
    private String baseUrl;

    @Value("${keycloak.realm}")
    private String realm;


    private final RestClient restClient;

    public String getAccessToken(IntraServiceCredentialDomainModel intraServiceCredentialDomainModel) throws JsonProcessingException {

        String uri = baseUrl + "/realms/" + realm + "/protocol/openid-connect/token";
        MultiValueMap<String, String> formData = new LinkedMultiValueMap<>();
        formData.add("client_id", intraServiceCredentialDomainModel.getClientId());
        formData.add("client_secret", intraServiceCredentialDomainModel.getClientSecret());
        formData.add("grant_type", intraServiceCredentialDomainModel.getGrantType());

        ResponseEntity<String> response = restClient.post()
                .uri(uri)
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .body(formData)
                .retrieve()
                .toEntity(String.class);
        if (response.getStatusCode() == HttpStatus.OK) {
            String responseBody = response.getBody();

            ObjectMapper objectMapper = new ObjectMapper();
            Map<String, String> responseMap = objectMapper.readValue(responseBody, new TypeReference<Map<String, String>>() {
            });

            return responseMap.get("access_token");
        }else {
            throw new RuntimeException("Failed to obtain access token. Status code: " + response.getStatusCode());
        }

    }
}
