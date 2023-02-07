package br.com.ada.georg.filehandler.interfaces;

import br.com.ada.georg.filehandler.enums.MFileAnnotationType;

public interface FileDatabase {
    void saveFile(String directory, String content, MFileAnnotationType type, String fileName);
    void recoverFile(String directory, String fileName);
    boolean removeFile(String directory, String fileName);
    void listFiles(String directory);
}
