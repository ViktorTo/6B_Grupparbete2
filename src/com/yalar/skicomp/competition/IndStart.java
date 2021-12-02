package com.yalar.skicomp.competition;

import com.yalar.skicomp.participant.Participant;

/**
 * 
 * @author Viktor Tornberg
 *
 */

public class IndStart extends Competition {

	private int distance;
	private int numOfParticipants;

	public IndStart(int distance, int numOfParticipants, Participant[] skiers) {
		this.distance = distance;
		this.numOfParticipants = numOfParticipants;
		this.setSkiers(skiers);
	}

	public int getDistance() {
		return distance;
	}

	public void setDistance(int distance) {
		this.distance = distance;
	}

	public int getNumOfParticipants() {
		return numOfParticipants;
	}

	public void setNumOfParticipants(int numOfParticipants) {
		this.numOfParticipants = numOfParticipants;
	}
	
	public Participant[] getSkierList() {
		return this.getSkiers();
	}

	public void startSkierTime(Participant skier) {
		skier.getStopWatch().startClock();
	}

	public void startSkierIntervall(Participant[] skiers, int i) {

		for (int j = 0; j < skiers.length; j++) {

			skiers[j].getStopWatch().startClock();
			System.out.println(skiers[j].getFirstName() + skiers[j].getParticipantNumber() + " is starting");
			
			try {
				Thread.sleep(i * 1000);
			} catch (InterruptedException e) {

				e.printStackTrace();
			}

		}

	}

	public void endSkierTime(Participant skier) {
		skier.getStopWatch().endClock();
	}

	public String getDuration(Participant skier) {

		return skier.getStopWatch().getDuration();

	}

}