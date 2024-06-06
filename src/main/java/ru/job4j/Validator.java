package ru.job4j;

public class Validator {
    
    public static void validateArgs(String[] args) {
        if (args.length == 0) {
            throw new IllegalArgumentException(ErrorMessage.ERR_1.getFilledMessage());
        }
    }
}
