package com.kusulu.AccountMicroService.Service;

import com.kusulu.AccountMicroService.Model.Account;
import com.kusulu.AccountMicroService.Model.Balance;
import com.kusulu.AccountMicroService.Repository.AccountRepo;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class AccountService {
    @Autowired
    AccountRepo repo;
    @Autowired
    Account acc;

    public ResponseEntity<Account> createBankAccount(Account details) {
        repo.save(details);
        System.out.println(acc.getBalance());
        return new ResponseEntity<>(details, HttpStatus.CREATED);
    }
        public ResponseEntity<Account> getByUserId(Long id) {
            try {
                Account account = repo.findByUserId(id);

                if (account != null) {
                    return new ResponseEntity<>(account, HttpStatus.OK);
                } else {
                    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
                }
            } catch (Exception e) {
                e.printStackTrace();
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        public ResponseEntity<List<Account>> getAllHolderDetails() {
            try {
                List<Account> allHolders = repo.findAll();

                if (allHolders.isEmpty()) {
                    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
                }

                return new ResponseEntity<>(allHolders, HttpStatus.OK);
            } catch (Exception e) {
                e.printStackTrace();
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

    public ResponseEntity<List<Account>> CurrentBank(String type) {
        try {
            List<Account> current = repo.findByType(type);

            if (current.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(current, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
    @Transactional
    public ResponseEntity<String> deactivateTheAccount(Long userId) {
        try {
            repo.deactivateByUserId(userId);
            return new ResponseEntity<>("deactivated", HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("Failed to deactivate account", HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
    public ResponseEntity<String> activateAccount(Long id ) {
        try {
            Account account=repo.findByUserId(id);
            account.setActive(true);
            repo.save(account);
            return new ResponseEntity<>("activated", HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("Failed to deactivate account", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<Double> getBalanceId(Long id) {
        Account acc=repo.findByUserId(id);
        Double balance=acc.getBalance();
        return new ResponseEntity<>(balance,HttpStatus.OK);
    }

    public ResponseEntity<String> updateBalance(Long id,  Balance balance) {
        Account acc=repo.findByUserId(id);
        Double updateBalance=balance.getBalance();
        System.out.println(updateBalance);
        acc.setBalance(updateBalance);
        repo.save(acc);
        return new ResponseEntity<>("updated",HttpStatus.OK);
    }

    public ResponseEntity<Boolean> status(Long id) {
        Account acc=repo.findByUserId(id);
        boolean check=acc.isActive();
        return new ResponseEntity<>(check,HttpStatus.OK);
    }
}
