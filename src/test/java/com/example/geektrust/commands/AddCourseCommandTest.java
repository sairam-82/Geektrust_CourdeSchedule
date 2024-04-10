package com.example.geektrust.commands;

import com.example.geektrust.app_config.ExecutionFactory;
import com.example.geektrust.entities.Course;
import com.example.geektrust.exceptions.CourseFullException;
import com.example.geektrust.exceptions.IncorrectInputException;
import edu.emory.mathcs.backport.java.util.Arrays;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class AddCourseCommandTest {

    List<String> command1;
    CommandExecutor executor;
    private TreeMap<String , Course> courses;
    private Map<String,Course> registrationIdCourseMap;
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    @BeforeEach
    public void setUp() throws IncorrectInputException {
        System.setOut(new PrintStream(outContent));
        command1 = Arrays.asList("ADD-COURSE-OFFERING JAVA JAMES 15062022 1 2".split(" "));
        executor = ExecutionFactory.getExecutor(command1);
        courses = new TreeMap<>();
        registrationIdCourseMap = new HashMap<>();
    }

    @Test
    public void testExecute(){
        assertDoesNotThrow( ()->executor.executeCommand(courses , registrationIdCourseMap , command1));
    }


    @Test
    public void testSuccessMessage() throws IncorrectInputException, CourseFullException {
        executor.executeCommand(courses , registrationIdCourseMap , command1);
        assertEquals("OFFERING-JAVA-JAMES",outContent.toString().trim());
    }
}
