package com.ekids.ticktacktoe.validator;

public class InputDataValidator {
    private static final String NUMBER_REGEX = "[1-3]";

    public boolean validateIndexInput(String input){
        return input.matches(NUMBER_REGEX);
    }
}
