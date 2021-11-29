package com.yalar.skicomp.participant;

import com.yalar.skicomp.stopwatch.StopWatch;

public class Participant implements Comparable<Participant> {

	private int participantNum;
	private Person participant;
	private StopWatch sw;
	
	
	public Participant(int participantNum, Person participant) {
		super();
		this.participantNum = participantNum;
		this.participant = participant;
		this.sw = new StopWatch();
	}
	public int getParticipantNum() {
		return participantNum;
	}
	public void setParticipantNum(int participantNum) {
		this.participantNum = participantNum;
	}
	public Person getParticipant() {
		return participant;
	}
	public void setParticipant(Person participant) {
		this.participant = participant;
	} 
	
	public StopWatch getStopWatch() {
		return sw;
	}
	@Override
	public String toString() {
		return participant + ", StopWatch= " + sw;
	}
	@Override
	public int compareTo(Participant o) {
		
		return sw.compareTo(o.getStopWatch());
		
	}
	
	
	
	
	
}
