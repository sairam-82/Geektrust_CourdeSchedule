package com.example.geektrust.commands;

import com.example.geektrust.entities.Course;
import com.example.geektrust.exceptions.CourseFullException;
import com.example.geektrust.exceptions.IncorrectInputException;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public interface CommandExecutor {
    void executeCommand(TreeMap<String, Course> courses, Map<String, Course> registrationIdCourseMap, List<String> command) throws IncorrectInputException, CourseFullException;
}
