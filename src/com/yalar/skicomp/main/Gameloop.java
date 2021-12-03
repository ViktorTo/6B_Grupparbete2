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
	
	public void printMenu() {
		
	}
	
	public void start() {
		while(!done) {
			SimonLib.intInput();
		}
		
		//TODO: Implement the loop.
	}
}
