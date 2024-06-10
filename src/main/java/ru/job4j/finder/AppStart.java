package ru.job4j.finder;

import ru.job4j.finder.search.Search;

import java.io.IOException;

/**
 * Console application for searching files by name, mask or regex
 * @author Denis Trunov
 * @Date: 04.06.2024
 */
public class AppStart {
    
    public static void main(String[] args) throws IOException {
        Search search = new Search();
        search.execute(args);
    }
}