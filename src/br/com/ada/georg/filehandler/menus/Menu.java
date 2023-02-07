package br.com.ada.georg.filehandler.menus;

import br.com.ada.georg.filehandler.FileHandler;
import br.com.ada.georg.filehandler.FileHandlerRunner;
import br.com.ada.georg.filehandler.mfile.MFile;
import br.com.ada.georg.filehandler.enums.MFileAnnotationType;
import br.com.ada.georg.filehandler.mfile.factory.MFileFactory;

import java.util.Optional;

public class Menu {
    private InputHandler inputHandler;
    private FileHandlerRunner fileHandlerRunner;
    private FileHandler fileHandler;
    private MFileFactory mFileFactory;

    public Menu(FileHandlerRunner fileHandlerRunner) {
        this.inputHandler = new InputHandler();
        this.fileHandlerRunner = fileHandlerRunner;
        this.fileHandler = fileHandlerRunner.getFileHandler();
        this.mFileFactory = new MFileFactory(inputHandler);
    }

    public void mainMenu() {
        switch (getIntFrom(new String[]{
                "1 - Create File",
                "2 - Create Multiple Files At Once",
                "3 - Visualize Files and Folder Structure",
                "4 - View File Content",
                "5 - Delete File",
                "6 - Delete Multiple Folders At Once",
                "7 - View Created Folders",
                "8 - Exit Application",
                "9 - See Cute Panda"
        })) {
            case 1 -> createFileSubMenu();
            case 2 -> createMultipleFilesSubMenu();
            case 3 -> fileVisualizationSubMenu();
            case 4 -> viewFileContentSubMenu();
            case 5 -> fileDeletionSubMenu();
            case 6 -> deleteMultipleFolders();
            case 7 -> fileHandler.listAllCreatedFolders();
            case 8 -> fileHandlerRunner.stop();
            case 9 -> fileHandler.displayImageFromURL("https://s4.static.brasilescola.uol.com.br/img/2019/09/panda.jpg");
        }
    }

    private void deleteMultipleFolders() {
        int foldersToBeDeleted = inputHandler.getInteger(1, 100,
                "Insert the number of folders you would like to delete",
                "Invalid value, please insert an integer above 0 and below 100");
        for (int i = 0; i < foldersToBeDeleted; i++) {
            fileHandler.getDirectoryDeletionRequests().add(inputHandler.getPath(false));
        }
        fileHandler.deleteRequestedDirectories();
    }

    private void createMultipleFilesSubMenu() {
        fileHandler.setMultiFileCreationMode(true);
        int filesToBeCreated = inputHandler.getInteger(1, 100,
                "Insert the number of files you would like to create",
                "Invalid value, please insert an integer above 0 and below 100");
        for (int i = 0; i < filesToBeCreated; i++) {
            createFileSubMenu();
        }
        fileHandler.setMultiFileCreationMode(false);
        fileHandler.saveAllFiles();
    }

    private void viewFileContentSubMenu() {
        switch (getIntFrom(new String[]{
                "1 - View Text File Content",
                "2 - View Image File Content"
        })) {
            case 1 -> fileHandler.recoverFile(
                    inputHandler.getDirectory(true),
                    inputHandler.getFileNameWithExtension(new String[]{".txt"})
            );
            case 2 -> viewImageSubSubMenu();
        }
    }

    public void viewImageSubSubMenu() {
        switch (getIntFrom(new String[]{
                "1 - Display image from an URL",
                "2 - Display image from a Directory"
        })) {
            case 1 -> fileHandler.displayImageFromURL(inputHandler.getURLInput());
            case 2 -> fileHandler.displayImageFromFile(inputHandler.getPath(false));
        }
    }

    public void createFileSubMenu() {
        Optional<MFile> optionalMFile = Optional.ofNullable(
            switch (getIntFrom(new String[]{
                    "1 - Create a Text File",
                    "2 - Create an Image File",
                    "3 - Create a Folder"
            })) {
                case 1 -> createTextFileSubSubMenu();
                case 2 -> createImageFileSubSubMenu();
                case 3 -> {
                    fileHandler.createFolder(inputHandler.getPath(true));
                    yield null;
                }
                default -> null;
            });
            optionalMFile.ifPresent(mFile -> mFile.handleRequest(fileHandler));
    }

    private MFile createTextFileSubSubMenu() {
        var type = MFileAnnotationType.getTranslatorHashmap().get(getIntFrom(new String[]{
                "1 - Create 'REMINDER' File",
                "2 - Create 'IMPORTANT' File",
                "3 - Create 'SIMPLE' File "}));
        return mFileFactory.createTextMFile(type);
    }

    private MFile createImageFileSubSubMenu() {
        return switch (getIntFrom(new String[]{
                "1 - Create an Image File from a URL",
                "2 - Create an Image File from a Directory"
        })) {
            case 1 -> mFileFactory.createImageMFileFromURL();
            case 2 -> mFileFactory.createImageMFileFromDirectory();
            default -> null;
        };
    }

    public void fileVisualizationSubMenu() {
        switch (getIntFrom(new String[]{
                "1 - Show all Files in a Directory and Subdirectories",
                "2 - Show all Text Files in a Directory and Subdirectories",
                "3 - Show all Image Files in a Directory and Subdirectories",
                "4 - Show all Directories and Subdirectories in a Directory"
        })) {
            case 1 -> fileHandler.listAllFiles(inputHandler.getDirectory(true));
            case 2 -> fileHandler.listFiles(inputHandler.getDirectory(true));
            case 3 -> fileHandler.listAllImageFiles(inputHandler.getDirectory(true));
            case 4 -> fileHandler.listAllFolders(inputHandler.getDirectory(true));
        }
    }

    private void fileDeletionSubMenu() {

        switch (getIntFrom(new String[]{
                "1 - Delete a Text File",
                "2 - Delete an Image File",
                "3 - Delete a Folder"
        })) {
            case 1 -> fileHandler.removeFile(inputHandler.getDirectory(true),
                    inputHandler.getFileNameWithExtension(new String[]{".txt"}));
            case 2 -> fileHandler.removeImageFile(inputHandler.getDirectory(true),
                    inputHandler.getFileNameWithExtension(new String[]{".png", ".jpg"}));
            case 3 -> fileHandler.removeFolder(inputHandler.getDirectory(true));
        }
    }

    protected int getIntFrom(String[] opcoes) {
        String prompt = "Insert a value between " + 1 + " and " + opcoes.length;
        String invalidValueMessage = "Invalid value! " + prompt;
        return inputHandler.getIntegerFromMenu(opcoes, 1, opcoes.length, prompt,
                invalidValueMessage, true);
    }
}
