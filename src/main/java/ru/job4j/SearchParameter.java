package ru.job4j;

public class SearchParameter {
    
    private String startFolder;
    private String fileName;
    private SearchType searchType;
    private String outputFileName;
    
    public String getStartFolder() {
        return startFolder;
    }
    
    public void setStartFolder(String startFolder) {
        this.startFolder = startFolder;
    }
    
    public String getFileName() {
        return fileName;
    }
    
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
    
    public SearchType getSearchType() {
        return searchType;
    }
    
    public void setSearchType(String searchType) {
        switch (searchType.toLowerCase()) {
            case "name" -> this.searchType = SearchType.NAME;
            case "mask" -> this.searchType = SearchType.MASK;
            case "regex" -> this.searchType = SearchType.REGEX;
            default -> throw new IllegalArgumentException(
                ErrorMessage.ERR_2.getFilledMessage(searchType, Constants.SEARCH_TYPE_KEY)
            );
        }
    }
    
    public String getOutputFileName() {
        return outputFileName;
    }
    
    public void setOutputFileName(String outputFileName) {
        this.outputFileName = outputFileName;
    }
}
