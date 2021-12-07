package com.yalar.skicomp.io;

import java.util.Scanner;

public class SimonLib {
	static Scanner scan = new Scanner(System.in);
	
	/**
	 * Takes in an integer from the user and makes sure it has a valid input.
	 * 
	 * @return
	 */
	public static int intInput() {

		boolean success = false;
		int num = 0;
		while (!success) {
			System.out.print("> ");
			String s = scan.nextLine();
			try {
				num = Integer.parseInt(s);
				success = true;
			} catch (Exception e) {
				System.out.println("Only enter integers!");
				num = -1;
			}
		}

		return num;
	}
	
	/**
	 * Takes in a double from the user and makes sure it has a valid input.
	 * 
	 * @return
	 */
	public static double doubleInput() {

		boolean success = false;
		double num = 0;
		while (!success) {
			System.out.print("> ");
			String s = scan.nextLine();
			try {
				num = Double.parseDouble(s);
				success = true;
			} catch (Exception e) {
				System.out.println("Only enter doubles!");
				num = -1;
			}
		}

		return num;
	}
	
	/**
	 * Takes in a string from the user and makes sure it's not empty.
	 * 
	 * @return
	 */
	public static String stringInput() {

		String s = "";

		boolean success = false;
		while (!success) {
			System.out.print("> ");
			try {
				s = scan.nextLine();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (s.isEmpty()) {
				System.out.println("You didn't enter anything!");
				success = false;
			} else {
				success = true;
			}
		}

		return s;
	}
}
