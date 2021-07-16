package com.dcsuibian.service;

public interface CommonService<T> {
    T getById(long id);
    Iterable<T> getAll();
    T save(T t); // TODO 这个方法还非常有问题
}
