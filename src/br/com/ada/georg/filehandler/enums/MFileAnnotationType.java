package br.com.ada.georg.filehandler.enums;

import java.util.Arrays;
import java.util.HashMap;

public enum MFileAnnotationType {
    REMINDER(".txt", true, "\\reminder"),
    IMPORTANT(".txt", true , "\\important"),
    SIMPLE(".txt", false, ""),
    IMAGE("png", true, "\\images");

    private String extension;
    private boolean extraFolderNecessary;
    private String extraFolderName;

    MFileAnnotationType(String extension, boolean extraFolderNecessary, String extraFolderName) {
        this.extension = extension;
        this.extraFolderNecessary = extraFolderNecessary;
        this.extraFolderName = extraFolderName;
    }

    public static HashMap<Integer, MFileAnnotationType> getTranslatorHashmap() {
        return MFileAnnotationTypeTranslator.translatorHashMap;
    }
    public String getExtension() {
        return extension;
    }

    public boolean isExtraFolderNecessary() {
        return extraFolderNecessary;
    }

    public String getExtraFolderName() {
        return extraFolderName;
    }

    static class MFileAnnotationTypeTranslator {
        static HashMap<Integer, MFileAnnotationType> translatorHashMap;

        static {
            initializeTranslatorHashmap();
        }

        private static void initializeTranslatorHashmap() {
            translatorHashMap = new HashMap<>();
            var counter = new int[] {1};
            Arrays.stream(MFileAnnotationType.values()).forEach(type -> {
                translatorHashMap.put(counter[0]++, type);
            });
        }
    }
}
