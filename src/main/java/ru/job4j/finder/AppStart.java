package ru.job4j.finder;

import ru.job4j.finder.search.Search;
import ru.job4j.finder.utils.ErrorMessage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;

/**
 * Console application for searching files by name, mask or regex
 * @author Denis Trunov
 * @Date: 04.06.2024
 */
public class AppStart {
    
    public static void main(String[] args) throws IOException {
        Search search = new Search();
        ArrayList<Path> foundFiles = search.execute(args);
        // todo: write result to file
        System.out.println("Found files: " + foundFiles.size());
        System.out.println(foundFiles.isEmpty() ? ErrorMessage.ERR_3.getFilledMessage() : foundFiles);
    }
}