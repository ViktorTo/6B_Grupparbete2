package com.yajava.skicomp.stopwatch;

import java.time.Duration;
import java.time.LocalTime;

public class StopWatch {
	
	private LocalTime start;
	private LocalTime end;
	private LocalTime[] intervalls = new LocalTime[10];
	
	public StopWatch() {
		
	}
	
	public LocalTime getStart() {
		return this.start;
	}
	
	public LocalTime getEnd() {
		return this.end;
	}
	
	public void startClock() {
		this.start = LocalTime.now();
	}
	
	public void endClock() {
		this.end = LocalTime.now();
	}
	
	public void setIntervall() {
		for (int i = 0; i < intervalls.length; i++) {
			if (this.intervalls[i] == null) {
				this.intervalls[i] = LocalTime.now();
				i = intervalls.length;
			}
		}
	}
	
	public String getIntervall() {
		String str = "";
		for (int i = 0; i < intervalls.length; i++) {
			if (intervalls[i] != null) {
				Duration dur = Duration.between(start, intervalls[i]);
				long HH = dur.toHours();
				long MM = dur.toMinutes();
				long SS = dur.toSeconds();
				str += String.format("%02d:%02d:%02d", HH, MM, SS) + "\n";
			}
		}
		return str;
	}
	
	@Override
	public String toString() {
		return "Stopwatch [start = " + start + ", end = " + end + "]";
	}
	
	//Method that returns string with the time from start to end or start to now.
	//Checks to see if end has value, if not create new Localtime.now and calculates time between.
	// if end has value, calculates time between start and end.
	
	public String getDuration() {
		LocalTime temp;
		temp = (end == null) ? LocalTime.now() : end;
		Duration dur = Duration.between(start, temp);
		long HH = dur.toHours();
		long MM = dur.toMinutesPart();
		long SS = dur.toSecondsPart();
		String timeBetween = String.format("%02d:%02d:%02d", HH, MM, SS);
		temp = null;
		return timeBetween;
	}

}


