package com.example.demo.controllers;

import com.example.demo.dtos.CIModuleRequestDto;
import com.example.demo.models.CIModule;
import com.example.demo.services.CIModuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
public class CIModuleController {
    @Autowired
    private CIModuleService ciModuleService;

    @GetMapping(value ="/cimodules")
    public ResponseEntity<Object> getCIModules() {
        return ResponseEntity.ok(ciModuleService.getAllCIModules());
    }

    @GetMapping(value = "/cimodules/{id}")
    public ResponseEntity<Object> getCIModule(@PathVariable int id) {
        return ResponseEntity.ok(ciModuleService.getCIModule(id));
    }


    @DeleteMapping(value = "/cimodules/{id}")
    public ResponseEntity<Object> deleteBook(@PathVariable("id") long id) {
        ciModuleService.deleteCIModule(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping(value = "/cimodules")
    public ResponseEntity<Object> addCIModule(@RequestBody CIModuleRequestDto ciModuleRequestDto) {
        Long newId = ciModuleService.addCIModule(ciModuleRequestDto);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").
                buildAndExpand(newId).toUri();
        return ResponseEntity.created(location).build();
    }


    @PutMapping(value = "/cimodules/{id}")
    public ResponseEntity<Object> updateCIMOdule(@PathVariable Long id, @RequestBody CIModule ciModule) {
        ciModuleService.updateCIModule(id, ciModule);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping(value = "/cimodules/{id}")
    public ResponseEntity<Object> partialUpdateCIModule(@PathVariable Long id, @RequestBody CIModule ciModule) {
        ciModuleService.partialUpdateCIModuleBook(id, ciModule);
        return ResponseEntity.noContent().build();
    }

}
