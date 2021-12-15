package com.yalar.skicomp.participant;

import com.yalar.skicomp.stopwatch.StopWatch;
import com.yalar.skicomp.io.SimonLib;
public class Participant extends Person implements Comparable<Participant> {

	// Private instance variables for each participant.
	private int participantNumber;

	public int getParticipantNumber() {
		return participantNumber;
	}

	public void setParticipantNumber(int participantNumber) {
		this.participantNumber = participantNumber;
	}

	private StopWatch sw;
	private boolean finished;

	public boolean isFinished() {
		return finished;
	}

	public void setFinished(boolean finished) {
		this.finished = finished;
	}

	public Participant(String firstName, String lastName, String country) {
		super(firstName, lastName, country);
		this.sw = new StopWatch();
		this.finished = false;
	}

	public static Participant[] setParticipantNum(Participant[] participants) {

		// Array för att spara slumpade nummer.

		int[] nums = new int[participants.length];

		// Yttre loop för att kolla placering i participant array

		for (int i = 0; i < participants.length; i++) {

			boolean cont = true;
			// while loop ifall vi beh�ver slumpa ett nytt nummer p� samma plats i arrayen
			while (cont) {
				nums[i] = (int) (Math.random() * 100);

				cont = false;
				// For loop får att j�mf�ra de två arrayerna ifall vi hittar ett tal som är
				// lika.
				for (int j = 0; j < participants.length; j++) {
					// om samma nummer inträffar så körs loopen igen.

					if (participants[j].getParticipantNumber() == nums[i]) {
						j = participants.length;
						cont = true;
						// om talet i participants är noll, så avslutas kontrollen.
					} else if (participants[j].getParticipantNumber() == 0) {
						j = participants.length;
					}
				}
			}

			// Nummer förs in på index av deltagare.
			participants[i].setParticipantNumber(nums[i]);

		}

		return participants;

	}

	public static Participant[] setParticipantName(Participant[] participants) {
		for (int i = 0; i < participants.length; i++) {
			System.out.print("First name of participant #" + (i + 1) + ": ");
			String firstName = SimonLib.stringInput();
			System.out.print("Last name of participant #" + (i + 1) + ": ");
			String lastName = SimonLib.stringInput();
			System.out.print("Country of participant #" + (i + 1) + ": ");
			String country = SimonLib.stringInput();

			participants[i] = new Participant(firstName, lastName, country);


		}
		return participants;
	}

	public StopWatch getStopWatch() {
		return sw;
	}

	@Override
	public String toString() {
		return "Participant " + participantNumber + " " + super.toString() + sw;
	}

	// A compareTo method that uses the StopWatch to get the time.
	@Override
	public int compareTo(Participant o) {

		return sw.compareTo(o.getStopWatch());

	}

}
