package br.com.ada.georg.filehandler.mfile;

import br.com.ada.georg.filehandler.FileHandler;
import br.com.ada.georg.filehandler.enums.MFileAnnotationType;

public abstract class MFile {
    private String content;
    private String fileName;
    private String destinationPath;
    private MFileAnnotationType type;
    private String extension;

    protected MFile(String fileName, String destinationPath, String content, MFileAnnotationType type) {
        this.fileName = fileName;
        this.destinationPath = destinationPath;
        this.type = type;
        this.content = content;
        this.extension = type.getExtension();
    }

    public abstract void handleRequest(FileHandler fileHandler);
    public String getContent() {
        return content;
    }
    public String getFileName() {
        return fileName;
    }
    public String getDestinationPath() {
        return destinationPath;
    }
    public MFileAnnotationType getType() {
        return type;
    }
    public String getExtension() {
        return extension;
    }
    public boolean sourceIsURL() {
        return false;
    }
}
