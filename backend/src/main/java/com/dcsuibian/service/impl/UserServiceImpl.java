package com.dcsuibian.service.impl;

import com.dcsuibian.entity.User;
import com.dcsuibian.repository.UserRepository;
import com.dcsuibian.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;

    public UserServiceImpl(@Autowired UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User getById(long id) {
        Optional<User> userOptional = userRepository.findById(id);
        if(userOptional.isPresent()){
            return userOptional.get();
        }else{
            return null;
        }
    }

    @Override
    public Iterable<User> getAll() {
        return userRepository.findAll();
    }

    @Override
    public void add(User user) {
    }

    @Override
    public void deleteById(long id) {
        userRepository.deleteById(id);
    }
}
