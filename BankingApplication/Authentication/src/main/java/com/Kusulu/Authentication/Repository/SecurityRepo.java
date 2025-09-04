package com.Kusulu.Authentication.Repository;

import com.Kusulu.Authentication.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SecurityRepo extends JpaRepository<User,Integer> {
    User findByusername(String username);
}
