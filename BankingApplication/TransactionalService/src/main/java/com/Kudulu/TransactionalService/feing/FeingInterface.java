package com.Kudulu.TransactionalService.feing;

import com.Kudulu.TransactionalService.Model.Balance;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@FeignClient("ACCOUNTMICROSERVICE")
public interface FeingInterface {
    @GetMapping("bank/balance/{id}")
    public ResponseEntity<Double> getBalanceId(@PathVariable Long id);
    @PutMapping("bank/update/balance/{id}")
    public ResponseEntity<String> updateBalance(@PathVariable Long id, @RequestBody Balance balance );
    @GetMapping("bank/check/status/{id}")
    public ResponseEntity<Boolean> status(@PathVariable Long id);


}
