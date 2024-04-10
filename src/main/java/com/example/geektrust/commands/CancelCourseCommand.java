package com.example.geektrust.commands;

import com.example.geektrust.entities.Course;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import static com.example.geektrust.utils.Helper.CANCEL_ACCEPTED;
import static com.example.geektrust.utils.Helper.CANCEL_REJECTED;

public class CancelCourseCommand implements CommandExecutor{
    @Override
    public void executeCommand(TreeMap<String, Course> courses, Map<String, Course> registrationIdCourseMap, List<String> command) {
        String regId = command.get(1);
        if(isValidRegistrationID(regId,registrationIdCourseMap)){
            updateCancellation(regId,registrationIdCourseMap);
        }else{
            System.out.println(regId+" "+CANCEL_REJECTED);
        }
    }

    private void updateCancellation(String regId, Map<String, Course> registrationIdCourseMap) {

        if(isCourseNotAllotted(regId,registrationIdCourseMap)){
            System.out.println(regId+" "+CANCEL_REJECTED);
        }else{
            //Remove the registration for the employee...
            removeRegisteredEmployee(regId,registrationIdCourseMap);
            System.out.println(regId+" "+CANCEL_ACCEPTED);
        }}

    private void removeRegisteredEmployee(String regId, Map<String, Course> registrationIdCourseMap) {
        Course course = registrationIdCourseMap.get(regId);
        course.getEmployeesRegistered().remove(regId);
        registrationIdCourseMap.remove(regId);
    }

    private boolean isCourseNotAllotted(String regId, Map<String, Course> registrationIdCourseMap) {
        return registrationIdCourseMap.get(regId).isAllotted();
    }

    private boolean isValidRegistrationID(String regId, Map<String, Course> registrationIdCourseMap) {
        if(registrationIdCourseMap.get(regId)!=null){
            return true;
        }else{
            return false;
        }
    }
}
