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

		return getDurationToString(LocalTime.MIDNIGHT, start);
	}

	/**
	 * Method that returns the latest interval as a String.
	 * 
	 * @return
	 */

	public String getLatestInt() {
		if (latestInterval != null) {
			return getDurationToString(start, latestInterval);
		} else {
			return "No time found";
		}
	}

	/**
	 * Method that sets interval. Adds the LocalTime to the array intervals.
	 */

	public void setInterval() {
		boolean b = (this.getEnd() == null);
		if (b) {
			for (int i = 0; i < intervals.length; i++) {
				if (this.intervals[i] == null) {
					latestInterval = LocalTime.now();
					this.intervals[i] = LocalTime.now();
					i = intervals.length;
				}
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
				str = getDurationToString(start, intervals[i]);
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

	public String getDurationToString() {
		LocalTime temp;
		temp = (end == null) ? LocalTime.now() : end;
		String timeBetween = getDurationToString(start, temp);
		temp = null;
		return timeBetween;
	}
	
	public Duration getDuration() {
		if(this.end == null) {
		Duration dur = Duration.between(start, latestInterval);
		return dur;
		}else {
			Duration dur = Duration.between(start, end);
			return dur;
		}
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
			if (o.latestInterval != null) {
				return this.latestInterval.compareTo(o.latestInterval);
			} else {
				return 0;
			}

		} else {
			return -1;
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

	/**
	 * 
	 * Static method takes two LocaTimes, checks duration and returns a formatted
	 * string.
	 * 
	 * @param t1 - LocalTime 1
	 * @param t2 - LocalTime 2
	 * @return
	 */

	public static String getDurationToString(LocalTime t1, LocalTime t2) {
		Duration dur = Duration.between(t1, t2);
		long HH = Math.abs(dur.toHoursPart());
		long MM = Math.abs(dur.toMinutesPart());
		long SS = Math.abs(dur.toSecondsPart());
		long MS = Math.abs(dur.toMillisPart());
		return String.format("%02d:%02d:%02d:%02d", HH, MM, SS, MS);
	}
	

}
