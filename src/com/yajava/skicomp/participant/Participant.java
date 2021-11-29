package com.yajava.skicomp.participant;

public class Participant {

	private int participantNum;
	private Person participant;
	
	
	
	public Participant(int participantNum, Person participant) {
		super();
		this.participantNum = participantNum;
		this.participant = participant;
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
	@Override
	public String toString() {
		return "[participantNum=" + participantNum + ", participant=" + participant + "]";
	}
	
	
	
	
}
