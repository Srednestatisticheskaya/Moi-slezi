package com.example.Joke.Controller;

import com.example.Joke.model.DbPupaILupa;
import com.example.Joke.service.PupaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/jokes")
public class Lupa_I_Pupa {

    private final PupaService service;

    @PostMapping
    ResponseEntity<Void> addjoke(@RequestBody DbPupaILupa text) {
        service.addjoke(text);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    ResponseEntity<List<DbPupaILupa>> showAllJokes() {
        return ResponseEntity.ok(service.getAllJokes());
    }

    @GetMapping("/{id}")
    ResponseEntity<DbPupaILupa> showjokebyid(@PathVariable Long id) {
        return service.getJokeById(id).map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    ResponseEntity<String> deletejokebyid(@PathVariable Long id) {
        return service.deleteJokeById(id);
    }

    @PutMapping("/{id}")
    ResponseEntity<DbPupaILupa> editjokebyid(@PathVariable Long id, @RequestBody DbPupaILupa text) {
        return service.editJokeById(id, text.getText()).map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    public Long getNumber() {
        return service.getNumberid();
    }
}
