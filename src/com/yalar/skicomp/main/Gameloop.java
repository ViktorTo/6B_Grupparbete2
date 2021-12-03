package com.yalar.skicomp.main;

import java.util.Scanner;

import com.yalar.skicomp.io.SimonLib;

public class Gameloop {
	SimonLib input;
	boolean done;

	public Gameloop() {
		input = new SimonLib();
		done = false;
	}

	public void intro() {
		// TODO: ADD TEXT!
		int option = SimonLib.intInput();
		// TODO handle input
	}

	public void competition() {
		// TODO: ADD TEXT!
		int option = SimonLib.intInput();
		// TODO handle input
	}

	public void result() {
		// TODO: ADD TEXT!
		int option = SimonLib.intInput();
		// TODO handle input
	}

	public void log() {
		// TODO: ADD TEXT!
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
