package com.mycompany.serviceone.service.otherservicecall;

import com.mycompany.serviceone.feing.ServiceTwoFeing;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ServiceTwoCallService {

    private final ServiceTwoFeing serviceTwoFeing;

    public String getServiceTwoDummyData(){
        return serviceTwoFeing.dummyData().getBody();
    }
}
