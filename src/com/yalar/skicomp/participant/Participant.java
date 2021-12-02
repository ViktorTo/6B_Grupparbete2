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

		// Array för att spara slumpade nummer.

		int[] nums = new int[participants.length];

		//Yttre loop för att kolla placering i participant array

		for (int i = 0; i < participants.length; i++) {

			boolean cont = true;
			// while loop ifall vi behöver slumpa ett nytt nummer på samma plats i arrayen
			while(cont) {
				nums[i] = (int) (Math.random() * 100);
				
				cont = false;
				//For loop för att jämföra de två arrayerna ifall vi hittar ett tal som är lika.
				for(int j = 0; j < participants.length; j++) {
					// om samma nummer inträffar kör vi while loopen igen.

					if(participants[j].getParticipantNumber() == nums[i]) {
						j = participants.length;
						cont = true;
						// om talet i participants är noll, så avslutas kontrollen.
					} else if (participants[j].getParticipantNumber() == 0) {
						j = participants.length;
					}
				}
			}


			//Nummer förs in på index av deltagare.
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
