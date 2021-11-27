package com.example.demo.services;

import com.example.demo.dtos.CIModuleRequestDto;
import com.example.demo.exceptions.BadRequestException;
import com.example.demo.exceptions.RecordNotFoundException;
import com.example.demo.models.CIModule;
import com.example.demo.repositories.CIModuleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CIModuleService {
    @Autowired
    private CIModuleRepository ciModuleRepository;

    public Iterable<CIModule> getAllCIModules() {
        return ciModuleRepository.findAll();
    }

    public CIModule getCIModule(long id) {
        Optional<CIModule> optionalCIModule = ciModuleRepository.findById(id);
        if (optionalCIModule.isPresent()) {
            return optionalCIModule.get();
        } else {
            throw new RecordNotFoundException("ID does not exist!");
        }
    }

    public void deleteCIModule(long id) {
        if (ciModuleRepository.existsById(id)) {
            ciModuleRepository.deleteById(id);
        } else {
            throw new RecordNotFoundException("ID does not exist!!!");
        }
    }

    public Long addCIModule(CIModuleRequestDto ciModuleRequestDto) {
        String name = ciModuleRequestDto.getName();
        List<CIModule> ciModules = (List<CIModule>) ciModuleRepository.findAllByName(name);
        if (ciModules.size() > 0) {
            System.out.println("Lengte is: " + ciModules.size());
            throw new BadRequestException("Name already exists!!!");
        }

        CIModule ciModule = new CIModule();
        ciModule.setName(ciModuleRequestDto.getName());
        ciModule.setPrice(ciModuleRequestDto.getPrice());

        CIModule newCIModule = ciModuleRepository.save(ciModule);
        return newCIModule.getId();
    }

//    public void updateTelevision(int id, Television television) {
//        Optional<Television> optionalTelevision = televisionRepository.findById(id);
//
//        if (optionalTelevision.isPresent()) {
//            Television storedTelevision = optionalTelevision.get();
//            television.setId(storedTelevision.getId());
//            televisionRepository.save(television);
//        } else {
//            throw new RecordNotFoundException("ID does not exist!!!");
//        }
//    }
//
//    public void partialUpdateBook(int id, Television television) {
//        Optional<Television> optionalTelevision = televisionRepository.findById(id);
//
//        if (optionalTelevision.isPresent()) {
//            Television storedTelevision = televisionRepository.findById(id).orElse(null);
//
//            if (television.getName() != null && !television.getName().isEmpty()) {
//                storedTelevision.setName(television.getName());
//            }
//            if (television.getBrand() != null && !television.getBrand().isEmpty()) {
//                storedTelevision.setBrand(television.getBrand());
//            }
//            if (television.getType() != null && !television.getType().isEmpty()){
//                storedTelevision.setType(television.getType());
//            }
//            televisionRepository.save(storedTelevision);
//
//        } else {
//            throw new RecordNotFoundException("ID does not exist!!!");
//        }
//    }

}
