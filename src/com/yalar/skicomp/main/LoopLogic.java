package com.yalar.skicomp.main;

import com.yalar.skicomp.competition.Competition;
import com.yalar.skicomp.io.SimonLib;
import com.yalar.skicomp.participant.Participant;

public class LoopLogic {
	
	public static void printParticipants(Participant[] participants) {
		System.out.print("Choose a participant's number: ");
		for (int i = 0; i < participants.length; i++) {
			System.out.print(participants[i].getParticipantNumber() + ", ");

		}
	}
	
	public static void checkPlcmnt(Participant[] participants, Competition c1) {
		LoopLogic.printParticipants(participants);

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
	
	public static void setInterval(Participant[] participants, Competition c) {
		LoopLogic.printParticipants(participants);

		int num = SimonLib.intInput();
		if (c.isInComp(num)) {
			for (int i = 0; i < participants.length; i++) {
				if (num == participants[i].getParticipantNumber()) {
					participants[i].getStopWatch().setInterval();
					System.out.println(participants[i]);
					System.out.println(participants[i].getStopWatch().getLatestInt());
					i = participants.length;
				}
			}
		}
	}
	
	public static int finishSkier(Participant[] participants, int numOfRacersFinish) {
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
}
