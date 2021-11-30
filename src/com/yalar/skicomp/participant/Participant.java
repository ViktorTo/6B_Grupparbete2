package com.yalar.skicomp.participant;

import com.yalar.skicomp.stopwatch.StopWatch;

public class Participant extends Person implements Comparable<Participant> {

	// Private instance variables for each participant.
	private int participantNum = 1;
	private StopWatch sw;
	
	
	
	
	
	public Participant(String firstName, String lastName, String country) {
		super(firstName, lastName, country);
		this.sw = new StopWatch();
	}
	public int getParticipantNum() {
		return participantNum;
	}
	public void setParticipantNum(int participantNum) {
		this.participantNum = participantNum;
	}
	
	
	public StopWatch getStopWatch() {
		return sw;
	}
	
	
	
	
	
	@Override
	public String toString() {
		return "Participant "+ participantNum + super.toString() + ", " + " " + sw;
	}
	// A compareTo method that uses the StopWatch to get the time.
	@Override
	public int compareTo(Participant o) {
		
		return sw.compareTo(o.getStopWatch());
		
	}
	
	
	
	
	
}
