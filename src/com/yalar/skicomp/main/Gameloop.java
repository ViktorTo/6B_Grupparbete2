package com.yalar.skicomp.main;

import java.util.ArrayList;
import java.util.Scanner;

import com.yalar.skicomp.io.FileManager;
import com.yalar.skicomp.io.SimonLib;
import com.yalar.skicomp.competition.*;
import com.yalar.skicomp.participant.*;
public class Gameloop {
	FileManager fileManager;
	//SimonLib input;
	boolean done;

	public Gameloop() {
		fileManager = new FileManager();
		//input = new SimonLib();
		done = false;
	}
	
	IndStart is1;	// Individual Start
	Masstart ms1;	// MassStart
	Sprint spr1;	// Sprint
	Competition comp;
	
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
			comp = new IndStart(8, SimonLib.intInput(), null);
			break;
		case 2:
			comp = new Masstart(8, 7, null);
			break;
		case 3:
			comp = new Sprint(8,7,null);
			break;
		}
		
	}

	public void result() {
		System.out.println("Available log files:");
		var list = fileManager.listDirectory("./log");
		for(var string : list){
			System.out.println(string);
		}

		System.out.println("Enter file name");
		String path = SimonLib.stringInput();
		path = "./log/" + path + ".log";

		if(fileManager.doesExist(path)){
			ArrayList<String> content = fileManager.readFile(path);
			for(var string : content){
				System.out.println(string);
			}
			return;
		}
		System.out.println("File does not exist");
	}

	public void log() {
		System.out.println("Enter file name");
		String path = SimonLib.stringInput();

		//for testing only, replace with competition.getArrayList()
		ArrayList<String> testData = new ArrayList<>();
		testData.add("line 1");
		testData.add("line 2");
		testData.add("line 3");

		//return if file creation was successful
		if(fileManager.saveFile(testData, "./log/" + path + ".log")) return;

		System.err.println("An error occurred while creating the log file");
	}

	public void start() {
		int choice;
		while (!done) {
			intro();
			choice = SimonLib.intInput();
			switch (choice) {
			case 1:
				competition();
				break;
			case 2:
				result();
				break;
			case 3:
				log();
				break;
			case 4:
				done = true;
				break; // EXIT OPTION
			}
		}

		// TODO: Implement the loop.
	}
}
