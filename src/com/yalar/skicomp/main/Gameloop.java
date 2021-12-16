package com.yalar.skicomp.main;

import java.util.ArrayList;
import java.util.List;


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
	Competition comp;
	
	public void intro() {
		System.out.println("Welcome to YAJava Skiing Competition!\n" + "Your options are as follows:\n\n"
				+ "1. Start A Competition\n" + "2. View Results\n" + "3. View Competition Log\n" + "4. Exit");
	}

	public void competition() {
		Participant[] participants = new Participant[4];

		participants = Participant.setParticipantName(participants);
		participants = Participant.setParticipantNum(participants);

		System.out.println("\nIt is a beautiful day for a skiing competition!\n"
				+ "We got some incredible participants today\n" + "What kind of race would you like to do?\n"
				+ "1. Individual Race\n" + "2. Mass Start\n" + "3. Back To Menu");

		int option = SimonLib.intInput();
		switch (option) {
		case 1:
			System.out.println("Time to start an individual start!");
			is1 = new IndStart(8, participants.length, participants);
			indRace(participants);
			break;
		case 2:
			System.out.println("Time to start a mass start!");
			ms1 = new Masstart(8, participants.length, participants);
			massRace(participants);
			break;
		case 3:
			intro();
		}

	}

	// Method for making an individual race.
	private void indRace(Participant[] participants) {
		System.out.println("Please enter a time interval for each participant");
		int setInterval = SimonLib.intInput();

		is1.startSkierIntervall(participants, setInterval);
		int numOfRacersFinish = 0;
		boolean temp = true;
		while (temp) {

			boolean cont = false;
			do {
				System.out.println("What do you want to do?\n1. Set interval\n" + "2. Check Placement\n"
						+ "3. Check Fields\n" + "4. Skier finished\n" + "5. Finish Race");
				int input = SimonLib.intInput();
				switch (input) {
				case 1:
					setInterval(participants);
					break;
				case 2:
					checkPlcmnt(participants, is1);
					break;
				case 3:
					is1.checkField();
					break;
				case 4:
					numOfRacersFinish = finishSkier(participants, numOfRacersFinish);
					break;
				case 5:
					cont = finishRace(participants, numOfRacersFinish, cont, is1);
					break;
				}

			} while (!cont);
			System.out.println(
					"Do you wish to:\n1. Go back to Main Menu\n2. New race with new participants\n3. Exit");
			int choice = SimonLib.intInput();
			switch (choice) {
			case 1:
				start();
				break;
			case 2:
				competition();
				break;
			case 3:
				System.exit(0);
				break;
			}
		}

	}

	// Method for mass race
	private void massRace(Participant[] participants) {
		int numOfRacersFinish = 0;
		ms1.startAllSkiers(participants);
		for (int i = 0; i < participants.length; i++) {
			System.out.println(participants[i]);
		}

		boolean cont = false;
		do {
			System.out.println("What do you want to do?\n1. Set interval\n" + "2. Check Placement\n"
					+ "3. Check Fields\n" + "4. Skier finished\n" + "5. Finish Race");
			int input = SimonLib.intInput();
			switch (input) {
			case 1:
				setInterval(participants);
				break;
			case 2:
				checkPlcmnt(participants, ms1);
				break;
			case 3:
				ms1.checkField();
				break;
			case 4:
				numOfRacersFinish = finishSkier(participants, numOfRacersFinish);
				break;
			case 5:
				cont = finishRace(participants, numOfRacersFinish, cont, ms1);
				break;
			default:
				System.out.println("Invalid input, please try again");
				break;
			}

		} while (!cont);

		System.out.println(
				"Do you wish to:\n1. Go back to Main Menu\n2. New race with new participants\n3. Exit");
		int choice = SimonLib.intInput();

		switch (choice) {
		case 1:
			start();
			break;
		case 2:
			competition();
			break;
		case 3:
			System.exit(0);
			break;
		default:
			System.out.println("Invalid input.");
		}
	}

	private void checkPlcmnt(Participant[] participants, Competition c1) {
		printParticipants(participants);

		System.out.println();
		int checkPartNum = SimonLib.intInput();
		for (int i = 0; i < participants.length; i++) {
			if (participants[i].getParticipantNumber() == checkPartNum) {
				if (participants[i].isFinished()) {
					System.out.println("This player has already crossed the finish line.");
					i = participants.length;
				} else {
					c1.checkPlacement(checkPartNum);
				}
				i = participants.length;
			}
		}
	}

	private void printParticipants(Participant[] participants) {
		System.out.print("Choose a participant's number: ");
		for (int i = 0; i < participants.length; i++) {
			System.out.print(participants[i].getParticipantNumber() + ", ");

		}
	}

	private boolean finishRace(Participant[] participants, int numOfRacersFinish, boolean cont, Competition c1) {
		if (c1.getSw().getEnd() == null) {
			if (c1.isCompDone()) {
				cont = true;
				c1.endComp();
				c1.sortResults();

				c1.checkField();
			} else {
				c1.checkField();
				if (numOfRacersFinish < participants.length) {
					System.out.println("Warning, competition has not ended yet. (" + numOfRacersFinish + "/"
							+ participants.length + ")" + " has crossed the finish line.");
					System.out.println("Do you wish to finish the race anyway? \n1. Yes\n2. No");
					int FinishRace = SimonLib.intInput();

					switch (FinishRace) {
					case 1:
						cont = true;
						c1.checkField();
						c1.endComp();
						break;
					default:
						break;
					}
				}
			}

		} else {
			System.out.println("Race has already ended!");
			System.out.println(
					"Do you wish to:\n1. Go back to Main Menu\n2. New race with new participants\n3. Exit");
			int choice = SimonLib.intInput();

			switch (choice) {
			case 1:
				start();
				break;
			case 2:
				competition();
				break;
			case 3:
				System.exit(0);
				break;
			}
		}
		return cont;
	}

	private int finishSkier(Participant[] participants, int numOfRacersFinish) {
		printParticipants(participants);

		System.out.println();
		int num = SimonLib.intInput();
		for (int i = 0; i < participants.length; i++) {
			if (num == participants[i].getParticipantNumber()) {
				numOfRacersFinish++;
				participants[i].setFinished(true);
				participants[i].getStopWatch().endClock();
				System.out.println(participants[i]);
			}
		}
		return numOfRacersFinish;
	}

	private void setInterval(Participant[] participants) {
		printParticipants(participants);

		int num = SimonLib.intInput();
		for (int i = 0; i < participants.length; i++) {
			if (num == participants[i].getParticipantNumber()) {
				participants[i].getStopWatch().setInterval();
				System.out.println(participants[i]);
				System.out.println(participants[i].getStopWatch().getLatestInt());
				i = participants.length;
			}
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
			List<String> content = fileManager.readFile(path);
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
