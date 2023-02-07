package br.com.ada.georg.filehandler.menus;

import br.com.ada.georg.filehandler.printer.MenuPrinter;
import br.com.ada.georg.filehandler.printer.Printer;

import java.io.File;
import java.util.InputMismatchException;
import java.util.Scanner;

public class InputHandler {

    private final Scanner SCANNER = new Scanner(System.in);

    public String getString(String prompt) {
        return getString(prompt, true).trim();
    }

    public String getURLInput() {
        return getString("Enter the URL: ");
    }

    public String getPath(boolean pathToCreateFolder) {
        String path = getString("Enter the file's path: ");
        File file = new File(path);
        if (file.exists() || pathToCreateFolder) {
            return path;
        } else {
            Printer.printFormattedMessage("Invalid path");
            return getPath(false);
        }
    }

    public String getDirectory(boolean source) {
        String promt = (source) ? "Enter the source directory: " : "Enter the destination directory: ";
        String path = getString(promt);
        File file = new File(path);
        if (file.isDirectory()) {
            return path;
        } else {
            Printer.printFormattedMessage("Invalid directory");
            return getDirectory(source);
        }
    }

    public String getFileNameWithExtension(String[] acceptedExtensions) {
        return getFileName(true, acceptedExtensions);
    }

    public String getFileNameWithoutExtension() {
        return getFileName(false, null);
    }

    private String getFileName(boolean withExtension, String[] extensions) {
        String prompt = (withExtension) ? "Enter the file name: " : "Enter the file name without an extension: " ;
        String fileName = getString(prompt);
        boolean validExtension = (withExtension) ? !fileName.endsWith(".") && isCompliantWithExtensions(fileName, extensions) : !fileName.endsWith(".");
        if (validExtension) {
            return fileName.trim();
        } else {
            Printer.printFormattedMessage("Invalid file name");
            return getFileName(withExtension, extensions);
        }
    }

    private boolean isCompliantWithExtensions(String fileName, String[] extensions) {
        for (String extension : extensions) {
            if (fileName.endsWith(extension)) {
                return true;
            }
        }
        return false;
    }

    private String getString(String prompt, boolean firstTry) {
        String string = (String) input("String", "Invalid input, " + prompt, prompt, firstTry );
        if (string != null && !string.isEmpty() && !string.isBlank()) {
            return string;
        }
        return getString(prompt, false);
    }

    public Integer getIntegerFromMenu(String[] options, int min, int max, String prompt,
                                             String invalidValueMessage, boolean firstTry) {

        MenuPrinter.printMenuWithOptions(options);
        Integer input = (Integer) input("int", invalidValueMessage, prompt, firstTry);
        if (input != null) {
            if (input >= min && input <= max) {
                return input;
            }
        }
        return getIntegerFromMenu(options, min, max, prompt, invalidValueMessage, false);
    }

    public Integer getInteger(int min, int max, String prompt, String invalidValueMsg) {
        return getInteger(min, max, prompt, invalidValueMsg, true);
    }

    private Integer getInteger(int min, int max, String prompt, String invalidValueMessage, boolean firstTry) {
        Integer input = (Integer) input("int", invalidValueMessage, prompt, firstTry);
        if (input != null) {
            if (input >= min && input <= max) {
                return input;
            }
        }
        return getInteger(min, max, prompt, invalidValueMessage, false);
    }


    private Object input(String type, String invalidValueMessage, String prompt, boolean firstTry) {
        String message;
        Object input;
        if (!firstTry) {
            message = invalidValueMessage;
        }
        else {
            message = prompt;
        }
        Printer.printFormattedMessage(message);
        try {
            switch (type) {
                case "String" -> input = SCANNER.nextLine();
                case "int" -> {
                    input = SCANNER.nextInt();
                    SCANNER.skip("((?<!\\R)\\s)*");
                }
                default -> input = null;
            }
            return input;
        } catch (InputMismatchException e) {
            SCANNER.next();
            return null;
        }
    }
}

