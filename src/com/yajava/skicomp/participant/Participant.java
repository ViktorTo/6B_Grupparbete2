package com.yajava.skicomp.participant;

import com.yajava.skicomp.stopwatch.StopWatch;

public class Participant {

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
		return "[participantNum= " + participantNum + ", participant= " + participant + ", StopWatch= " + sw + "]";
	}
	
	
	
	
	
	
}
