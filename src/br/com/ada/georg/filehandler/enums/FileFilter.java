package br.com.ada.georg.filehandler.enums;

import java.io.File;
import java.util.function.Predicate;

public enum FileFilter implements Predicate<File> {
    IMAGE {
        @Override
        public boolean test(File file) {
            return file.getName().endsWith(".jpg") || file.getName().endsWith(".png");
        }
    },
    TEXT {
        @Override
        public boolean test(File file) {
            return file.getName().endsWith(".txt");
        }
    },
    ALL {
        @Override
        public boolean test(File file) {
            return true;
        }
    },
    FOLDER {
        @Override
        public boolean test(File file) {
            return file.isDirectory();
        }
    }
}
