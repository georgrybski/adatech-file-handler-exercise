package br.com.ada.georg.filehandler.printer;

public abstract class Printer {

    static final int LINE_LENGTH = 102;


    public static void printFormattedMessage(String message) {
        printFrameLine();

        boolean messageLargerThanLine = (message.length() > LINE_LENGTH - 6);
        if (messageLargerThanLine) {
            printFormattedMultiLineString(message);
        } else {
            printIndentedFormattedLine(message);
        }
        printFrameLine();
    }

    protected static void printFrameLine() {
        System.out.println("+" + "-".repeat(LINE_LENGTH) + "+");
    }

    protected static void printFormattedMultiLineString(String string) {
        String[] stringArray = string.trim().split(" ");
        String currentLine = "";

        for (int index = 0; index < stringArray.length; index++) {

            String word = stringArray[index];

            boolean wordFitsInLine =
                    (word.length() + currentLine.length() + 1 < LINE_LENGTH - 6);

            if (wordFitsInLine) {
                currentLine += word + " ";
            } else {
                printIndentedFormattedLine(currentLine);
                currentLine = word + " ";
            }

            boolean lastLine = (index == stringArray.length - 1);
            if (lastLine) {
                printIndentedFormattedLine(currentLine);
                return;
            }
        }
    }

    public static void printIndentedFormattedLine(String line) {
        int spaceNumber = (LINE_LENGTH - line.length());
        int spacesEachSide = spaceNumber / 2;
        int oddOrEvenOffset = spaceNumber % 2;

        String formattedLine = "|" + " ".repeat(spacesEachSide) +
                line +
                " ".repeat(spacesEachSide + oddOrEvenOffset) + "|";

        System.out.println(formattedLine);
    }
}


