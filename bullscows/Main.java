package bullscows;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Main {
    static Scanner scan = new Scanner(System.in);
    static Random rand = new Random();

    public static void main(String[] args) {
        BullsAndCows game = new BullsAndCows(scan, rand);
        game.start();
    }
}
