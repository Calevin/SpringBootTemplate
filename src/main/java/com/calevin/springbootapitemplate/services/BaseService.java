package com.calevin.springbootapitemplate.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public abstract class BaseService<T, I, R extends JpaRepository<T, I>> {
    @Autowired
    protected R repository;

    public T save(T t) {
        return repository.save(t);
    }

    public Optional<T> findById(I id) {
        return repository.findById(id);
    }

    public List<T> findAll() {
        return repository.findAll();
    }

    public Page<T> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    public T edit(T t) {
        return repository.save(t);
    }

    public void delete(T t) {
        repository.delete(t);
    }

    public void deleteById(I id) {
        repository.deleteById(id);
    }
}
