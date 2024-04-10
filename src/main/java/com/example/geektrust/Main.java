package com.example.geektrust;

import com.example.geektrust.commands.CommandExecutor;
import com.example.geektrust.commands.CommandInvoker;
import com.example.geektrust.exceptions.IncorrectInputException;
import com.example.geektrust.services.DocumentProcessor;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

public class Main {
    public static void main(String[] args) {

       readFile(args);

    }

    private static void readFile(String[] args){
        try {
            if (args.length != 1) {
                throw new FileNotFoundException("Input file is not supplied");
            } else {
                CommandInvoker commandInvoker = new CommandInvoker();
                try {
                    DocumentProcessor documentProcessor = new DocumentProcessor(args[0]);
                    documentReader(documentProcessor,commandInvoker);
                } catch (FileNotFoundException ex) {
                    ex.printStackTrace();
                }
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private static void documentReader(DocumentProcessor documentProcessor, CommandInvoker commandInvoker){
        try {
            List<String> command = documentProcessor.readLine();
            while (command != null) {
                commandInvoker.fulfillCommand(command);
                command = documentProcessor.readLine();
            }
        } catch (IncorrectInputException | IOException e) {
            System.out.println(e.getMessage());
        }
    }

}
