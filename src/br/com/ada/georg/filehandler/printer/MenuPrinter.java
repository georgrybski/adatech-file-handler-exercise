package br.com.ada.georg.filehandler.printer;

public class MenuPrinter extends Printer {

    public static void printMenuWithOptions(String[] options) {

        String[][] optionsMatrix = getMenuComponentMatrix(options);
        printFrameLine();
        StringBuilder buttonsOuterLine = new StringBuilder();
        StringBuilder innerButtonLine = new StringBuilder();

        for (int i = 0, addedOptions = 0; i < optionsMatrix.length; i++) {

            boolean optionFitsLine =
                    (optionsMatrix[i][1].length() + innerButtonLine.length() < LINE_LENGTH - 4);

            if (optionFitsLine) {
                String optionText = optionsMatrix[i][0];
                String optionDelimiter = optionsMatrix[i][1];
                innerButtonLine.append(optionText).append(" ".repeat(2));
                buttonsOuterLine.append(optionDelimiter).append(" ".repeat(2));
                addedOptions++;
            }

            boolean lastIterationOrDontFitInLine = (!optionFitsLine || i == optionsMatrix.length - 1);
            if (lastIterationOrDontFitInLine) {
                String oddOrEvenOffset =
                        " ".repeat((LINE_LENGTH - innerButtonLine.toString().length()) % 2);
                String spacesEachSide =
                        " ".repeat((LINE_LENGTH - innerButtonLine.toString().length()) / 2);

                printOptionsLines(spacesEachSide, innerButtonLine,
                        buttonsOuterLine, oddOrEvenOffset);

                String optionText = optionsMatrix[i][0];
                String optionDelimiter = optionsMatrix[i][1];
                buttonsOuterLine = new StringBuilder("" + optionDelimiter + " ".repeat(2));
                innerButtonLine = new StringBuilder("" + optionText + " ".repeat(2));

                // Checking last iteration avoid value duplicity on the fourth line of buttons.
                boolean lastIteration = (i == optionsMatrix.length - 1);

                // Checking if the number of options is bigger than 2 avoids possible value duplicity on the third line.
                boolean moreThanTwoOptions = (optionsMatrix.length > 2);

                // Checking if the number of options is odd helps avoid value duplicity in the last menu line when
                // option number is odd and bigger than 3
                boolean evenIterationNumber = (i % 2 != 0);

                // Checking if the iteration number is different from the added options number avoids missing options
                // and duplication in some cases.
                boolean aditionalOptionNumberDifferentThanIterationNumber = (addedOptions != i + 1);

                // Checking if the option number is different from 6 avoids option duplicity in non-existent line.
                boolean optionNumberDifferentThanSix = (optionsMatrix.length != 6);

                // Checking if the number of options is a multiple of 5 avoids missing last option in these situations.
                boolean notMultipleOf5 = (optionsMatrix.length % 5 != 0);

                // Multiples of these numbers would have duplicity if not checked.
                boolean notMultipleOfProblematicNumbers = (
                         optionsMatrix.length != 2 &&
                         optionsMatrix.length % 3 != 0 &&
                         optionsMatrix.length % 7 != 0 &&
                         optionsMatrix.length % 10 != 0 &&
                         optionsMatrix.length % 11 != 0 &&
                         optionsMatrix.length % 13 != 0 &&
                         optionsMatrix.length % 14 != 0 &&
                         optionsMatrix.length % 16 != 0
                );

                boolean moreThanOneLineNecessary = (lastIteration && moreThanTwoOptions && evenIterationNumber &&
                        aditionalOptionNumberDifferentThanIterationNumber &&
                        optionNumberDifferentThanSix && notMultipleOfProblematicNumbers)
                        || (lastIteration && notMultipleOf5 && notMultipleOfProblematicNumbers ||  optionsMatrix.length == 10);

                if (moreThanOneLineNecessary) {
                    oddOrEvenOffset =
                            " ".repeat((LINE_LENGTH - innerButtonLine.toString().length()) % 2);
                    spacesEachSide =
                            " ".repeat((LINE_LENGTH - innerButtonLine.toString().length()) / 2);

                    printOptionsLines(spacesEachSide, innerButtonLine,
                            buttonsOuterLine, oddOrEvenOffset);
                }
            }
        }
        printFrameLine();
    }

    private static String[][] getMenuComponentMatrix(String[] options) {
        String[][] matrix = new String[options.length][2];
        for (int i = 0; i < options.length; i++) {
            String option = options[i];
            matrix[i][0] = "| " + option + " |";
            matrix[i][1] = "+" + "-".repeat(option.length() + 2) + "+";
        }
        return matrix;
    }

    private static void printOptionsLines(String spacesEachSide, StringBuilder buttonsInnerLine,
                                   StringBuilder buttonsOuterLine, String oddOrEvenOffset) {

        String outerLine = "|" + spacesEachSide + buttonsOuterLine + spacesEachSide + oddOrEvenOffset + "|";
        String innerLine = "|" + spacesEachSide + buttonsInnerLine + spacesEachSide + oddOrEvenOffset + "|";

        System.out.println(outerLine);
        System.out.println(innerLine);
        System.out.println(outerLine);
    }

}
