package bullscows;

import java.util.Random;
import java.util.Scanner;

public class BullsAndCows {
    private final Scanner scan;
    private final Random rand;

    public BullsAndCows(Scanner scan, Random rand) {
        this.scan = scan;
        this.rand = rand;
    }

    public void start() {
        int turn = 1;

        int length = getLength();
        if (length == -1) {
            return;
        }

        int possibleSymbols = getRange();
        if (possibleSymbols == -1) {
            return;
        }


        if (length > possibleSymbols) {
            System.out.println("Error: it's not possible to generate a code with a length of " + length + " with " +
                    possibleSymbols + " unique symbols.");
            return;
        }

        boolean gotTheCode = false;
        String secretCode = generateWithSymbols(length, possibleSymbols);
        messageGenerator(length, possibleSymbols);
        System.out.println("Okay, let's start a game!");

        while (!gotTheCode) {
            System.out.println("Turn " + turn++);
            String guess = scan.nextLine();
            Grade grade = new Grade(guess, secretCode);
            grade.checkCode();
            if (grade.guessedTheCode()) {
                gotTheCode = true;
            }
            System.out.println(grade.toString());
            System.out.println(secretCode);
        }
    }

    private int getLength() {
        System.out.println("Please enter the secret code's length: ");
        String input = scan.nextLine();
        try {
            int inputLength = Integer.parseInt(input);
            if (inputLength > 36) {
                System.out.println("Error: maximum number of possible symbols in the code is 36 (0-9, a-z).");
            } else if (inputLength < 1) {
                System.out.println("Error: minimum number of length is 1.");
            } else {
                return inputLength;
            }
        } catch (NumberFormatException e) {
            System.out.println("Error: \""+ input + "\" isn't a valid number.");
        }
        return -1;
    }

    private int getRange() {
        System.out.println("Input the number of possible symbols in the code:");
        String input = scan.nextLine();
        try {
            int possibleSymbols = Integer.parseInt(input);
            if (possibleSymbols > 36) {
                System.out.println("Error: maximum number of possible symbols in the code is 36 (0-9, a-z).");
            } else {
                return possibleSymbols;
            }
        } catch (NumberFormatException e) {
            System.out.println("Error: \""+ input + "\" isn't a valid number.");
        }
        return -1;
    }

    private String printStars(int length) {
        StringBuilder sb = new StringBuilder();
        String stars = "*";
        for (int i = 0; i < length; i++) {
            sb.append(stars);
        }
        return sb.toString();
    }

    public void messageGenerator(int length, int possibleSymbols) {
        if (possibleSymbols <= 10) {
            System.out.println("The secret is prepared: " + printStars(length) + " " +
                    "(0-" + possibleSymbols + ").");
        } else {
            System.out.println("The secret is prepared: " + printStars(length) +
                    " (0-" + Character.forDigit(9, possibleSymbols) +
                    ", a-" + Character.forDigit(possibleSymbols - 1, possibleSymbols) + ").");
        }
    }

    public String generateWithSymbols(int length, int possibleSymbols) {
        StringBuilder sb = new StringBuilder();
        while (sb.length() < length) {
            String c = String.valueOf(Character.forDigit(rand.nextInt(possibleSymbols), possibleSymbols));
            if (sb.toString().contains(c)) {
                continue;
            }
            sb.append(c);
        }
        return sb.toString();
    }
}
