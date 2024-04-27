package ru.mirn.auth.service;

import lombok.AllArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.mirn.auth.model.Role;
import ru.mirn.auth.model.User;
import ru.mirn.auth.repository.UserRepository;

@Service
@AllArgsConstructor
public class UserService {
    private final UserRepository repository;

    public User save(User user) {
        return repository.save(user);
    }

    public User create(User user) {
        if (repository.existsByUsername(user.getUsername())) {
            throw new RuntimeException("user with this name already exists");
        }
        if (repository.existsByEmail(user.getEmail())) {
            throw new RuntimeException("user with this email already exists");
        }
        return repository.save(user);
    }

    public User getByUsername(String username) {
        return repository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("user not found"));
    }

    public UserDetailsService userDetailsService() {
        return this::getByUsername;
    }

    public User getCurrentUser() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        return getByUsername(username);
    }

    public void getAdmin() {
        User user = getCurrentUser();
        user.setRole(Role.ADMIN);
        save(user);
    }
}
