package br.com.ada.georg.filehandler.mfile.factory;

import br.com.ada.georg.filehandler.interfaces.ImageMFileFactory;
import br.com.ada.georg.filehandler.interfaces.TextMFileFactory;
import br.com.ada.georg.filehandler.menus.InputHandler;
import br.com.ada.georg.filehandler.mfile.ImageMFile;
import br.com.ada.georg.filehandler.mfile.MFile;
import br.com.ada.georg.filehandler.enums.MFileAnnotationType;
import br.com.ada.georg.filehandler.mfile.TextMFile;

public class MFileFactory implements TextMFileFactory, ImageMFileFactory {
    private InputHandler inputHandler;

    public MFileFactory(InputHandler inputHandler) {
        this.inputHandler = inputHandler;
    }

    @Override
    public MFile createImageMFileFromURL() {
        return new ImageMFile(
                inputHandler.getFileNameWithoutExtension(),
                inputHandler.getDirectory(false),
                inputHandler.getURLInput(),
                true
        );
    }

    @Override
    public MFile createImageMFileFromDirectory() {
        return new ImageMFile(
                inputHandler.getFileNameWithoutExtension(),
                inputHandler.getDirectory(false),
                inputHandler.getPath(false),
                false
        );
    }

    @Override
    public MFile createTextMFile(MFileAnnotationType type) {
        return new TextMFile(
                inputHandler.getFileNameWithoutExtension(),
                inputHandler.getDirectory(false),
                inputHandler.getString("Insert the text that should be written in the file: "),
                type
        );
    }
}
