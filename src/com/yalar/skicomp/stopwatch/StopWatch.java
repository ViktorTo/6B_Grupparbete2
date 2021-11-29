package com.yalar.skicomp.stopwatch;

import java.time.Duration;
import java.time.LocalTime;

public class StopWatch implements Comparable<StopWatch>{
	
	private LocalTime start;
	private LocalTime end;
	private LocalTime[] intervalls = new LocalTime[10];
	private Duration totalTime;
	
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
		totalTime = Duration.between(this.start, this.end);
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
				long HH = dur.toHoursPart();
				long MM = dur.toMinutesPart();
				long SS = dur.toSecondsPart();
				long MS = dur.toMillisPart();
				str += String.format("%02d:%02d:%02d:%02d", HH, MM, SS, MS) + "\n";
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
		totalTime = Duration.between(start, temp);
		long HH = totalTime.toHours();
		long MM = totalTime.toMinutesPart();
		long SS = totalTime.toSecondsPart();
		long MS = totalTime.toMillisPart();
		String timeBetween = String.format("%02d:%02d:%02d:%02d", HH, MM, SS, MS);
		temp = null;
		return timeBetween;
	}
 
	@Override
	public int compareTo(StopWatch o) {
		return this.totalTime.compareTo(o.totalTime);
		
	}

}


