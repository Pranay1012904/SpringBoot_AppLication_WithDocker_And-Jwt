package com.blogapp.Practice.service;


import com.blogapp.Practice.dto.LoginDto;
import com.blogapp.Practice.dto.RegisterDto;

public interface AuthService {
    String logging(LoginDto loginDto);
    String register(RegisterDto registerDto);
}
