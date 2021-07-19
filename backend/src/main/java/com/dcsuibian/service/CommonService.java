package com.dcsuibian.service;

public interface CommonService<T> {
    T getById(long id);
    Iterable<T> getAll();
}
