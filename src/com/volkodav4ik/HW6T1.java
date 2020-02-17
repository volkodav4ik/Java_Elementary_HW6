package com.volkodav4ik;

import java.util.Scanner;

public class HW6T1 {
    private static final Scanner SCANNER = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("Please, enter first comparable line");
        String a = SCANNER.nextLine();
        System.out.println("Please, enter second comparable line");
        String b = SCANNER.nextLine();
        int result = compareString(a, b);
        System.out.println("Result of comparison is: " + result);
    }

    private static int compareString(String a, String b) {
        if (a.length() != b.length()) {
            return -1;
        }
        StringBuilder sba = new StringBuilder(a);
        StringBuilder sbb = new StringBuilder(b);
        for (int i = sba.length() - 1; i >= 0; i--) {
            for (int j = sbb.length() - 1; j >= 0; j--) {
                if (sba.charAt(i) == sbb.charAt(j)) {
                    sba.deleteCharAt(i);
                    sbb.deleteCharAt(j);
                    break;
                }
            }
        }
        return sba.length();
    }
}
