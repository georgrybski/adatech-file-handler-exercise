package br.com.ada.georg.filehandler.orchestrators;

import br.com.ada.georg.filehandler.enums.FileFilter;
import br.com.ada.georg.filehandler.interfaces.FileDatabase;
import br.com.ada.georg.filehandler.interfaces.ImageFileDatabase;
import br.com.ada.georg.filehandler.mfile.MFile;
import br.com.ada.georg.filehandler.enums.MFileAnnotationType;
import br.com.ada.georg.filehandler.printer.Printer;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;
import java.util.function.Predicate;

public class FileOrchestrator extends FolderOrchestrator implements ImageFileDatabase, FileDatabase {
    protected ImageOrchestrator imageOrchestrator;
    private TextReader textReader;
    private Scanner scanner;

    public FileOrchestrator() {
        this.imageOrchestrator = new ImageOrchestrator();
        this.textReader = new TextReader(scanner);
        this.scanner = new Scanner(System.in);
    }

   public void saveAllListOfFiles(List<MFile> mFiles){}
   public void saveAllListsOfImages(List<MFile> mImageFiles){}

    @Override
    public void saveFile(String directory, String content, MFileAnnotationType type, String fileName) {
        try (FileWriter writer = new FileWriter(directory + "\\" + fileName + type.getExtension())) {
            writer.write(content);
        } catch (IOException e) {
            System.out.println("Error saving file: " + e.getMessage());
        }
    }

    @Override
    public void saveImageFile(String directory, String content, String fileName) {
        try {
            createFolder(directory + "\\images");
            file = new File(directory + "\\images\\" + fileName + "." + content);
            ImageIO.write(imageOrchestrator.getBufferedImage(), content, file);
        } catch (IOException e) {
            System.out.println("Error saving image: " + e.getMessage());
        }
    }

    @Override
    public void recoverImageFile(String directory) {
        file = new File(directory);
        if (file.exists()){
            imageOrchestrator.convertFileToBufferImage(file);
        }
    }

    @Override
    public void recoverFile(String directory, String fileName) {
        file = new File(directory);
        if (file.exists() && file.isDirectory()) {
            for (File file : file.listFiles()) {
                if (file.getName().equals(fileName)) {
                    System.out.println("\n" + fileName + " contents: {");
                    textReader.getTextFromFile(file);
                    System.out.println("}");
                }
            }
        }
    }

    @Override
    public boolean removeImageFile(String directory, String fileName) {
        return deleteFile(directory, fileName, FileFilter.IMAGE);
    }

    @Override
    public boolean removeFile(String directory, String fileName) {
        return deleteFile(directory, fileName, FileFilter.TEXT);
    }

    public boolean deleteFile(String directory, String fileName, Predicate<File> fileTypeFilter) {
        boolean sucess = false;
        file = new File(directory + "\\" + fileName);
        if (file.exists()) {
            if (fileTypeFilter.test(file)) {
                sucess = file.delete();
            }
        }
        Printer.printFormattedMessage(sucess ? "File deleted" : "No file within selected parameters found");
        return sucess;
    }

    @Override
    public void listFiles(String directory) {
        file = new File(directory);
        recursiveIterator.listFiles(file, 0, FileFilter.TEXT);
    }

    @Override
    public void listAllImageFiles(String directory) {
        file = new File(directory);
        recursiveIterator.listFiles(file, 0, FileFilter.IMAGE);
    }
}
