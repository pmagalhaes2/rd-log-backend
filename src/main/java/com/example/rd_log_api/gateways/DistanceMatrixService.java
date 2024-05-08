package com.example.rd_log_api.gateways;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Service
@FeignClient(name = "distance", url =
        "https://maps.googleapis.com/maps/api")
public interface DistanceMatrixService {
    @GetMapping("/distancematrix/json")
    String getDistance(@RequestParam("origins") String origins, @RequestParam("destinations") String destinations,
                       @RequestParam("key") String key);

}
