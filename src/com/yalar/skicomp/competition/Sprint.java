package com.yalar.skicomp.competition;

import com.yalar.skicomp.participant.Participant;

/**
 * 
 * @author Viktor Tornberg
 *
 */

public class Sprint extends Competition {

	private int distance;
	private int numOfParticipants;
	
	public Sprint(int distance, int numOfParticipants, Participant[] skiers) {
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
	
	/**
	 * Method that starts all skiers. The start time will be the same for all skiers
	 * in the array. If startClock is null, the clock will be set in the method.
	 * 
	 * @param skiers
	 */
	
	public void startAllSkiers(Participant[] skiers) {

		if (getSw().getStart() == null) {
			getSw().startClock();
		}

		for (int i = 0; i < skiers.length; i++) {
			skiers[i].getStopWatch().setStart(getSw().getStart());
		}

	}


	@Override
	public String getTypeSignature() {
		return "S";
	}
}
