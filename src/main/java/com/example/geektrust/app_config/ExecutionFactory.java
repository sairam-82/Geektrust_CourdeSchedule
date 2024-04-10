package com.example.geektrust.app_config;

import com.example.geektrust.commands.*;
import com.example.geektrust.utils.Helper;

import java.util.List;

public class ExecutionFactory {
    public static CommandExecutor getExecutor(List<String> task) {
        CommandExecutor commandExecutor = null;
        if(task!=null){
            switch(Helper.getCommand(task.get(0))){
                case "ADD_COURSE_OFFERING":
                    commandExecutor = new AddCourseCommand();
                    break;
                case "CANCEL":
                    commandExecutor = new CancelCourseCommand();
                    break;
                case "ALLOT":
                    commandExecutor = new AllotCourseCommand();
                    break;
                case "REGISTER":
                    commandExecutor = new RegisterCourseCommand();
                    break;
                default:
                    break;
            }
        }
        return commandExecutor;
    }
}
