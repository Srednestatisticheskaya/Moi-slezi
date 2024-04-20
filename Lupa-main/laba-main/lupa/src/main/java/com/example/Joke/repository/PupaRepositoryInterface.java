package com.example.Joke.repository;

import com.example.Joke.model.DbPupaILupa;
import org.springframework.data.jpa.repository.JpaRepository;


public interface PupaRepositoryInterface extends JpaRepository<DbPupaILupa, Long> {}
