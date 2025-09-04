package com.Kusulu.Authentication.Service;

import com.Kusulu.Authentication.Model.User;
import com.Kusulu.Authentication.Repository.SecurityRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class LoginService {
    @Autowired
    SecurityRepo repo;
    @Autowired
    AuthenticationManager auth;
    @Autowired
    JwtService jwt;
    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    public User details(User user) {
        System.out.println(user.getPassword());
        user.setPassword(encoder.encode(user.getPassword()));
        repo.save(user);
        return user;
    }

    public String verify(User user) {
        Authentication authentication=auth.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(),user.getPassword()));
        if(authentication.isAuthenticated()){
            return jwt.generateToken(user.getUsername());
        }
        return "fail";
    }
}
