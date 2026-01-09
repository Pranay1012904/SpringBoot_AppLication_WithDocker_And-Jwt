package com.blogapp.Practice.security;

import com.blogapp.Practice.entity.User;
import com.blogapp.Practice.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String usernameOrEmail) throws UsernameNotFoundException {
       User fetchedUser= userRepository.findByUsernameOrEmail(usernameOrEmail, usernameOrEmail).orElseThrow(()-> new UsernameNotFoundException(String.format("NO USER WITH USERNAME OR EMAIL :: %s FOUND!",usernameOrEmail)));
        Set<GrantedAuthority> authorities=fetchedUser.getUserRole()
                .stream()
                .map(role-> new SimpleGrantedAuthority(role.getRole())).collect(Collectors.toSet());

       return new org.springframework.security.core.userdetails.User(fetchedUser.getEmail(), fetchedUser.getPassword(), authorities );
    }
}
