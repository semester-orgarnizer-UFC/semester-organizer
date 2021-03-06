package com.ufc.backend.backend.services.utils;

import com.ufc.backend.backend.exceptions.ClassCantBeDoneAtTheFirstSemester;
import com.ufc.backend.backend.exceptions.ClassDontHaveThePreRequisiteException;
import com.ufc.backend.backend.exceptions.ClassesAndPreRequisiteAtTheSameTimeException;
import com.ufc.backend.backend.model.Semester;
import com.ufc.backend.backend.model.Classes;
import com.ufc.backend.backend.model.User;
import com.ufc.backend.backend.services.ClassesService;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Class responsible to throw possible exceptions when you try to create a {@link Semester} with invalid {@link Classes}
 */
@Service
public class HandlePossibleClassesException {


    private final ClassesService classesService;
    private final List<String> idsAlreadyDone;

    public HandlePossibleClassesException(List<String> idsAlreadyDone, ClassesService classesService) {
        this.idsAlreadyDone = idsAlreadyDone;
        this.classesService = classesService;
    }

    public void classesDontHaveThePreRequisite(String classesId) {
        Classes classes = this.classesService.findById(classesId);
        if (this.idsAlreadyDone == null && classes.getPreRequisite() != null)
            throw new ClassDontHaveThePreRequisiteException(classes);

        if (this.idsAlreadyDone != null && classes.getPreRequisite() != null && !this.idsAlreadyDone.contains(classes.getPreRequisite().getId()))
            throw new ClassDontHaveThePreRequisiteException(classes);
    }

    public void classesCantBeDoneAtTheFirstSemester(String classesId, Semester semester) {
        Classes classes = this.classesService.findById(classesId);

        if (semester.getIndex() == 1 && classes.getSemester() != 1)
            throw new ClassCantBeDoneAtTheFirstSemester(classes);
    }

    public void classAndPreRequisiteAtTheSameTime(Semester semester, User user) {

        if (user.getSemester() != null) {
            List<String> listClasses = semester.getClasses().stream().map(Classes::getId).collect(Collectors.toList());
            Semester updatedSemester = user.getSemester().stream().filter(obj -> obj.getIndex().equals(semester.getIndex())).findFirst().orElse(null);

            if (updatedSemester != null) {
                List<String> listClassesUpdatedSemester = updatedSemester.getClasses().stream().map(Classes::getId).collect(Collectors.toList());
                listClasses.forEach(obj -> {
                    Classes classes = classesService.findById(obj);
                    if (classes.getPreRequisite() != null && listClassesUpdatedSemester.contains(classes.getPreRequisite().getId())) {
                        throw new ClassesAndPreRequisiteAtTheSameTimeException(classes);
                    }
                });
            }
        }
    }
}
