package com.yalar.skicomp.io;

import java.util.Iterator;
import java.util.Scanner;

public class SimonLib {
	public static int intInput(Scanner scan) {

        int num = -1;
        while (num == -1) {
            System.out.print("> ");
            String s = scan.nextLine();
            try {
                num = Integer.parseInt(s);
            } catch (Exception e) {
                System.out.println("Only enter integers!");
                num = -1;
            }
        }

        return num;
    }
	public static double doubleInput(Scanner scan) {

        double num = -1;
        while (num == -1) {
            System.out.print("> ");
            String s = scan.nextLine();
            try {
                num = Double.parseDouble(s);
            } catch (Exception e) {
                System.out.println("Only enter doubles!");
                num = -1;
            }
        }

        return num;
    }
	public static String stringInput(Scanner scan) {
		
		char[] list = {'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p',
					   'q','r','s','t','u','v','w','x','y','z','å','ä','ö'};
		
		String s = "";
		
        int num = -1;
        while (num == -1) {
            System.out.print("> ");
            s = scan.nextLine();
            try {
                for (int i = 0; i < list.length; i++) {
					for (int j = 0; j < s.length(); j++) {
						if(s.charAt(j) == list[i]) {
							num = 1;
						}
					}
				}
            } catch (Exception e) {
                System.out.println("Only enter strings with letters in them!");
                num = -1;
            }
        }

        return s;
    }
}
