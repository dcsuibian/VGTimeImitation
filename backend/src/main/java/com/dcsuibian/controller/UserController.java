package com.dcsuibian.controller;

import com.dcsuibian.entity.User;
import com.dcsuibian.entity.vo.ResponseWrapper;
import com.dcsuibian.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.dcsuibian.util.Util.builder;

@Slf4j
@RestController
@RequestMapping("/api/users")
public class UserController {
    private UserService service;

    @Autowired
    public UserController(UserService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseWrapper<Iterable<User>> getAll() {
        Iterable<User> users = service.getAll();
        return builder(users, "给你所有user", 200);
    }

    @GetMapping("/{id}")
    public ResponseWrapper<User> getById(@PathVariable("id") long id) {
        User user = service.getById(id);
        if (null != user) {
            return builder(user, "给你这个user", 200);
        } else {
            return builder(null, "不存在这个user", 404);
        }
    }
}
