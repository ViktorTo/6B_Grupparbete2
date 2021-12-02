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
	
	
	
  

}
