package br.com.ada.georg.filehandler.interfaces;

import br.com.ada.georg.filehandler.mfile.MFile;
import br.com.ada.georg.filehandler.enums.MFileAnnotationType;

public interface TextMFileFactory {
    MFile createTextMFile(MFileAnnotationType type);
}
