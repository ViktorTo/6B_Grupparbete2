package com.yalar.skicomp.main;

import java.util.Date;
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
	
	private void intro() {
		System.out.println("Welcome to YAJava Skiing Competition!\n" + "Your options are as follows:\n\n"
				+ "1. Start A Competition\n" + "2. View Results\n" + "3. View Competition Log\n" + "4. Exit");
	}

	
	/**
	 * Method that contains the menu of what races to choose from, if the user wishes to either start an individual race (see indRace),
	 * or mass start (see massRace) 
	 * @param competition
	 * */
	public void competition() {
		
		System.out.println("Please enter how many participants you want");
		
		int numOfRacers = SimonLib.intInput();
		
		Participant[] participants = new Participant[numOfRacers];

		participants = Participant.setParticipantName(participants);
		participants = Participant.setParticipantNum(participants);

		System.out.println("\nIt is a beautiful day for a skiing competition!\n"
				+ "We got some incredible participants today\n" + "What kind of race would you like to do?\n"
				+ "1. Individual Race\n" + "2. Mass Start\n" + "3. Back To Menu");

	boolean test = true;
	do {
		int option = SimonLib.intInput();
		
		/**
		 * Switch case that starts a race, depending if user chose individual start or mass race
		 * @param option
		 * */
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
		default:
			System.out.println("Invalid input.");
			break;
		}
	} while (test);

	}

	/**
	 * This method will start an individual race for each participant entered 
	 * and has the option to enter a custom interval in seconds
	 * for each participant. 
	 * When race has started, a menu will appear showing different things the user can choose to do. End race, set interval, check placements
	 * @param indRace
	 * */
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
				
				/**
				 * Switch case that checks what the user wishes to do during the race's duration.
				 * @param input
				 * */
				int input = SimonLib.intInput();
				switch (input) {
				case 1:
					LoopLogic.setInterval(participants, is1);
					break;
				case 2:
					LoopLogic.checkPlcmnt(participants, is1);
					break;
				case 3:
					is1.checkField();
					break;
				case 4:
					numOfRacersFinish = LoopLogic.finishSkier(participants, numOfRacersFinish);
					break;
				case 5:
					cont = finishRace(participants, numOfRacersFinish, cont, is1);
					break;
				default:
					System.out.println("Invalid input.");
					break;
				}

			} while (!cont);
			System.out.println(
					"Do you wish to:\n1. Go back to Main Menu\n2. Save race\n3. New race with new participants\n4. Exit");
			int choice = SimonLib.intInput();
			/**
			 * Switch case that checks what the user entered, if they wish to go back to main menu, save race, new race or exit
			 * @return choice
			 */
			switch (choice) {
			case 1:
				start();
				break;
			case 2:
				log(is1);
				break;
			case 3:
				competition();
				break;
			case 4:
				System.exit(0);
				break;
			default:
				System.out.println("Invalid input.");
				break;
			}
		}

	}

	/**
	 * This method will start a mass race; where all participants start at the same time
	 * for all participants entered. 
	 *When race has started, a menu will appear showing different things the user can choose to do. 
	 *End race, set interval, check placements
	 * @param massRace
	 * */
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
			/**
			 * Switch case that checks what the user wishes to do during the race's duration.
			 * @param input
			 * */
			switch (input) {
			case 1:
				LoopLogic.setInterval(participants, ms1);
				break;
			case 2:
				LoopLogic.checkPlcmnt(participants, ms1);
				break;
			case 3:
				ms1.checkField();
				break;
			case 4:
				numOfRacersFinish = LoopLogic.finishSkier(participants, numOfRacersFinish);
				break;
			case 5:
				cont = finishRace(participants, numOfRacersFinish, cont, ms1);
				break;
			default:
				System.out.println("Invalid input.");
				break;
			}

		} while (!cont);

		System.out.println(
				"Do you wish to:\n1. Go back to Main Menu\n2. Save race\n3. New race with new participants\n4. Exit");
		int choice = SimonLib.intInput();
		/**
		 * Switch case that checks what the user entered, if they wish to go back to main menu, save race, new race or exit
		 * @return choice
		 */
		switch (choice) {
		case 1:
			start();
			break;
		case 2:
			log(ms1);
			break;
		case 3:
			competition();
			break;
		case 4:
			System.exit(0);
			break;
		default:
			System.out.println("Invalid input.");
		}
	}

	/**
	 * This method will be called once the user decides to end the race, regardless if all participants have crossed the finish line.
	 * It will then return the placement of each participant, in the order from fastest time.
	 * Once results are printed, the user can wish to save the results, see log();
	 * 
	 * @param finishRace
	 * 	 * */
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
					"Do you wish to:\n1. Go back to Main Menu\n2. Save race\n3. New race with new participants\n4. Exit");
			int choice = SimonLib.intInput();
			/**
			 * This switch case is for if the user tries to do something; after the race has ended.
			 * Switch case that checks what the user entered, if they wish to go back to main menu, save race, new race or exit.
			 * @return choice
			 */
			switch (choice) {
			case 1:
				start();
				break;
			case 2:
				log(c1);
				break;
			case 3:
				competition();
				break;
			case 4:
				System.exit(0);
				break;
			default:
				System.out.println("Invalid input.");
				break;
			}
		}
		return cont;
	}

	/**
	 * Shows the result of a finished game.
	 *
	 */
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

	public void log(Competition competition) {
		Date date = new Date();
		String path = competition.getTypeSignature() + "-" + date.toString().substring(4, 19);

		if(fileManager.saveFile(competition.getArraylist(), "./log/" + path + ".log")) return;

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
				done = true;
				break; // EXIT OPTION
			default:
				System.out.println("Invalid input.");
				break;
			}
		}

		// TODO: Implement the loop.
	}
}
