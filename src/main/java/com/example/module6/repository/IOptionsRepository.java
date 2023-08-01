package com.example.module6.repository;

import com.example.module6.model.Options;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IOptionsRepository extends JpaRepository<Options, Long> {
}
