package com.example.geektrust.commands;

import com.example.geektrust.entities.Course;
import com.example.geektrust.entities.Employee;
import com.example.geektrust.exceptions.IncorrectInputException;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class AllotCourseCommand implements CommandExecutor{
    @Override
    public void executeCommand(TreeMap<String, Course> courses, Map<String, Course> registrationIdCourseMap, List<String> command) throws IncorrectInputException {
        String courseId = command.get(1);
        Course course = courses.get(courseId);
        if(course!=null){
            updateCourse(course);
        }else{
            throw new IncorrectInputException("INPUT_DATA_ERROR");
        }
    }

    private void updateCourse(Course course) {
        if(course.getEmployeesRegistered().size()<course.getMinCapacity()){
            course.setCancelled(true);
            printCourseData(course);
        }else{
            course.setAllotted(true);
            printCourseData(course);
        }
    }

    private void printCourseData(Course course) {
        String status = course.isCancelled()?"COURSE_CANCELED":"CONFIRMED";
        for(Map.Entry<String, Employee> e: course.getEmployeesRegistered().entrySet()){
            String pattern = "ddMMyyyy";
            DateFormat df = new SimpleDateFormat(pattern);
            System.out.println(e.getKey()+" "+e.getValue().getEmailAddress()+" "+course.getCourseID()+" "+course.getCourseName()+" "+course.getInstructor()+" "
                    + df.format(course.getDate())+" "+status);
        }
    }
}
