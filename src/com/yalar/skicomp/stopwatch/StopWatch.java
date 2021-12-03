package com.yalar.skicomp.stopwatch;

import java.time.Duration;
import java.time.LocalTime;

/**
 * 
 * @author Viktor Tornberg
 *
 */

public class StopWatch implements Comparable<StopWatch> {

	private LocalTime start;
	private LocalTime end;
	private LocalTime[] intervals = new LocalTime[10];
	private Duration totalTime;
	private Duration latestInt;

	public StopWatch() {

	}

	public void setStart(LocalTime start) {
		this.start = start;
		latestInt = Duration.between(start, start);
	}

	public LocalTime getStart() {
		return this.start;
	}

	public String getStartToString() {
		LocalTime lt = LocalTime.MIDNIGHT;
		Duration dur = Duration.between(lt, start);
		long HH = dur.toHoursPart();
		long MM = dur.toMinutesPart();
		long SS = dur.toSecondsPart();
		long MS = dur.toMillisPart();
		return String.format("%02d:%02d:%02d:%02d", HH, MM, SS, MS);
	}
	
	public String getLatestInt() {
		if(latestInt != null) {
			long HH = latestInt.toHoursPart();
			long MM = latestInt.toMinutesPart();
			long SS = latestInt.toSecondsPart();
			long MS = latestInt.toMillisPart();
			return String.format("%02d:%02d:%02d:%02d", HH, MM, SS, MS);
		}else {
			return "No time found";
		}
	}

	public LocalTime getEnd() {
		return this.end;
	}

	public void startClock() {
		this.start = LocalTime.now();
	}

	public void endClock() {
		this.end = LocalTime.now();
		if (this.start != null) {
			totalTime = Duration.between(this.start, this.end);
		}
	}

	public void setInterval() {
		for (int i = 0; i < intervals.length; i++) {
			if (this.intervals[i] == null) {
				this.intervals[i] = LocalTime.now();
				latestInt = Duration.between(start, this.intervals[i]);
				i = intervals.length;
			}
		}
	}

	public String getIntervals() {
		String str = "";
		for (int i = 0; i < intervals.length; i++) {
			if (intervals[i] != null) {
				Duration dur = Duration.between(start, intervals[i]);
				long HH = dur.toHoursPart();
				long MM = dur.toMinutesPart();
				long SS = dur.toSecondsPart();
				long MS = dur.toMillisPart();
				str += String.format("%02d:%02d:%02d:%02d", HH, MM, SS, MS) + "\n";
			}
		}
		return str;
	}

	// Method that returns string with the time from start to end or start to now.
	// Checks to see if end has value, if not create new Localtime.now and
	// calculates time between.
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

	// Uses Durations compareTo-method.
	// If getEnd is null, then the list will sort after latest interval.
	// if getEnd has value, list will sort the end value.

	@Override
	public int compareTo(StopWatch o) {
		if (this.getEnd() != null) {
			return this.totalTime.compareTo(o.totalTime);
		} else if(latestInt != null){
			return this.latestInt.compareTo(o.latestInt);
		}else {
			System.out.println("Nothing to compare!");
			return 2;
		}

	}

	@Override
	public String toString() {
		if (start == null) {
			return "Stopwatch [Start: " + "00:00:00" + " - End: " + "00:00:00" + "]";
		} else if (end == null) {
			return "Stopwatch [Start: " + start + " - End: " + "00:00:00" + "]";
		}
		return "Stopwatch [Start: " + start + " - End: " + end + "]";
	}

}
