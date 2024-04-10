package com.example.geektrust.commands;

import com.example.geektrust.app_config.ExecutionFactory;
import com.example.geektrust.entities.Course;
import com.example.geektrust.exceptions.IncorrectInputException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.*;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class RegisterCourseCommandTest {
    List<String> command1;
    List<String> command2;
    CommandExecutor executor1;
    CommandExecutor executor2;
    private TreeMap<String , Course> courses;
    private Map<String,Course> registrationIdCourseMap;
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    @BeforeEach
    public void setUp() throws IncorrectInputException {
        System.setOut(new PrintStream(outContent));
        command1 = Arrays.asList("ADD-COURSE-OFFERING JAVA JAMES 15062022 1 2".split(" "));
        command2 = Arrays.asList("REGISTER ANDY@GMAIL.COM OFFERING-JAVA-JAMES".split(" "));
        executor1 = ExecutionFactory.getExecutor(command1);
        courses = new TreeMap<>();
        registrationIdCourseMap = new HashMap<>();
        executor2 = ExecutionFactory.getExecutor(command2);
    }

    @Test
    public void testExecute() {
        assertDoesNotThrow(()->executor2.executeCommand(courses , registrationIdCourseMap , command2));
    }



}
