package com.example.demo.controllers;

import com.example.demo.dtos.TelevisionRequestDto;
import com.example.demo.models.Television;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import com.example.demo.services.TelevisionService;

import java.net.URI;

@RestController
public class TelevisionController {

    @Autowired
    private TelevisionService televisionService;

    @GetMapping(value ="/televisions")
    public ResponseEntity<Object> getTelevisions() {
        return ResponseEntity.ok(televisionService.getAllTelevisions());
    }

    @GetMapping(value = "/televisions/{id}")
    public ResponseEntity<Object> getTelevision(@PathVariable int id) {
        return ResponseEntity.ok(televisionService.getTelevision(id));
    }

    //    Hieronder een specifieke weergave van de PathVariable die nodig is al er in de 'value' meerdere
//    variabelen staan die bepalen welk record moet worden verwijderd.
    @DeleteMapping(value = "/televisions/{id}")
    public ResponseEntity<Object> deleteBook(@PathVariable("id") int id) {
        televisionService.deleteTelevision(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping(value = "/televisions")
    public ResponseEntity<Object> addTelevision(@RequestBody TelevisionRequestDto televisionRequestDto) {
        int newId = televisionService.addTelevision(televisionRequestDto);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").
                buildAndExpand(newId).toUri();
        return ResponseEntity.created(location).build();
    }


    @PutMapping(value = "/televisions/{id}")
    public ResponseEntity<Object> updateTelevision(@PathVariable int id, @RequestBody Television television) {
        televisionService.updateTelevision(id, television);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping(value = "/televisions/{id}")
    public ResponseEntity<Object> partialUpdateBook(@PathVariable int id, @RequestBody Television television) {
        televisionService.partialUpdateBook(id, television);
        return ResponseEntity.noContent().build();
    }
}
