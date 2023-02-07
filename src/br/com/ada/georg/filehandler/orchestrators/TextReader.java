package br.com.ada.georg.filehandler.orchestrators;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class TextReader {
    private Scanner scanner;

    public TextReader(Scanner scanner) {
        this.scanner = scanner;
    }

    public void getTextFromFile(File file) {
        try {
            scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String data = "\t" + scanner.nextLine();
                System.out.println(data);
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found.");
        }
    }
}
