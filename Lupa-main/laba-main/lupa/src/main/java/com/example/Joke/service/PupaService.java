package com.example.Joke.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import com.example.Joke.model.DbPupaILupa;
import com.example.Joke.repository.PupaRepositoryInterface;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class PupaService implements PupaServiceInterface{

    private final PupaRepositoryInterface jokes;

    @Override
    public void addjoke(DbPupaILupa joke){
        joke.setCreated(new Date());
        jokes.save(joke);

    }
    @Override
    public List<DbPupaILupa> getAllJokes(){
        return jokes.findAll();
    }
    @Override
    public Optional<DbPupaILupa> getJokeById(Long id){
        return jokes.findById(id);
    }
    @Override
    public ResponseEntity<String> deleteJokeById(Long id){
        if (jokes.findById(id).isPresent()){
            jokes.deleteById(id);
            return ResponseEntity.ok("Шутка успешно удалена с id: "+id);
        }
        else{
            return ResponseEntity.notFound().build();
        }
    }
    @Override
    public Optional<DbPupaILupa> editJokeById(Long id, String text) {
        Optional<DbPupaILupa> optionalJoke = jokes.findById(id);
        if (optionalJoke.isPresent()){
            DbPupaILupa joke = optionalJoke.get();
            joke.setText(text);
            joke.setUpdated(new Date());
            jokes.save(joke);
        }
        return optionalJoke;
    }

    public long getNumberid() {
        return jokes.count();
    }
}
