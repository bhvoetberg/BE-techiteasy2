package com.example.demo.services;

import com.example.demo.exceptions.RecordNotFoundException;
import com.example.demo.models.Television;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.repositories.TelevisionRepository;
import java.util.Optional;

@Service
public class TelevisionService {

    @Autowired
    private TelevisionRepository televisionRepository;

    public Iterable<Television> getTelevisions() {
            return televisionRepository.findAll();
    }

    public Television getTelevision(int id) {
        Optional<Television> optionalTelevision = televisionRepository.findById(id);

        if (optionalTelevision.isPresent()) {
            return optionalTelevision.get();
        }
        else {
            throw new RecordNotFoundException("ID does not exist!");
        }
    }

    public int addTelevision(Television television) {
        Television newTelevision = televisionRepository.save(television);
        return newTelevision.getId();
    }

   public void deleteBook(int id) {
        if (televisionRepository.existsById(id)) {
            televisionRepository.deleteById(id);
        }
        else {
            throw new RecordNotFoundException("ID does not exist!!!");
        }
    }

    public void updateTelevision(int id, Television television) {
        Optional<Television> optionalTelevision = televisionRepository.findById(id);

        if (optionalTelevision.isPresent()) {
//            Television storedTelevision = optionalTelevision.get();
//
//            television.setId(storedTelevision.getId());
            televisionRepository.save(television);
        }
        else {
            throw new RecordNotFoundException("ID does not exist!!!");
        }
    }


}
