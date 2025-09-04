package com.Kudulu.TransactionalService.Controller;

import com.Kudulu.TransactionalService.Model.Transactions;
import com.Kudulu.TransactionalService.Model.Transfer;
import com.Kudulu.TransactionalService.Service.TransactionalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TransactionalController {
    @Autowired
    TransactionalService service;

  @PostMapping("/Transaction")
    public ResponseEntity<String> transaction(@RequestBody Transactions transactions){
     return service.transaction(transactions);
  }
  @GetMapping("get/{userId}")
    public ResponseEntity<List<Transactions>> typeOfTranscation(@PathVariable int userId){
      return service.typeOfTransaction(userId);
  }
   @PostMapping("transfer/funds")
    public ResponseEntity<String> transferFund(@RequestBody  Transfer transfer){
      return service.transferFund(transfer);
   }


}
