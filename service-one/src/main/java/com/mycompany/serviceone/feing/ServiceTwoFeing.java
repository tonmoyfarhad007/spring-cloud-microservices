package com.mycompany.serviceone.feing;


import com.mycompany.serviceone.config.feingclient.IntraServiceFeignClientConfiguration;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "SERVICE-TWO", configuration = IntraServiceFeignClientConfiguration.class)
public interface ServiceTwoFeing {

    @GetMapping("/api/v1/private/service/two/dummy-data")
    public ResponseEntity<String> dummyData();
}
