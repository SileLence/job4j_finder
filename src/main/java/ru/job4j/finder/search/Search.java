package ru.job4j.finder.search;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.job4j.finder.utils.Constants;
import ru.job4j.finder.utils.Message;
import ru.job4j.finder.utils.PatternType;
import ru.job4j.finder.validator.Validator;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

public class Search {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(Search.class);
    private final Validator validator = new Validator();
    private SearchParameter searchParameters;
    
    public void execute(String[] args) throws IOException {
        LOGGER.info("Start search with args: {}", Arrays.toString(args));
        validator.validateArgs(args);
        prepareParameters(args);
        Finder finder = new Finder(prepareCondition());
        Files.walkFileTree(Paths.get(searchParameters.getStartFolder()), finder);
        ArrayList<Path> foundFiles = finder.getFoundFiles();
        writeResult(foundFiles);
    }
    
    private Predicate<Path> prepareCondition() {
        PatternType patternType = searchParameters.getPatternType();
        Predicate<Path> condition = null;
        if (patternType.isName()) {
            condition = path -> path.toFile().getName().toLowerCase()
                .contains(searchParameters.getSearchPattern().toLowerCase());
        } else if (patternType.isMask()) {
            String regexFromMask = getRegexFromMask(searchParameters.getSearchPattern());
            condition = getConditionFromRegex(regexFromMask);
        } else if (patternType.isRegex()) {
            condition = getConditionFromRegex(searchParameters.getSearchPattern());
        }
        return condition;
    }
    
    private void prepareParameters(String[] args) {
        SearchParameter searchParameter = new SearchParameter();
        for (String arg : args) {
            String[] param = arg.split(Constants.EQUAL_SYMBOL, Constants.TWO);
            switch (param[Constants.ZERO]) {
                case Constants.START_FOLDER_KEY -> searchParameter.setStartFolder(param[Constants.ONE]);
                case Constants.SEARCH_PATTERN_KEY -> searchParameter.setSearchPattern(param[Constants.ONE]);
                case Constants.PATTERN_TYPE_KEY -> searchParameter.setPatternType(param[Constants.ONE]);
                case Constants.OUTPUT_FILE_KEY -> searchParameter.setOutputFileName(param[Constants.ONE]);
            }
        }
        LOGGER.debug("Prepared parameters: {}", searchParameter);
        this.searchParameters = searchParameter;
    }
    
    private String getRegexFromMask(String searchPattern) {
        String regexFromMask = searchPattern;
        if (searchPattern.contains(".")) {
            int indexOfDot = searchPattern.lastIndexOf(".");
            String beforeDot = searchPattern.substring(Constants.ZERO, indexOfDot);
            String afterDot = searchPattern.substring(indexOfDot + Constants.ONE);
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
    
    private void writeResult(List<Path> foundFiles) throws IOException {
        LOGGER.trace("Writing found files: {}", foundFiles);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(searchParameters.getOutputFileName()))) {
            if (foundFiles.isEmpty()) {
                writer.write(Message.MSG_3.getFilledMessage());
            } else {
                writer.write(foundFiles.size() == Constants.ONE
                    ? Message.MSG_4.getFilledMessage(foundFiles.size(), "")
                    : Message.MSG_4.getFilledMessage(foundFiles.size(), "s")
                );
            }
            for (Path file : foundFiles) {
                writer.write(file.toFile().getPath() + System.lineSeparator());
            }
        } catch (IOException exception) {
            LOGGER.error(Message.MSG_5.getFilledMessage(exception.getMessage()));
            throw new IOException(Message.MSG_5.getFilledMessage(exception.getMessage()), exception);
        }
    }
}
