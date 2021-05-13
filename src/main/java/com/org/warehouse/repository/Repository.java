package com.org.warehouse.repository;

public interface Repository<T> {

    T save(T entity);
    T find(Integer id);
}
