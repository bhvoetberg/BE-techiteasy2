package com.example.demo.services;

import com.example.demo.dtos.TelevisionInputDto;
import com.example.demo.dtos.TelevisionRequestDto;
import com.example.demo.exceptions.BadRequestException;
import com.example.demo.exceptions.RecordNotFoundException;
import com.example.demo.models.Television;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.repositories.TelevisionRepository;

import java.util.List;
import java.util.Optional;

@Service
public class TelevisionService {

    @Autowired
    private TelevisionRepository televisionRepository;

    public Iterable<Television> getAllTelevisions() {
        return televisionRepository.findAll();
    }

    public Television getTelevision(int id) {
        Optional<Television> optionalTelevision = televisionRepository.findById(id);
        if (optionalTelevision.isPresent()) {
            return optionalTelevision.get();
        } else {
            throw new RecordNotFoundException("ID does not exist!");
        }
    }

    public void deleteTelevision(int id) {
        if (televisionRepository.existsById(id)) {
            televisionRepository.deleteById(id);
        } else {
            throw new RecordNotFoundException("ID does not exist!!!");
        }
    }

    public int addTelevision(TelevisionRequestDto televisionRequestDto) {
        String name = televisionRequestDto.getName();
        List<Television> televisions = (List<Television>) televisionRepository.findAllByName(name);
        if (televisions.size() > 0) {
            System.out.println("Lengte is: " + televisions.size());
            throw new BadRequestException("Name already exists!!!");
        }

        Television television = new Television();
        television.setName(televisionRequestDto.getName());
        television.setType(televisionRequestDto.getType());
        television.setBrand(televisionRequestDto.getBrand());

        Television newTelevision = televisionRepository.save(television);
        return newTelevision.getId();
    }

    public void updateTelevision(int id, Television television) {
        Optional<Television> optionalTelevision = televisionRepository.findById(id);

        if (optionalTelevision.isPresent()) {
            Television storedTelevision = optionalTelevision.get();
            television.setId(storedTelevision.getId());
            televisionRepository.save(television);
        } else {
            throw new RecordNotFoundException("ID does not exist!!!");
        }
    }

    public void partialUpdateBook(int id, Television television) {
        Optional<Television> optionalTelevision = televisionRepository.findById(id);

        if (optionalTelevision.isPresent()) {
            Television storedTelevision = televisionRepository.findById(id).orElse(null);

            if (television.getName() != null && !television.getName().isEmpty()) {
                storedTelevision.setName(television.getName());
            }
            if (television.getBrand() != null && !television.getBrand().isEmpty()) {
                storedTelevision.setBrand(television.getBrand());
            }
            if (television.getType() != null && !television.getType().isEmpty()){
                storedTelevision.setType(television.getType());
            }
            televisionRepository.save(storedTelevision);

        } else {
            throw new RecordNotFoundException("ID does not exist!!!");
        }
    }

}
