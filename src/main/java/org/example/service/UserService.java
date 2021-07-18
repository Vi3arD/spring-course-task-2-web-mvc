package org.example.service;

import lombok.RequiredArgsConstructor;
import org.example.domain.User;
import org.example.exception.AuthorizationFailedException;
import org.example.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository repository;

    public User getUserByName(String name) {
        return repository.getByName(name).orElseThrow(AuthorizationFailedException::new);
    }

    public User checkUser(String login, String password) {
        User user = getUserByName(login);
        if (!user.getPassword().equals(password)) {
            throw new AuthorizationFailedException();
        }
        return user;
    }

}
