package com.example.geektrust.commands;

import com.example.geektrust.entities.Course;
import com.example.geektrust.entities.Employee;
import com.example.geektrust.exceptions.CourseFullException;
import com.example.geektrust.exceptions.IncorrectInputException;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import static com.example.geektrust.utils.Helper.ACCEPTED;
import static com.example.geektrust.utils.Helper.REJECTED;

public class RegisterCourseCommand implements CommandExecutor {
    @Override
    public void executeCommand(TreeMap<String, Course> courses, Map<String, Course> registrationIdCourseMap, List<String> command) throws IncorrectInputException, CourseFullException {
        String courseID = command.get(2);
        Employee employee = constructEmployeeObject(command);
        if(courses.containsKey(courseID)){
            Course course = courses.get(courseID);
            if(!courses.get(courseID).isAllotted() || courses.get(courseID).isCancelled()){
                if(course.getEmployeesRegistered().size()==course.getMaxCapacity()){
                    throw new CourseFullException("COURSE_FULL_ERROR");
                }else{
                    //register the employee to course......
                    registerEmployeeToCourse(employee , course ,registrationIdCourseMap);
                }
            }else{
                //In case of course is allotted already
                System.out.println("REG-COURSE-"+employee.getName()+"-"+courses.get(courseID).getCourseName()+" "+REJECTED);
            }
        }else{
            //In case of course ID is not present in the course offering list.
            System.out.println("INPUT_DATA_ERROR");
        }

    }

    private void registerEmployeeToCourse(Employee employee, Course course, Map<String, Course> registrationIdCourseMap) {
        String regID = course.addEmployee(employee);
        registrationIdCourseMap.put(regID , course);
        System.out.println(regID+" "+ACCEPTED);
    }

    private Employee constructEmployeeObject(List<String> command) throws IncorrectInputException {
        return new Employee(command.get(1));
    }
}
