package br.com.ada.georg.filehandler.orchestrators;

import br.com.ada.georg.filehandler.enums.FileFilter;
import br.com.ada.georg.filehandler.interfaces.FolderManagement;
import br.com.ada.georg.filehandler.printer.Printer;

import java.io.File;
import java.util.List;

public class FolderOrchestrator implements FolderManagement {
    protected RecursiveIterator recursiveIterator;
    protected File file = null;
    protected List<String> createdFoldersPath;
    protected boolean multiFileCreationMode;

    public FolderOrchestrator() {
        recursiveIterator = new RecursiveIterator();
        createdFoldersPath = new java.util.ArrayList<>();
    }

    public void createFolders(List<String> folderPaths) {
        folderPaths.stream().forEach(this::createFolder);
    }

    public void removeFolders(List<String> folderPaths) {
        folderPaths.stream().forEach(this::removeFolder);
    }

    public void listAllCreatedFolders() {
        Printer.printFormattedMessage("Folders created: ");
        createdFoldersPath.forEach(folder -> System.out.println("\t" + folder));
    }

    @Override
    public boolean createFolder(String path) {
        boolean sucess = false;
        if (multiFileCreationMode) {
            getDirectoryRequestsPath().add(path);
        } else {
            file = new File(path);
            if (!file.exists()) {
                sucess = file.mkdirs();
                createdFoldersPath.add(path);
            }
        }
        return sucess;
    }

    @Override
    public boolean removeFolder(String path) {
        boolean success = false;
        file = new File(path);
        if (file.exists()) {
            success = recursiveIterator.deleteDirectory(file);
            if (success) {
                getDirectoryRequestsPath().remove(path);
            }
        }
        return success;
    }

    @Override
    public void listAllFolders(String path) {
        file = new File(path);
        if (file.exists()) {
            recursiveIterator.listFiles(file, 0, FileFilter.FOLDER);
        }
    }

    public void listAllFiles(String path) {
        file = new File(path);
        if (file.exists()) {
            recursiveIterator.listFiles(file, 0, FileFilter.ALL);
        }
    }

    public List<String> getDirectoryRequestsPath() {
        return null;
    }

    public List<String> getDirectoryDeletionsRequests(){
        return null;
    }

    public boolean isMultiFileCreationMode() {
        return multiFileCreationMode;
    }

    public void setMultiFileCreationMode(boolean multiFileCreationMode) {
        this.multiFileCreationMode = multiFileCreationMode;
    }
}