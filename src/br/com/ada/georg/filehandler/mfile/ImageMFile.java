package br.com.ada.georg.filehandler.mfile;

import br.com.ada.georg.filehandler.FileHandler;
import br.com.ada.georg.filehandler.enums.MFileAnnotationType;

public class ImageMFile extends MFile{
    boolean sourceIsURL;

    public ImageMFile(String fileName, String destinationPath, String sourcePath, boolean sourceIsURL) {
        super(fileName, destinationPath, sourcePath, MFileAnnotationType.IMAGE);
        this.sourceIsURL = sourceIsURL;
    }

    @Override
    public boolean sourceIsURL() {
        return sourceIsURL;
    }

    @Override
    public void handleRequest(FileHandler fileHandler) {
        if (fileHandler.isMultiFileCreationMode()) {
            fileHandler.getImageMFileRequests().add(this);
            return;
        }
        if (sourceIsURL()) {
            fileHandler.fetchAndSaveImageFromURL(
                    getContent(), getDestinationPath(),
                    getExtension(), getFileName()
            );
        }
        else {
            fileHandler.fetchAndSaveImageFromDirectory(
                    getContent(), getDestinationPath(),
                    getExtension(), getFileName()
            );
        }
    }
}
