package com.example.geektrust.commands;

import com.example.geektrust.entities.Course;
import com.example.geektrust.exceptions.IncorrectInputException;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class AddCourseCommand implements CommandExecutor{
    @Override
    public void executeCommand(TreeMap<String, Course> courses, Map<String, Course> registrationIdCourseMap, List<String> command) throws IncorrectInputException {
        List<String> params = command.stream().skip(1).collect(Collectors.toList());
        Course course = constructCourseObject(params);
        offerCourse(courses , course);
    }

    private Course constructCourseObject(List<String> params) throws IncorrectInputException {
        Course course = null;
        try{
            String courseName = params.get(0);
            String courseInstructor = params.get(1);
            SimpleDateFormat inputFormat = new SimpleDateFormat("ddMMyyyy");
            Date d = inputFormat.parse(params.get(2));
            int minCount = Integer.parseInt(params.get(3));
            int maxCount = Integer.parseInt(params.get(4));
            course = new Course("OFFERING-"+courseName+"-"+courseInstructor,courseName , courseInstructor , d ,minCount , maxCount , false, false);
        }catch (Exception e){
            throw new IncorrectInputException("INPUT_ERROR");
        }
        return course;
    }

    private void offerCourse(TreeMap<String, Course> courses, Course course){
        courses.put(course.getCourseID(),course);
        System.out.println(course.getCourseID());
    }
}
