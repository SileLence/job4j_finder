package ru.job4j.finder.validator;

import ru.job4j.finder.utils.ErrorMessage;

import java.util.regex.Pattern;

public class Validator {
    
    public void validateArgs(String[] args) {
        if (args.length == 0) {
            throw new IllegalArgumentException(ErrorMessage.ERR_1.getFilledMessage());
        }
    }
    
    public void validateRegex(String regex) {
        Pattern.compile(regex);
    }
}
