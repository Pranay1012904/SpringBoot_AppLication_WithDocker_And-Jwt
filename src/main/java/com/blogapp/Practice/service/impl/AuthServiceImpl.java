package com.blogapp.Practice.service.impl;

import com.blogapp.Practice.dto.LoginDto;
import com.blogapp.Practice.dto.RegisterDto;
import com.blogapp.Practice.entity.Role;
import com.blogapp.Practice.entity.User;
import com.blogapp.Practice.exception.BlogApiException;
import com.blogapp.Practice.repository.RoleRepository;
import com.blogapp.Practice.repository.UserRepository;
import com.blogapp.Practice.service.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class AuthServiceImpl implements AuthService {

    private AuthenticationManager authenticationManager;
    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;

    @Override
    public String logging(LoginDto loginDto) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDto.getUsernameOrEmail(),
                loginDto.getPassword()));
        //to store authentication object into spring security context holder
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return "User Logged In Successfully!";
    }

    @Override
    public String register(RegisterDto registerDto) {
        if (userRepository.existsByUsername(registerDto.getUsername())) {
            throw new BlogApiException(String.format("USERNAME %s EXISTS !", registerDto.getUsername()));
        }
        if (userRepository.existsByEmail(registerDto.getEmail())) {
            throw new BlogApiException(String.format("EMAIL %s EXISTS!", registerDto.getEmail()));
        }
        User user = new User();
        user.setName(registerDto.getName());
        user.setUsername(registerDto.getUsername());
        user.setEmail(registerDto.getEmail());
        user.setPassword(passwordEncoder.encode(registerDto.getPassword()));
        List<Role> userRole = new ArrayList<>();
        Role role = roleRepository.findByRole("ROLE_USER").get();
        userRole.add(role);
        user.setUserRole(userRole);
        User savedUser = userRepository.save(user);
        return "USER REGISTERED SUCCESSFULLY";
    }
}
