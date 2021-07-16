package com.dcsuibian.service;

import com.dcsuibian.entity.User;

public interface UserService {
    User getById(long id);
    Iterable<User> getAll();
    void add(User user);
    void deleteById(long id);
}
