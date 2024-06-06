package ru.job4j;

import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.function.Predicate;

public class Search {
    
    private SearchParameter searchParameter;
    
    public ArrayList<Path> execute(String[] args) throws IOException {
        Validator.validateArgs(args);
        prepareParameters(args);
        Finder finder = new Finder(prepareCondition());
        return null;
    }
    
    private Predicate<Path> prepareCondition() {
        SearchType searchType = searchParameter.getSearchType();
        Predicate<Path> condition = null;
        if (searchType.isName()) {
            condition = path -> path.toFile().getName().contains(searchParameter.getFileName());
        }
        return null;
    }
    
    private void prepareParameters(String[] args) {
        SearchParameter searchParameter = new SearchParameter();
        for (String arg : args) {
            String[] param = arg.split("=");
            switch (param[0]) {
                case Constants.STARTING_FOLDER_KEY -> searchParameter.setStartFolder(param[1]);
                case Constants.FILE_NAME_KEY -> searchParameter.setFileName(param[1]);
                case Constants.SEARCH_TYPE_KEY -> searchParameter.setSearchType(param[1]);
                case Constants.OUTPUT_FILE_KEY -> searchParameter.setOutputFileName(param[1]);
            }
        }
        this.searchParameter = searchParameter;
    }
}
