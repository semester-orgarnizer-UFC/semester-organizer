package com.ufc.backend.backend.services;

import com.ufc.backend.backend.exceptions.ObjectNotFoundException;
import com.ufc.backend.backend.model.Classes;
import com.ufc.backend.backend.repositories.ClassRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ClassesService {
    @Autowired
    private ClassRepository repository;

    public Classes findById(String id){
        Optional<Classes> obj = repository.findById(id);
        if(obj.isEmpty()) throw new ObjectNotFoundException(id);
        return obj.get();
    }
    public Classes insert(Classes classes){
        return repository.insert(classes);
    }

    public List<Classes> findAll(){
        return repository.findAll();
    }

    public List<Classes> preRequisites(String id){
        return findById(id).getPreRequisites();
    }
}
