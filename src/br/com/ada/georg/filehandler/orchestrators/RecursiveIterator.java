package br.com.ada.georg.filehandler.orchestrators;

import br.com.ada.georg.filehandler.enums.FileFilter;

import java.io.File;
import java.util.Arrays;
import java.util.function.Predicate;

public class RecursiveIterator {

    public boolean deleteDirectory(File directory) {
        if (directory.isDirectory()) {
            File[] files = directory.listFiles();
            if (files != null) {
                Arrays.stream(files).forEach(this::deleteDirectory);
            }
        }
        return directory.delete();
    }

    public void listFiles(File fileToBeListed, int indentationLevel, Predicate<File> fileFilter) {
        if (fileToBeListed.isDirectory()) {
            if (folderContains(fileToBeListed, fileFilter) || fileFilter == FileFilter.ALL || fileFilter == FileFilter.FOLDER) {
                System.out.println(":\t".repeat(indentationLevel) + "[" + fileToBeListed.getName() + "] { ");
                if (fileToBeListed.listFiles() != null) {
                    Arrays.stream(fileToBeListed.listFiles()).forEach(file -> {
                        listFiles(file, indentationLevel + 1, fileFilter);
                    });
                }
                System.out.println(":\t".repeat(indentationLevel) + "}");
            }
        }
        else {
            if (fileFilter.test(fileToBeListed) && (fileFilter != FileFilter.FOLDER)) {
                System.out.println(":\t".repeat(indentationLevel) + fileToBeListed.getName());
            }
        }
    }

    private boolean folderContains(File folder, Predicate<File> fileFilter) {
        boolean contains = false;
        if (folder.listFiles() != null) {
            for (File file : folder.listFiles()) {
                if (file.isDirectory()) {
                    if (folderContains(file, fileFilter)) {
                        contains = true;
                        break;
                    }
                } else if (fileFilter.test(file)) {
                    contains = true;
                    break;
                }
            }
        }
        return contains;
    }
}
