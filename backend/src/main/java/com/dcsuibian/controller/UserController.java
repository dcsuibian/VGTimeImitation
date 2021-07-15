package com.dcsuibian.controller;
import com.dcsuibian.entity.User;
import com.dcsuibian.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

import static com.dcsuibian.controller.Util.builder;

@Slf4j
@RestController
@RequestMapping("/api/users")
public class UserController {
    private UserRepository userRepository;

    public UserController(@Autowired UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    @GetMapping
    public ResponseWrapper findAll() {
        Iterable<User> users = userRepository.findAll();
        return builder(users, "给你所有user", 200);
    }

    @GetMapping("/{id}")
    public ResponseWrapper findById(@PathVariable("id") Long id) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isPresent()) {
            return builder(optionalUser.get(), "给你这个user", 200);
        } else {
            return builder(null, "不存在这个user", 404);
        }
    }
    @PostMapping
    public ResponseWrapper save(@RequestBody User user){
        user = userRepository.save(user);
        return builder(user,"新增了一个user",201);
    }
}
