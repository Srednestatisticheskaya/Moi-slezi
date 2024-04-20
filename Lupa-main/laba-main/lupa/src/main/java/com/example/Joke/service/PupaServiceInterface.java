package com.example.Joke.service;

import com.example.Joke.model.DbPupaILupa;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface PupaServiceInterface {
    void addjoke(DbPupaILupa Jokes);
    List<DbPupaILupa> getAllJokes();
    Optional<DbPupaILupa> getJokeById(Long id);
    ResponseEntity<String> deleteJokeById(Long id);
    Optional<DbPupaILupa> editJokeById(Long id, String text);
}
