package com.ekids.ticktacktoe.handler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ConsoleHandler {

    private final BufferedReader reader;

    public ConsoleHandler() {
        reader = new BufferedReader(new InputStreamReader(System.in));
    }

    public void writeMessage(String message) {
        System.out.println(message);
    }

    public String readMessage() {
        String message = "";

        try {
            message = reader.readLine();
        } catch (IOException e) {
            System.out.println("error was occur when application read data from console");
        }

        return message;
    }

    public void close() {
        try {
            reader.close();
        } catch (IOException e) {
            System.out.println("error was occur when application close input stream");
        }
    }
}
