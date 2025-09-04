package com.kusulu.UserMicroService.Service;

import com.kusulu.UserMicroService.Feing.feingInterface;
import com.kusulu.UserMicroService.Model.Users;
import com.kusulu.UserMicroService.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.sql.SQLOutput;

@Service
public class UserService {
    @Autowired
    feingInterface feing;
    @Autowired
    UserRepository repo;

    public ResponseEntity<Users> createUser(Users user) {
        try {
            Users savedUser = repo.save(user);
            return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace(); // For logging purposes
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<Users> getByUserId(int id) {
        try {
            Users user = repo.findById(id).orElse(null);

            if (user == null) {
                return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
            }

            return new ResponseEntity<>(user, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    public ResponseEntity<String> updateById(Users user, int id) {
        try {
            Users existingUser = repo.findById(id).get();

            if (existingUser == null) {
                return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
            }

            existingUser.setAddress(user.getAddress());
            existingUser.setEmail(user.getEmail());
            existingUser.setPhone(user.getPhone());
            existingUser.setFullName(user.getFullName());
            existingUser.setUsername(user.getUsername());

            repo.save(existingUser);
            return new ResponseEntity<>("Updated successfully", HttpStatus.OK);

        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("Failed to update user", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    public ResponseEntity<String> deactivateAccount(Long id) {

        return feing.dectivateTheAccount(id);
    }

    public ResponseEntity<String> activatedAccount(Long id) {

        return feing.activateAccount(id);

    }
}
