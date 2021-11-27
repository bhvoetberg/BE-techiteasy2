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

    public void updateCIModule(Long id, CIModule ciModule) {
        Optional<CIModule> optionalCIModule = ciModuleRepository.findById(id);

        if (optionalCIModule.isPresent()) {
            CIModule storedCIModule = optionalCIModule.get();
            ciModule.setId(storedCIModule.getId());
            ciModuleRepository.save(ciModule);
        } else {
            throw new RecordNotFoundException("ID does not exist!!!");
        }
    }

    public void partialUpdateCIModuleBook(Long id, CIModule ciModule) {
        Optional<CIModule> optionalCIModule = ciModuleRepository.findById(id);

        if (optionalCIModule.isPresent()) {
            CIModule storedCIModule = ciModuleRepository.findById(id).orElse(null);

            if (ciModule.getName() != null && !ciModule.getName().isEmpty()) {
                storedCIModule.setName(ciModule.getName());
            }
            if (ciModule.getPrice() != null && !ciModule.getPrice().isNaN()) {
                storedCIModule.setPrice(ciModule.getPrice());
            }
            ciModuleRepository.save(storedCIModule);

        } else {
            throw new RecordNotFoundException("ID does not exist!!!");
        }
    }

}
