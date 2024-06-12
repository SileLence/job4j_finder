package ru.job4j.finder.search;

import ru.job4j.finder.utils.PatternType;

public class SearchParameter {
    
    private String startFolder;
    private String searchPattern;
    private PatternType patternType;
    private String outputFileName;
    
    public String getStartFolder() {
        return startFolder;
    }
    
    public void setStartFolder(String startFolder) {
        this.startFolder = startFolder;
    }
    
    public String getSearchPattern() {
        return searchPattern;
    }
    
    public void setSearchPattern(String searchPattern) {
        this.searchPattern = searchPattern;
    }
    
    public PatternType getPatternType() {
        return patternType;
    }
    
    public void setPatternType(String patternType) {
        switch (patternType.toLowerCase()) {
            case "name" -> this.patternType = PatternType.NAME;
            case "mask" -> this.patternType = PatternType.MASK;
            case "regex" -> this.patternType = PatternType.REGEX;
        }
    }
    
    public String getOutputFileName() {
        return outputFileName;
    }
    
    public void setOutputFileName(String outputFileName) {
        this.outputFileName = outputFileName;
    }
    
    @Override
    public String toString() {
        return "SearchParameter{"
            + "startFolder='" + startFolder + '\''
            + ", searchPattern='" + searchPattern + '\''
            + ", patternType=" + patternType
            + ", outputFileName='" + outputFileName + '\''
            + '}';
    }
}
