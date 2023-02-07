package br.com.ada.georg.filehandler.interfaces;

import br.com.ada.georg.filehandler.mfile.MFile;

public interface ImageMFileFactory {
    MFile createImageMFileFromURL();
    MFile createImageMFileFromDirectory();
}
