package ru.job4j.finder.validator;

import ru.job4j.finder.utils.Constants;
import ru.job4j.finder.utils.Message;

import java.util.Set;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

public class Validator {
    
    private static final Set<String> KEYS = Set.of(
        Constants.STARTING_FOLDER_KEY,
        Constants.SEARCH_PATTERN_KEY,
        Constants.PATTERN_TYPE_KEY,
        Constants.OUTPUT_FILE_KEY
    );
    
    public void validateArgs(String[] args) {
        if (args.length == Constants.ZERO) {
            throw new IllegalArgumentException(Message.MSG_1.getFilledMessage());
        }
        for (String parameter : args) {
            if (!parameter.contains(Constants.EQUAL_SYMBOL)) {
                throw new IllegalArgumentException(Message.MSG_6.getFilledMessage(parameter));
            }
            
            String[] keyValue = parameter.split(Constants.EQUAL_SYMBOL, Constants.TWO);
            
            if ("".equals(keyValue[Constants.ZERO])) {
                throw new IllegalArgumentException(Message.MSG_7.getFilledMessage(parameter));
            }
            if ("".equals(keyValue[Constants.ONE])) {
                throw new IllegalArgumentException(Message.MSG_8.getFilledMessage(parameter));
            }
            if (!KEYS.contains(keyValue[Constants.ZERO])) {
                throw new IllegalArgumentException(Message.MSG_9.getFilledMessage(keyValue[Constants.ZERO], keyValue[Constants.ONE]));
            }
        }
    }
    
    public void validateRegex(String regex) {
        try {
            Pattern.compile(regex);
        } catch (PatternSyntaxException exception) {
            throw new PatternSyntaxException(Message.MSG_15.getFilledMessage(), regex, Constants.MINUS_ONE);
        }
    }
}
