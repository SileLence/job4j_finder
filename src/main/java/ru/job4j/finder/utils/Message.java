package ru.job4j.finder.utils;

public enum Message {
    
    MSG_1("Input parameters are empty."),
    MSG_2("Unsupported value [%s] of [-t] key. Possible values: 'name', 'mask', 'regex'."),
    MSG_3("Files not found."),
    MSG_4("Found %d file%s:\n"),
    MSG_5("Error during writing to Output file: %s"),
    MSG_6("Parameter [%s] has incorrect structure. It should be 'key=value'."),
    MSG_7("Parameter [%s] has empty key."),
    MSG_8("Parameter [%s] has empty value."),
    MSG_9("Unsupported key [%s] of [%s] value. Possible values: '-d', '-n', '-t', '-o'."),
    MSG_10("Key [-d] has contain incorrect directory path: [%s]."),
    MSG_11("Output file of [-o] key is not exists: [%s]."),
    MSG_12("Input regex is not valid: %s"),
    MSG_13("Input parameters should contain only four mandatory keys: '-d', '-n', '-t', '-o'.");
    
    private final String message;
    
    Message(String message) {
        this.message = message;
    }
    
    public String getFilledMessage(Object... params) {
        return String.format(this.message, params);
    }
}
