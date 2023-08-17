package ru.hogwarts.school.service;

import ru.hogwarts.school.exceptions.StudentNotFoundException;
import ru.hogwarts.school.model.Faculty;

import java.util.*;
import org.springframework.stereotype.Service;
import ru.hogwarts.school.exceptions.FacultyNotFoundException;
import java.util.Collection;
import java.util.stream.Collectors;


@Service
public class FacultyServiceImpl implements FacultyService {
    Map<Long, Faculty> faculties = new HashMap<>();


    @Override
    public Faculty addFaculty(Faculty faculty) {
            faculties.put(faculty.getId(), faculty);
            return faculty;
    }

    @Override
    public Faculty getFacultyById(Long id) {
        if (!faculties.containsKey(id)) {
            throw new StudentNotFoundException("Faculty not found");
        }
        return faculties.get(id);
    }

    @Override
    public Collection<Faculty> getAllFaculties() {
        return Collections.unmodifiableCollection(faculties.values());
    }

    @Override
    public Faculty updateFaculty(Long id, Faculty faculty) {
        if (!faculties.containsKey(id)) {
            throw new FacultyNotFoundException("Faculty not found");
        }
        Faculty facultyUpdate = faculties.get(id);
        facultyUpdate.setName(faculty.getName());
        facultyUpdate.setColor(faculty.getColor());
        faculties.put(id, faculty);
        return facultyUpdate;
    }

    @Override
    public void deleteFaculty(Long id) {
        if (!faculties.containsKey(id)) {
            throw new FacultyNotFoundException("Faculty not found");
        }
        faculties.remove(id);
    }

    @Override
    public Collection<Faculty> getFacultiesByColor(String color) {
        return getAllFaculties().stream()
                .filter(student -> student.getColor() == color)
                .collect(Collectors.toList());
    }
}