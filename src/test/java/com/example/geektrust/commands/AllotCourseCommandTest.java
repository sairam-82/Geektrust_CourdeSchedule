package com.example.geektrust.commands;

import com.example.geektrust.app_config.ExecutionFactory;
import com.example.geektrust.entities.Course;
import com.example.geektrust.exceptions.IncorrectInputException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.*;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class AllotCourseCommandTest {


    List<String> command1;
    CommandExecutor executor;
    private TreeMap<String , Course> courses;
    private Map<String,Course> registrationIdCourseMap;
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();


    @BeforeEach
    public void setUp() throws IncorrectInputException {
        System.setOut(new PrintStream(outContent));
        String command= "ALLOT OFFERING-JAVA-JAMES";
        command1 = Arrays.asList(command.split(" "));
        executor = ExecutionFactory.getExecutor(command1);
        courses = new TreeMap<>();
        registrationIdCourseMap = new HashMap<>();
    }


    @Test
    public void testExecute() {
        assertThrows(IncorrectInputException.class , ()->executor.executeCommand(courses , registrationIdCourseMap , command1));
    }

}
