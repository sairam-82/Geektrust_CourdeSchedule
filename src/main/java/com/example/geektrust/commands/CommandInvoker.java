package com.example.geektrust.commands;

import com.example.geektrust.app_config.ExecutionFactory;
import com.example.geektrust.entities.Course;
import com.example.geektrust.exceptions.CourseFullException;
import com.example.geektrust.exceptions.IncorrectInputException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class CommandInvoker {
    private final TreeMap<String , Course> courses;
    private final Map<String,Course> registrationIdCourseMap;

    public CommandInvoker() {
        courses = new TreeMap<>();
        registrationIdCourseMap = new HashMap<>();
    }




    public void fulfillCommand(List<String> inputCommand) throws IncorrectInputException {
        CommandExecutor commandExecutor = ExecutionFactory.getExecutor(inputCommand);
        try{
            commandExecutor.executeCommand(courses,registrationIdCourseMap, inputCommand);
        }catch(CourseFullException e ){
            System.out.println(e.getMessage());
        }
    }
}
