package com.example.geektrust.commands;

import com.example.geektrust.app_config.ExecutionFactory;
import com.example.geektrust.entities.Course;
import com.example.geektrust.exceptions.CourseFullException;
import com.example.geektrust.exceptions.IncorrectInputException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.*;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class CancelCourseCommandTest {

    List<String> command1;
    List<String> command2;
    List<String> command3;
    CommandExecutor executor1;
    CommandExecutor executor2;
    CommandExecutor executor3;

    private TreeMap<String , Course> courses;
    private Map<String,Course> registrationIdCourseMap;
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    @BeforeEach
    public void setUp() throws IncorrectInputException, CourseFullException {
        System.setOut(new PrintStream(outContent));
        command1 = Arrays.asList( "ADD-COURSE-OFFERING JAVA JAMES 15062022 1 2".split(" "));
        command2 = Arrays.asList("REGISTER ANDY@GMAIL.COM OFFERING-JAVA-JAMES".split(" "));
        command3 = Arrays.asList("CANCEL REG-COURSE-ANDY-JAVA".split(" "));
        executor1 = ExecutionFactory.getExecutor(command1);
        executor2 = ExecutionFactory.getExecutor(command2);
        executor3 = ExecutionFactory.getExecutor(command3);
        courses = new TreeMap<>();
        registrationIdCourseMap = new HashMap<>();
        executor1.executeCommand(courses , registrationIdCourseMap , command1);
        executor2.executeCommand(courses,registrationIdCourseMap , command2);

    }

    @Test
    public void testExecute() {
        assertDoesNotThrow(()->executor3.executeCommand(courses , registrationIdCourseMap , command3));
    }


}
