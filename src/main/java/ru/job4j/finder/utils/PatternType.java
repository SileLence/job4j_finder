package ru.job4j.finder.utils;

public enum PatternType {
    
    NAME,
    MASK,
    REGEX;
    
    public boolean isName() {
        return NAME.equals(this);
    }
    
    public boolean isMask() {
        return MASK.equals(this);
    }
    
    public boolean isRegex() {
        return REGEX.equals(this);
    }
}
