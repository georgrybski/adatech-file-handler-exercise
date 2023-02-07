package br.com.ada.georg.filehandler.interfaces;

public interface FolderManagement {
    boolean createFolder(String path);
    boolean removeFolder(String path);
    void listAllFolders(String path);
}
