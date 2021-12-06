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
	private LocalTime latestInterval;
	private LocalTime[] intervals = new LocalTime[10];
	private Duration totalTime;

	public StopWatch() {

	}

	public void setStart(LocalTime start) {
		this.start = start;
	}

	public LocalTime getStart() {
		return this.start;
	}
	
	public LocalTime getLatestInterval() {
		return this.latestInterval;
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

	/**
	 * Method that returns the start time as a String.
	 * 
	 * @return
	 */

	public String getStartToString() {
		LocalTime lt = LocalTime.MIDNIGHT;
		Duration dur = Duration.between(lt, start);
		long HH = dur.toHoursPart();
		long MM = dur.toMinutesPart();
		long SS = dur.toSecondsPart();
		long MS = dur.toMillisPart();
		return String.format("%02d:%02d:%02d:%02d", HH, MM, SS, MS);
	}

	/**
	 * Method that returns the latest interval as a String.
	 * 
	 * @return
	 */

	public String getLatestInt() {
		if (latestInterval != null) {
			Duration dur = Duration.between(start, latestInterval);
			long HH = dur.toHoursPart();
			long MM = dur.toMinutesPart();
			long SS = dur.toSecondsPart();
			long MS = dur.toMillisPart();
			return String.format("%02d:%02d:%02d:%02d", HH, MM, SS, MS);
		} else {
			return "No time found";
		}
	}

	/**
	 * Method that sets interval. Adds the LocalTime to the array intervals.
	 */

	public void setInterval() {
		for (int i = 0; i < intervals.length; i++) {
			if (this.intervals[i] == null) {
				latestInterval = LocalTime.now();
				this.intervals[i] = LocalTime.now();
				i = intervals.length;
			}
		}
	}

	/**
	 * Method that calculates time between start and first intervals. returns the
	 * result as String.
	 * 
	 * @return
	 */

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

	/**
	 * 
	 * Method that returns string with the time from start to end or start to now.
	 * Checks to see if end has value, if not create new Localtime.now and
	 * calculates time between. if end has value, calculates time between start and
	 * end.
	 * 
	 * @return
	 */

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

	/**
	 * Uses Durations compareTo-method. If getEnd is null, then the list will sort
	 * after latest interval. if getEnd has value, list will sort the end value.
	 */

	@Override
	public int compareTo(StopWatch o) {
		if (this.getEnd() != null) {
			return this.totalTime.compareTo(o.totalTime);
		} else if (latestInterval != null) {
			return this.latestInterval.compareTo(o.latestInterval);
		} else {
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
