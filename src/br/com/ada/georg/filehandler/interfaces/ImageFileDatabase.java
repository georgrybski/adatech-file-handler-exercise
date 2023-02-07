package br.com.ada.georg.filehandler.interfaces;

public interface ImageFileDatabase {
    void saveImageFile(String directory, String content, String fileName);
    void recoverImageFile(String directory);
    boolean removeImageFile(String directory, String fileName);
    void listAllImageFiles(String directory);
}
