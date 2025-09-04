package com.Kudulu.TransactionalService.Service;

import com.Kudulu.TransactionalService.Model.Balance;
import com.Kudulu.TransactionalService.Model.Transactions;
import com.Kudulu.TransactionalService.Model.Transfer;
import com.Kudulu.TransactionalService.Repository.TransactionalRepo;
import com.Kudulu.TransactionalService.feing.FeingInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class TransactionalService {
    @Autowired
    TransactionalRepo repo;
    @Autowired
    FeingInterface feingInterface;
    @Autowired
    Balance bal;
    @Autowired
    Transactions transactions;
    public ResponseEntity<String> transaction(Transactions transactions) {
        boolean status=feingInterface.status((long)transactions.getUserId()).getBody();
        if(status) {
            String transctionType = transactions.getType();
            if (transctionType.equals("Withdraw")) {
                Double balance = feingInterface.getBalanceId((long) transactions.getUserId()).getBody();
                Double withDraw = transactions.getAmount();
                if (balance >= withDraw) {
                    Double remainingBalance = balance - withDraw;
                    Balance updatedBalnce = new Balance();
                    updatedBalnce.setBalance(remainingBalance);
                    feingInterface.updateBalance((long) transactions.getUserId(), updatedBalnce);
                    repo.save(transactions);
                    return new ResponseEntity<>("withdraw completed", HttpStatus.OK);
                } else {
                    return new ResponseEntity<>("cannot withdraw", HttpStatus.OK);
                }
            } else if (transctionType.equals("Deposit")) {
                Double balance = feingInterface.getBalanceId((long) transactions.getUserId()).getBody();
                Double deposit = transactions.getAmount();
                Double updatedBalance = balance + deposit;
                bal.setBalance(updatedBalance);
                feingInterface.updateBalance((long) transactions.getUserId(), bal);
                repo.save(transactions);
                return new ResponseEntity<>("Deposit completed", HttpStatus.OK);
            }
        }
        else{
            return new ResponseEntity<>("activate the account to do transactions",HttpStatus.OK);
        }


        return new ResponseEntity<>("internalServerIssue", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    public ResponseEntity<List<Transactions>> typeOfTransaction(int userId) {
        List<Transactions> listTransactions=repo.findByUserId(userId);
        return new ResponseEntity<>(listTransactions, HttpStatus.OK);
    }

    public ResponseEntity<String> transferFund(Transfer transfer) {
        boolean status1=feingInterface.status((long)transfer.getId1()).getBody();
        boolean status2=feingInterface.status((long)transfer.getId2()).getBody();
        if(status1 && status2) {
            int id1 = transfer.getId1();
            Double balance1 = feingInterface.getBalanceId((long) id1).getBody();
            if(balance1>=transfer.getTransferFund()) {
                Double withdrawAmount = balance1 - transfer.getTransferFund();
                bal.setBalance(withdrawAmount);
                feingInterface.updateBalance((long) id1, bal);
                int id2 = transfer.getId2();
                Double balance2 = feingInterface.getBalanceId((long) id2).getBody();
                Double transferBalance = transfer.getTransferFund() + balance2;
                bal.setBalance(transferBalance);
                System.out.println(bal.getBalance());
                feingInterface.updateBalance((long) id2, bal);
                transactions = new Transactions(id1, "Withdraw", "transfer to" + (long) id2, transfer.getTransferFund(), transfer.getTime());
                repo.save(transactions);
                transactions = new Transactions(id2, "Deposit", "transfer from" + (long) id1, transfer.getTransferFund(), transfer.getTime());
                repo.save(transactions);
                return new ResponseEntity<>("transferd successfully", HttpStatus.OK);
            }
            else{
                return new ResponseEntity<>("you dont have balance to transfer the money",HttpStatus.OK);
            }
        }
        return new ResponseEntity<>("need to activate the account to transfer",HttpStatus.OK);
    }
}
