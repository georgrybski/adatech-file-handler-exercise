package br.com.ada.georg.filehandler;

import br.com.ada.georg.filehandler.mfile.MFile;
import br.com.ada.georg.filehandler.orchestrators.FileOrchestrator;

import java.util.LinkedList;
import java.util.List;

public class FileHandler extends FileOrchestrator {
    private List<MFile> textMFileRequests;
    private List<MFile> imageMFileRequests;
    private List<String> directoryRequestsPath;
    private List<String> directoryDeletionRequests;


    public FileHandler() {
        textMFileRequests = new LinkedList<>();
        imageMFileRequests = new LinkedList<>();
        directoryRequestsPath = new LinkedList<>();
        directoryDeletionRequests = new LinkedList<>();
    }

    public void fetchAndSaveImageFromDirectory(String sourcePath, String destinationPath, String fileExtension, String fileName) {
        recoverImageFile(sourcePath);
        saveImageFile(destinationPath, fileExtension, fileName);
    }

    public void fetchAndSaveImageFromURL(String url, String destinationDirectory, String fileExtension, String fileName) {
        if (imageOrchestrator.recoverImageFromURL(url)) {
            saveImageFile(destinationDirectory, fileExtension, fileName);
        }
        else {
            System.out.println("Error: Could not fetch image from URL");
        }
    }

    public void displayImageFromFile(String path) {
        recoverImageFile(path);
        imageOrchestrator.displayImage();
    }

    public void displayImageFromURL(String url) {
        if (imageOrchestrator.recoverImageFromURL(url)) {
            imageOrchestrator.displayImage();
        }
        else {
            System.out.println("Error: Could not fetch image from this URL");
        }
    }

    public void saveAllFiles() {
        createFolders(directoryRequestsPath);
        saveAllListOfFiles(textMFileRequests);
        saveAllListsOfImages(imageMFileRequests);
        directoryRequestsPath.clear();
        textMFileRequests.clear();
        imageMFileRequests.clear();
    }

    public List<MFile> getTextMFileRequests() {
        return textMFileRequests;
    }

    public List<MFile> getImageMFileRequests() {
        return imageMFileRequests;
    }

    public List<String> getDirectoryRequestsPath() {
        return directoryRequestsPath;
    }

    public List<String> getDirectoryDeletionRequests(){
        return directoryDeletionRequests;
    }

    public void deleteRequestedDirectories() {
        directoryDeletionRequests.forEach(this::removeFolder);
        directoryDeletionRequests.clear();
    }

    private void saveAllMFiles(List<MFile> mFiles) {
        mFiles.forEach(mfile ->{ mfile.handleRequest(this);});
    }

    @Override
    public void saveAllListOfFiles(List<MFile> mFiles){
        saveAllMFiles(mFiles);
    }

    @Override
    public void saveAllListsOfImages(List<MFile> mImageFiles){
        saveAllMFiles(mImageFiles);
    }
}
