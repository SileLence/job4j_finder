package ru.job4j.finder.search;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.function.Predicate;

public class Finder extends SimpleFileVisitor<Path> {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(Finder.class);
    private final ArrayList<Path> foundFiles = new ArrayList<>();
    private final Predicate<Path> condition;
    
    public Finder(Predicate<Path> condition) {
        this.condition = condition;
    }
    
    public ArrayList<Path> getFoundFiles() {
        return foundFiles;
    }
    
    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) {
        if (condition.test(file)) {
            foundFiles.add(file);
        }
        return FileVisitResult.CONTINUE;
    }
    
    @Override
    public FileVisitResult visitFileFailed(Path file, IOException exception) {
        LOGGER.warn("Cannot access a folder: [{}]", file.toAbsolutePath());
        return FileVisitResult.SKIP_SUBTREE;
    }
}
