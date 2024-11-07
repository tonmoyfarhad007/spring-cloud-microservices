package com.mycompany.serviceone.domainmodel;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
public class IntraServiceCredentialDomainModel {

    private String grantType;
    private String clientSecret;
    private String clientId;
}
