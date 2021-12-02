package com.yalar.skicomp.participant;


import java.util.Random;


import com.yalar.skicomp.stopwatch.StopWatch;

public class Participant extends Person implements Comparable<Participant> {

//	
//	private static Random random = null;
//	
//	int min = 1;
	int max = 4;

	// Private instance variables for each participant.
	private int participantNumber;

	public int getParticipantNumber() {
		return participantNumber;
	}

	public void setParticipantNumber(int participantNumber) {
		this.participantNumber = participantNumber;
	}

	private StopWatch sw;


	public Participant(String firstName, String lastName, String country) {
		super(firstName, lastName, country);
		this.sw = new StopWatch();
	}

	public static Participant[] setParticipantNum(Participant[] participants) {
//		while (rnd.size() < participants.length) {
//
//			/*
//			 * The add() method of a Set is a boolean and if it returns true, then this
//			 * element is not inside the Set yet and will be added to it, if it returns
//			 * false, the element already exists and it will not be added to the Set again
//			 */
//			rnd.add((int) (Math.random() * 100));
//		}

		// Array f�r att spara slumpade nummer.

		int[] nums = new int[participants.length];

		//Yttre loop f�r att kolla placering i participant array

		for (int i = 0; i < participants.length; i++) {

			boolean cont = true;
			// while loop ifall vi beh�ver slumpa ett nytt nummer p� samma plats i arrayen
			while(cont) {
				nums[i] = (int) (Math.random() * 100);
				
				cont = false;
				//For loop f�r att j�mf�ra de tv� arrayerna ifall vi hittar ett tal som �r lika.
				for(int j = 0; j < participants.length; j++) {
					// om samma nummer intr�ffar k�r vi while loopen igen.

					if(participants[j].getParticipantNumber() == nums[i]) {
						j = participants.length;
						cont = true;
						// om talet i participants �r noll, s� avslutas kontrollen.
					} else if (participants[j].getParticipantNumber() == 0) {
						j = participants.length;
					}
				}
			}


			//Nummer f�rs in p� index av deltagare.
			participants[i].setParticipantNumber(nums[i]);



		}

		return participants;

	}

	public StopWatch getStopWatch() {
		return sw;
	}

	@Override
	public String toString() {
		return "Participant " + participantNumber + " " + super.toString() + ", " + " " + sw;
	}

	// A compareTo method that uses the StopWatch to get the time.
	@Override
	public int compareTo(Participant o) {

		return sw.compareTo(o.getStopWatch());

	}

}
