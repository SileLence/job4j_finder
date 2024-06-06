package ru.job4j;

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
        System.out.println(foundFiles);
    }
}