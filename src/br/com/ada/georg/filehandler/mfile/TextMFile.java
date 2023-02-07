package br.com.ada.georg.filehandler.mfile;

import br.com.ada.georg.filehandler.FileHandler;
import br.com.ada.georg.filehandler.enums.MFileAnnotationType;

public class TextMFile extends MFile{

    public TextMFile(String fileName, String path, String content, MFileAnnotationType type) {
        super(fileName, path, content, type);
    }

    @Override
    public void handleRequest(FileHandler fileHandler) {
        if (fileHandler.isMultiFileCreationMode()) {
            fileHandler.getTextMFileRequests().add(this);
            return;
        }
        if(getType().isExtraFolderNecessary()) {
            fileHandler.createFolder(getDestinationPath() + getType().getExtraFolderName());
        }
        fileHandler.saveFile(
                getDestinationPath() + getType().getExtraFolderName(),
                getContent(),
                getType(),
                getFileName()
        );
    }
}
