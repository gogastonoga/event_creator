package com.capgemini.wolimierz.userregistry.service;

import com.capgemini.wolimierz.userregistry.UserCreateDto;
import com.capgemini.wolimierz.userregistry.model.Role;
import com.capgemini.wolimierz.userregistry.model.User;
import com.capgemini.wolimierz.userregistry.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Collection;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PostConstruct
    public void init() {
        if (userRepository.findAll().isEmpty()) {
            create(new UserCreateDto("Maciej", "Chrzan", "macchrz", "maciej.chrzan@capgemini.com", Role.ADMIN));
        }
    }

    @Override
    public Optional<User> getUserById(long id) {
        return Optional.ofNullable(userRepository.findOne(id));
    }

    @Override
    public Optional<User> getUserByEmail(String email) {
        return Optional.ofNullable(userRepository.findByEmail(email));
    }

    @Override
    public Collection<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User create(UserCreateDto userCreateDto) {
        User user = new User();
        user.setName(userCreateDto.getName());
        user.setSurname(userCreateDto.getSurname());
        user.setEmail(userCreateDto.getEmail());
        user.setPassword(new BCryptPasswordEncoder().encode(userCreateDto.getPassword()));
        // user.setPassword(new ShaPasswordEncoder(ENCODING_STRENGTH).encodePassword(userCreateDto.getPassword(), user.getEmail()));
        return userRepository.save(user);
    }
}
