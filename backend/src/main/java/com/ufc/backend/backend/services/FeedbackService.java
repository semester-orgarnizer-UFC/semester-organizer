package com.ufc.backend.backend.services;

import com.ufc.backend.backend.exceptions.ForbiddenException;
import com.ufc.backend.backend.exceptions.ObjectNotFoundException;
import com.ufc.backend.backend.model.Classes;
import com.ufc.backend.backend.model.feedback.Feedback;
import com.ufc.backend.backend.repositories.FeedbackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FeedbackService {

    @Autowired
    private FeedbackRepository repository;

    @Autowired
    private UserService userService;

    @Autowired
    private ClassesService classesService;

    public List<Feedback> findAll(){
        return repository.findAll();
    }
    public Feedback findById(String id) {
        return repository.findById(id).orElseThrow(() -> new ObjectNotFoundException(id));
    }

    public Feedback save(Feedback feedback) {
        feedback.setActualUser(userService.findById(AuthService.userAuthenticated().getId()));

        feedback.setUser(feedback.isAnonymous() ? null : feedback.getActualUser());

        repository.save(feedback);
        Classes classes = classesService.findById(feedback.getClasses().getId());
        classes.addFeedback(feedback);
        classesService.save(classes);
        feedback.setClasses(classes);
        return repository.save(feedback);
    }

    public void delete(String id) {
        Feedback feedback = findById(id);

        if(feedback.getUser() != userService.findById(AuthService.userAuthenticated().getId())) {
            throw new ForbiddenException();
        }

        feedback.getUser().deleteFeedback(feedback);
        repository.delete(feedback);
    }

    public Feedback update(Feedback feedback){
        feedback.setActualUser(userService.findById(AuthService.userAuthenticated().getId()));
        feedback.setUser(feedback.isAnonymous() ? null : feedback.getActualUser());

        repository.save(feedback);
        Classes classes = classesService.findById(feedback.getClasses().getId());
        classes.updateFeedback(feedback);
        classesService.save(classes);
        feedback.setClasses(classes);
        return repository.save(feedback);
    }
}
