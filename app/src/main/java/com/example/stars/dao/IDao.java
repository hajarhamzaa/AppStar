package com.example.stars.dao;

import java.util.List;

public interface IDao<T> {
    boolean create(T o);

    boolean update(T o);

    List<T> finAll();

    T finById(int id);

    boolean delete(T o);
}
