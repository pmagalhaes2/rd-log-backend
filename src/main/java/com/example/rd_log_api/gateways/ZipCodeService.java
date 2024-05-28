package com.example.rd_log_api.gateways;

import com.example.rd_log_api.service.ZipCodeResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Service
@FeignClient(name = "zipcode", url = "https://viacep.com.br/ws")
public interface ZipCodeService {
    @GetMapping("/{cep}/json")
    ZipCodeResponse getZipCode(@PathVariable("cep") String zipcode);
}
