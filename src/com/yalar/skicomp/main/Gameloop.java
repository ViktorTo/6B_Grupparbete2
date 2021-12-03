package com.yalar.skicomp.main;

import java.util.Scanner;

import com.yalar.skicomp.io.SimonLib;
import com.yalar.skicomp.competition.*;
import com.yalar.skicomp.participant.*;
public class Gameloop {
	SimonLib input;
	boolean done;

	public Gameloop() {
		input = new SimonLib();
		done = false;
	}

	public void intro() {
		System.out.println("Welcome to YAJava Skiing Competition!\n" + "Your options are as follows:\n\n"
				+ "1. Start A Competition\n"
				+ "2. View Results\n"
				+ "3. View Competition Log\n"
				+ "4. Exit");
	}

	public void competition() {
		Participant[] participants = new Participant[4];
		
		System.out.println("It is a beautiful day for a skiing competition!\n"
						 + "We got some incredible participants today\n"
						 + "What kind of race would you like to do?\n"
						 + "1. Individual Race\n"
						 + "2. Mass Start\n"
						 + "3. Sprint Race");
						 
		int option = SimonLib.intInput();
		switch(option) {
		case 1:
			System.out.println();
			IndStart is1 = new IndStart(8, SimonLib.intInput(), null);
		case 2:
			Masstart ms1 = new Masstart(8, 7, null);
		case 3:
			Sprint spr1 = new Sprint(8,7,null);
		}
		
	}

	public void result() {
		System.out.println("Here are the results of the race.");
		int option = SimonLib.intInput();
		// TODO handle input
	}

	public void log() {
		System.out.println("Lorem ipsum 3");
		int option = SimonLib.intInput();
		// TODO handle input
	}

	public void start() {
		int choice;
		while (!done) {
			intro();
			choice = SimonLib.intInput();
			switch (choice) {
			case 1:
				competition();
			case 2:
				result();
			case 3:
				log();
			case 4:
				done = true;
				break; // EXIT OPTION
			}
		}

		// TODO: Implement the loop.
	}
}
