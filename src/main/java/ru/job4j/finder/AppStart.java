package ru.job4j.finder;

import ru.job4j.finder.search.Search;

/**
 * Console application for searching files by name, mask or regex
 * @author Denis Trunov - 04.06.2024
 */
public class AppStart {
    
    public static void main(String[] args) {
        Search search = new Search();
        try {
            search.execute(args);
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
        }
    }
}