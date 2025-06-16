package com.dci.ypper.service;

import com.dci.ypper.model.User;
import com.dci.ypper.model.UserPrincipal;
import com.dci.ypper.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
public class YpprUserDetailsService implements UserDetailsService {
    @Autowired
    private UserRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = repository.findByName(username).orElseThrow(
                () -> new UsernameNotFoundException("User not found: " + username));
        return new UserPrincipal(user);
    }
}
