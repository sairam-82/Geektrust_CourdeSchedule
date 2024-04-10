package com.example.geektrust.services;

import com.example.geektrust.utils.CommandLengths;
import com.example.geektrust.exceptions.IncorrectInputException;
import com.example.geektrust.utils.Helper;

import java.io.*;
import java.util.List;
import java.util.*;
import java.util.stream.Collectors;

public class DocumentProcessor {
    private final File file;
    private final BufferedReader reader;

    public DocumentProcessor(String filePath) throws FileNotFoundException {
        file = new File(filePath);
        reader = new BufferedReader(new FileReader(file));
    }

    public List<String> readLine() throws IncorrectInputException, IOException {
        String inputString = reader.readLine();
        if(inputString == null){
            return null;
        }
        if(isEmpty(inputString)){
            readLine();
        }
        try{
            return getCommandFromString(inputString);
        }catch(IncorrectInputException e){
            System.out.println(e.getMessage());
            return readLine();
        }
    }

    private List<String> getCommandFromString(String input) {
        try {
            String[] commandWithArguments = input.split(" ");
            List<String> commandParams =
                    Arrays.stream(commandWithArguments).collect(Collectors.toList());

            this.validateInputCommand(commandParams);
            return commandParams;
        } catch (Exception e) {
            throw new IncorrectInputException("INPUT_DATA_ERROR");
        }
    }

    private void validateInputCommand(List<String> commandParams) throws NoSuchFieldException, IllegalAccessException {
        String commandName= Helper.getCommand(commandParams.get(0));
        int requiredLengthOfCommand=(int)CommandLengths.class.getField(commandName).get(null);
        if(requiredLengthOfCommand != commandParams.size()){
            throw new IncorrectInputException("INPUT_DATA_ERROR");
        }
    }

    private boolean isEmpty(String line) {
        return line==null || line.length()==0 || line.trim().isEmpty() || line.trim().startsWith("#");
    }
}
