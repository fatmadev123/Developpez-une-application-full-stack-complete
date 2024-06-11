package com.openclassrooms.mddapi.service;

import com.openclassrooms.mddapi.exception.NotFoundException;
import com.openclassrooms.mddapi.model.User;
import com.openclassrooms.mddapi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    public UserService(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    public boolean existsByEmail (String email) {
        return userRepository.existsByEmail(email);
    }

    public void save (User user) {
        userRepository.save(user);
    }

    /**
     * get the user by id
     * @param id: user id
     * @return User: the user
     */
    public User getById(Long id) {
        return this.userRepository.findById(id).orElse(null);
    }

    /**
     * get the connected user
     * @return User: the connected user
     */
    public User getMe() {
        Optional<User> user = this.userRepository.findByEmail(SecurityContextHolder.getContext().getAuthentication().getName());

        if (user.isEmpty()) {
            throw new NotFoundException();
        }
        return user.get();
    }

    /**
     * update the connected user
     * @return User: the updated user
     */
    public User update(String email, String name, String password) {
        Optional<User> user = this.userRepository.findByEmail(SecurityContextHolder.getContext().getAuthentication().getName());

        if (user.isPresent()) {
            user.get().setEmail(email);
            user.get().setUserName(name);
            user.get().setPassword(passwordEncoder.encode(password));
        }
        return this.userRepository.save(user.get());
    }
}
