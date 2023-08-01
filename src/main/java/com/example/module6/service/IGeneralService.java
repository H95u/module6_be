package com.example.module6.service;

import java.util.List;
import java.util.Optional;

public interface IGeneralService<E, ID> {

    List<E> findAll();

    Optional<E> findOne(ID id);

    void save(E e);

    void delete(ID id);
}
