package com.kusulu.AccountMicroService.Repository;

import com.kusulu.AccountMicroService.Model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AccountRepo extends JpaRepository<Account,Integer> {

    Account findByUserId(Long userId);

    @Query("FROM Account a WHERE a.accountType = :type")
    List<Account> findByType( @Param("type") String type);

    @Modifying
    @Query("UPDATE Account a SET a.active = false WHERE a.userId = :userId")
    void deactivateByUserId(@Param("userId") Long userId);

    @Query("UPDATE Account a SET a.active = true WHERE a.userId = :userId")
    void activateByUserId( @Param("userId") Long userId);
}
