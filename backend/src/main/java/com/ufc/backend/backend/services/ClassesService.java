package com.ufc.backend.backend.services;

import com.ufc.backend.backend.exceptions.ObjectNotFoundException;
import com.ufc.backend.backend.model.Classes;
import com.ufc.backend.backend.repositories.ClassRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClassesService {
    @Autowired
    private ClassRepository repository;

    public Classes findById(String id) {
        return repository.findById(id).orElseThrow(() -> new ObjectNotFoundException(id));
    }

    public List<Classes> findAll() {
        return repository.findAll();
    }

    public List<Classes> findAllClassesThatHasTheGivenPreRequisite(String preRequisiteId) {
        return findAll().stream().filter(obj -> obj.getPreRequisite() != null && obj.getPreRequisite().getId().equals(preRequisiteId)).collect(Collectors.toList());
    }
}