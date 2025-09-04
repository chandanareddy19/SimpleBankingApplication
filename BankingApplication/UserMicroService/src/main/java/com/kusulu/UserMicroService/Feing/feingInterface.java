package com.kusulu.UserMicroService.Feing;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient("ACCOUNTMICROSERVICE")
public interface feingInterface {
    @GetMapping("/bank/deactivate/{id}")
    public ResponseEntity<String> dectivateTheAccount(@PathVariable Long id);

    @GetMapping("/bank/activate/{id}")
    public ResponseEntity<String> activateAccount(@PathVariable Long id);


}
