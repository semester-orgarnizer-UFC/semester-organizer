package com.ufc.backend.backend.services.utils;

import com.ufc.backend.backend.exceptions.ClassCantBeDoneAtTheFirstSemester;
import com.ufc.backend.backend.exceptions.ClassDontHaveThePreRequisiteException;
import com.ufc.backend.backend.exceptions.ClassesAndPreRequisiteAtTheSameTimeException;
import com.ufc.backend.backend.model.Semester;
import com.ufc.backend.backend.model.Classes;
import com.ufc.backend.backend.model.User;
import com.ufc.backend.backend.services.ClassesService;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Class responsible to throw possible exceptions when you try to create a {@link Semester} with invalid {@link Classes}
 */
public class HandlePossibleClassesException {


    private final ClassesService classesService;
    private final List<String> idsAlreadyDone;
    private final Semester semester;
    private final User user;

    public HandlePossibleClassesException(ClassesService classesService, List<String> idsAlreadyDone, Semester semester, User user) {
        this.classesService = classesService;
        this.idsAlreadyDone = idsAlreadyDone;
        this.semester = semester;
        this.user = user;
    }

    public void run() {
        semester.getClasses().forEach(classes -> {
            this.classesCantBeDoneAtTheFirstSemester(classes.getId(), semester);
            this.classesDontHaveThePreRequisite(classes.getId());
        });
        classesAndItsPreRequisiteCantBeDoneAtTheSameTime(semester);
    }

    private void classesDontHaveThePreRequisite(String classesId) {
        Classes classes = this.classesService.findById(classesId);
        if (this.idsAlreadyDone == null && classes.getPreRequisite() != null)
            throw new ClassDontHaveThePreRequisiteException(classes);

        if (this.idsAlreadyDone != null && classes.getPreRequisite() != null && !this.idsAlreadyDone.contains(classes.getPreRequisite().getId()))
            throw new ClassDontHaveThePreRequisiteException(classes);
    }

    private void classesCantBeDoneAtTheFirstSemester(String classesId, Semester semester) {
        Classes classes = this.classesService.findById(classesId);

        if (semester.getIndex() == 1 && classes.getSemester() != 1)
            throw new ClassCantBeDoneAtTheFirstSemester(classes);
    }

    private void classesAndItsPreRequisiteCantBeDoneAtTheSameTime(Semester semester) {
        Semester currentSemester = user.getSemester().get(semester.getIndex() - 1);

        semester.getClasses().forEach(classes -> {
            if (semester.getClasses().contains(classes.getPreRequisite())) {
                throw new ClassesAndPreRequisiteAtTheSameTimeException(classes);
            }
            if (currentSemester.getClasses() != null && currentSemester.getClasses().contains(classes.getPreRequisite()))
                throw new ClassesAndPreRequisiteAtTheSameTimeException(classes);
        });
    }
}
