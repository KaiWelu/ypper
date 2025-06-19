package com.dci.ypper.service;

import com.dci.ypper.model.User;
import com.dci.ypper.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;



@Service
public class UserService {
    @Autowired
    private UserRepository repository;

    @Autowired
    private JWTService jwtService;

    @Autowired
    AuthenticationManager authenticationManager;


    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);

    public User getUserByName(String name) {return repository.findByName(name).orElseThrow();}

    public User register(User user) {
        user.setPassword(encoder.encode(user.getPassword())); // this will encrypt the password
        return repository.save(user);
    }

    public String verify(User user) {
        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(user.getName(), user.getPassword()));
        if(authentication.isAuthenticated()) {
            return jwtService.generateToken(user.getName());
        }
        return "Fail!";
    }

}
