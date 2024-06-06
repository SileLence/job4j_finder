package ru.job4j;

public enum ErrorMessage {
    
    ERR_1("Input parameters should be provided."),
    ERR_2("Unsupported value [\s] of '\s' key. Possible values: 'name', 'mask', 'regex'.");
    
    private final String message;
    
    ErrorMessage(String message) {
        this.message = message;
    }
    
    String getFilledMessage(Object... params) {
        return String.format(this.message, params);
    }
}
