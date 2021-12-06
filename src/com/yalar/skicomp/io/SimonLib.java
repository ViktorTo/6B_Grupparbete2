package com.yalar.skicomp.io;

import java.util.Scanner;

public class SimonLib {
	static Scanner scan = new Scanner(System.in);

	/*public SimonLib() {
		scan = new Scanner(System.in);
	}*/

	public static int intInput() {

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

	public static double doubleInput() {

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

	public static String stringInput() {

		char[] list = { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's',
				't', 'u', 'v', 'w', 'x', 'y', 'z', 'å', 'ä', 'ö' };

		String s = "";

		int num = -1;
		while (num == -1) {
			System.out.print("> ");
			try {
				s = scan.nextLine();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			try {
				for (int i = 0; i < list.length; i++) {
					for (int j = 0; j < s.length(); j++) {
						if (s.charAt(j) == list[i]) {
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
