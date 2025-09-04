package com.kusulu.AccountMicroService.Controllers;

import com.kusulu.AccountMicroService.Model.Account;
import com.kusulu.AccountMicroService.Model.Balance;
import com.kusulu.AccountMicroService.Service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/bank")
public class AccountController {
    @Autowired
    AccountService service;

    @PostMapping("create")
    public ResponseEntity<Account> createBankAccount(@RequestBody Account details){

        return service.createBankAccount(details);
    }
    @RequestMapping("get/{id}")
    public ResponseEntity<Account> getByUserId(@PathVariable Long id){
        return service.getByUserId(id);
    }
    @RequestMapping("allDetails")
    public ResponseEntity<List<Account>> getAllHolderDetails(){
        return service.getAllHolderDetails();
    }
    @RequestMapping("type/{type}")
    public ResponseEntity<List<Account>> BankType(@PathVariable String type){
        return service.CurrentBank(type);
    }
    @GetMapping("deactivate/{id}")
    public ResponseEntity<String> dectivateTheAccount(@PathVariable Long id){
        return service.deactivateTheAccount(id);
    }
    @GetMapping("activate/{id}")
    public ResponseEntity<String> activateAccount(@PathVariable Long id){
        return service.activateAccount(id);
    }
    @GetMapping("balance/{id}")
    public ResponseEntity<Double> getBalanceId(@PathVariable Long id){
        return service.getBalanceId(id);
    }
    @PutMapping("update/balance/{id}")
    public ResponseEntity<String> updateBalance(@PathVariable Long id, @RequestBody Balance balance ){
        System.out.println(balance.getBalance());
        return service.updateBalance(id,balance);
    }
    @GetMapping("check/status/{id}")
    public ResponseEntity<Boolean> status(@PathVariable Long id){
        return service.status(id);
    }
}
