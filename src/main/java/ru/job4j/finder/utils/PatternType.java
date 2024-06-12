package ru.job4j.finder.utils;

public enum PatternType {
    
    NAME("name"),
    MASK("mask"),
    REGEX("regex");
    
    final String value;
    
    PatternType(String value) {
        this.value = value;
    }
    
    public String getValue() {
        return value;
    }
    
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
