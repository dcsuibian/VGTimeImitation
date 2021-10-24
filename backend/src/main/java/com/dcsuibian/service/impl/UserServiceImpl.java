package com.dcsuibian.service.impl;

import com.dcsuibian.entity.User;
import com.dcsuibian.entity.po.UserPO;
import com.dcsuibian.repository.UserPORepository;
import com.dcsuibian.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static com.dcsuibian.util.Util.batchConvert;

@Service
public class UserServiceImpl implements UserService {
    private UserPORepository poRepository;

    @Autowired
    public UserServiceImpl(UserPORepository poRepository) {
        this.poRepository = poRepository;
    }

    @Override
    public User getById(long id) {
        Optional<UserPO> optional = poRepository.findById(id);
        return optional.isPresent() ? UserPO.convert(optional.get()) : null;
    }

    @Override
    public Iterable<User> getAll() {
        return batchConvert(poRepository.findAll(), UserPO::convert);
    }
}
