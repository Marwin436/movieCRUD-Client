package org.mathias.movieapiclient.utils;

import java.util.Scanner;

public class InputHandler {
    private static final Scanner scanner = new Scanner(System.in);

    public static String getInputAsString(String prompt) {
        System.out.println(prompt);
        return scanner.nextLine();
    }

    public static int getInputAsInt(String prompt) {
        System.out.println(prompt);
        try {
            int input = scanner.nextInt();
            scanner.nextLine();
            return input;
        }
        catch (Exception e) {
            System.out.println("Invalid input. Please enter a number.");
            scanner.nextLine();
            return getInputAsInt(prompt);
        }
    }

    public static Long getInputAsLong(String prompt) {
        System.out.println(prompt);
        Long input = scanner.nextLong();
        scanner.nextLine();
        return input;
    }
}
