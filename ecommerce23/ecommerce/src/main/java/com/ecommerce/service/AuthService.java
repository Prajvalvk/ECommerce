package com.ecommerce.service;

import com.ecommerce.entity.*;
import com.ecommerce.exception.ApiException;
import com.ecommerce.repository.*;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service @RequiredArgsConstructor
public class AuthService {
    private final UserService users;

    public User register(com.ecommerce.dto.RegisterRequest r) {
        User u = new User();
        u.setName(r.name()); u.setEmail(r.email()); u.setPassword(r.password());
        return users.create(u);
    }
    public User login(com.ecommerce.dto.LoginRequest r) { return users.login(r.email(), r.password()); }
}
