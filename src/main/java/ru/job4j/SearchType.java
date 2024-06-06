package ru.job4j;

public enum SearchType {
    
    NAME,
    MASK,
    REGEX;
    
    boolean isName() {
        return NAME.equals(this);
    }
    
    boolean isMask() {
        return MASK.equals(this);
    }
    
    boolean isRegex() {
        return REGEX.equals(this);
    }
}
