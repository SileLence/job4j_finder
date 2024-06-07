package ru.job4j.finder.utils;

public enum ErrorMessage {
    
    ERR_1("Input parameters should be provided."),
    ERR_2("Unsupported value [%s] of [-t] key. Possible values: 'name', 'mask', 'regex'."),
    ERR_3("Files not found.");
    
    private final String message;
    
    ErrorMessage(String message) {
        this.message = message;
    }
    
    public String getFilledMessage(Object... params) {
        return String.format(this.message, params);
    }
}
