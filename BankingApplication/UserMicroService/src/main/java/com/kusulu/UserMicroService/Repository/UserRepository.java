package com.kusulu.UserMicroService.Repository;

import com.kusulu.UserMicroService.Model.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<Users,Integer> {


}
