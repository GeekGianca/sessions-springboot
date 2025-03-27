package com.example.session.core.domain.service;

import com.example.session.core.domain.persistence.IUserRepository;
import com.example.session.core.model.UserEntity;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomUserDetailService implements UserDetailsService {
    private final IUserRepository userRepository;

    public CustomUserDetailService(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity mUser = userRepository.findByEmail(username).orElseThrow();
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("admin"));
        if (mUser.getIsEnable()) {
            return new org.springframework.security.core.userdetails.User(mUser.getEmail(), mUser.getPassword(), authorities);
        } else {
            throw new UsernameNotFoundException("El usuario no ha sido activado.");
        }
    }
}
