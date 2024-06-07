package ru.job4j.finder.search;

import ru.job4j.finder.utils.Constants;
import ru.job4j.finder.utils.PatternType;
import ru.job4j.finder.validator.Validator;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.function.Predicate;

public class Search {
    
    private final Validator validator = new Validator();
    private SearchParameter searchParameter;
    
    public ArrayList<Path> execute(String[] args) throws IOException {
        validator.validateArgs(args);
        prepareParameters(args);
        Finder finder = new Finder(prepareCondition());
        Files.walkFileTree(Paths.get(searchParameter.getStartFolder()), finder);
        return finder.getFoundFiles();
    }
    
    private Predicate<Path> prepareCondition() {
        PatternType patternType = searchParameter.getPatternType();
        Predicate<Path> condition = null;
        if (patternType.isName()) {
            condition = path -> path.toFile().getName().toLowerCase()
                .contains(searchParameter.getSearchPattern().toLowerCase());
        } else if (patternType.isMask()) {
            String regexFromMask = getRegexFromMask(searchParameter.getSearchPattern());
            condition = getConditionFromRegex(regexFromMask);
        } else if (patternType.isRegex()) {
            condition = getConditionFromRegex(searchParameter.getSearchPattern());
        }
        return condition;
    }
    
    private void prepareParameters(String[] args) {
        SearchParameter searchParameter = new SearchParameter();
        for (String arg : args) {
            String[] param = arg.split("=");
            switch (param[0]) {
                case Constants.STARTING_FOLDER_KEY -> searchParameter.setStartFolder(param[1]);
                case Constants.SEARCH_PATTERN_KEY -> searchParameter.setSearchPattern(param[1]);
                case Constants.PATTERN_TYPE_KEY -> searchParameter.setPatternType(param[1]);
                case Constants.OUTPUT_FILE_KEY -> searchParameter.setOutputFileName(param[1]);
            }
        }
        this.searchParameter = searchParameter;
    }
    
    private String getRegexFromMask(String searchPattern) {
        String regexFromMask = searchPattern;
        if (searchPattern.contains(".")) {
            int indexOfDot = searchPattern.lastIndexOf(".");
            String beforeDot = searchPattern.substring(0, indexOfDot);
            String afterDot = searchPattern.substring(indexOfDot + 1);
            regexFromMask = beforeDot + "\\.{1}" + afterDot;
        }
        if (regexFromMask.contains("*")) {
            regexFromMask = regexFromMask.replace("*", ".*");
        }
        if (regexFromMask.contains("?")) {
            regexFromMask = regexFromMask.replace("?", ".{1}");
        }
        return regexFromMask;
    }
    
    private Predicate<Path> getConditionFromRegex(String regex) {
        validator.validateRegex(regex);
        return path -> path.toFile().getName().matches(regex);
    }
}
