package ru.job4j.finder.validator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.job4j.finder.utils.Constants;
import ru.job4j.finder.utils.Message;
import ru.job4j.finder.utils.PatternType;

import java.io.File;
import java.util.Set;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

public class Validator {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(Validator.class);
    private static final Set<String> KEYS = Set.of(
        Constants.START_FOLDER_KEY,
        Constants.SEARCH_PATTERN_KEY,
        Constants.PATTERN_TYPE_KEY,
        Constants.OUTPUT_FILE_KEY
    );
    private static final Set<String> PATTERN_TYPES = Set.of(
        PatternType.NAME.getValue(),
        PatternType.MASK.getValue(),
        PatternType.REGEX.getValue()
    );
    
    public void validateArgs(String[] args) {
        if (args.length == Constants.ZERO) {
            throwException(Message.MSG_1.getFilledMessage());
        }
        if (args.length != Constants.FOUR) {
            throwException(Message.MSG_13.getFilledMessage());
        }
        for (String parameter : args) {
            if (!parameter.contains(Constants.EQUAL_SYMBOL)) {
                throwException(Message.MSG_6.getFilledMessage(parameter));
            }
            String[] keyValue = parameter.split(Constants.EQUAL_SYMBOL, Constants.TWO);
            String key = keyValue[Constants.ZERO];
            String value = keyValue[Constants.ONE];
            
            if (Constants.EMPTY_STRING.equals(key)) {
                throwException(Message.MSG_7.getFilledMessage(parameter));
            }
            if (Constants.EMPTY_STRING.equals(value)) {
                throwException(Message.MSG_8.getFilledMessage(parameter));
            }
            if (!KEYS.contains(key)) {
                throwException(Message.MSG_9.getFilledMessage(key, value));
            }
            if (Constants.START_FOLDER_KEY.equals(key)) {
                File dir = new File(value);
                if (!dir.isDirectory()) {
                    throwException(Message.MSG_10.getFilledMessage(value));
                }
            }
            if (Constants.PATTERN_TYPE_KEY.equals(key)
                && !PATTERN_TYPES.contains(value.toLowerCase())) {
                    throwException(Message.MSG_2.getFilledMessage(value));
            }
            if (Constants.OUTPUT_FILE_KEY.equals(key)) {
                File output = new File(value);
                if (!output.exists()) {
                    throwException(Message.MSG_11.getFilledMessage(value));
                }
            }
        }
    }
    
    public void validateRegex(String regex) {
        try {
            Pattern.compile(regex);
        } catch (PatternSyntaxException exception) {
            LOGGER.error(Message.MSG_12.getFilledMessage(regex));
            throw new PatternSyntaxException(Message.MSG_12
                .getFilledMessage(Constants.EMPTY_STRING), regex, Constants.MINUS_ONE);
        }
    }
    
    private void throwException(String errorMessage) {
        LOGGER.error(errorMessage);
        throw new IllegalArgumentException(errorMessage);
    }
}
