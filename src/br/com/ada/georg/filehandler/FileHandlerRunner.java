package br.com.ada.georg.filehandler;

import br.com.ada.georg.filehandler.menus.Menu;
import br.com.ada.georg.filehandler.printer.Printer;

public class FileHandlerRunner {
    private FileHandler fileHandler;
    private Menu menu;
    private boolean isRunning;

    public FileHandlerRunner() {
        fileHandler = new FileHandler();
        menu = new Menu(this);
        isRunning = false;
    }

    public void start() {
        Printer.printFormattedMessage("Welcome to the Georg's Trusty File Handler!");
        isRunning = true;
        while (isRunning) {
            menu.mainMenu();
            }
        }

    public void stop() {
        isRunning = false;
        Printer.printFormattedMessage("Thank you for using the Georg's Trusty File Handler!");
    }

    public FileHandler getFileHandler() {
        return fileHandler;
    }
}
