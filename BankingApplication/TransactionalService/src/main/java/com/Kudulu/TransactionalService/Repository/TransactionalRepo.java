package com.Kudulu.TransactionalService.Repository;

import com.Kudulu.TransactionalService.Model.Transactions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface TransactionalRepo extends JpaRepository<Transactions,Integer> {
    @Query("FROM Transactions t WHERE t.UserId = :userId")
    List<Transactions> findByUserId(@Param("userId") int userId);

}
