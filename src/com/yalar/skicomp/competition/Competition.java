package com.yalar.skicomp.competition;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import com.yalar.skicomp.participant.Participant;
import com.yalar.skicomp.stopwatch.CompareSW;
import com.yalar.skicomp.stopwatch.StopWatch;

public abstract class Competition {

	private Participant[] skiers;
	private StopWatch sw = new StopWatch();

	public abstract String getTypeSignature();

	public Participant[] getSkiers() {
		return skiers;
	}

	public void setSkiers(Participant[] skiers) {
		this.skiers = skiers;
	}

	public void startComp() {
		sw.startClock();

	}

	public void endComp() {
		sw.endClock();
	}

	public StopWatch getSw() {
		return this.sw;
	}

	public void endSkierTime(Participant skier) {
		skier.getStopWatch().endClock();
	}

	public void sortResults() {
		Arrays.sort(this.skiers);
	}

	/**
	 * Convert Array of skiers to ArrayList.
	 * 
	 * @return
	 */

	public List<String> getArraylist() {

		List<String> a1 = new ArrayList<>();

		for (Participant s : skiers) {
			a1.add(s.toString() + " " + s.getStopWatch().getDurationToString());
		}

		return a1;

	}

	public String getDuration(Participant skier) {

		if (skier.getStopWatch().getEnd() != null) {

			return StopWatch.getDurationToString(this.sw.getStart(), skier.getStopWatch().getEnd());
		}

		return "Skier have not finished";

	}

	/**
	 * Method will find the entered number (if it is available in the participant
	 * array) sort a temporary arraylist to find out where in the field the skier is
	 * located. When located, the method will print out placement and time between
	 * skiers.
	 * 
	 * @param skiNum
	 */

	public void checkPlacement(int partNum) {
		List<Participant> temp = getAllIntervals();
		int index = getSkierPos(partNum);
		int placement = index + 1;
		String empty = "";
		if (index != -1) {
			String time1 = (index != 0)
					? StopWatch.getDurationToString(temp.get(index).getStopWatch().getLatestInterval(),
							temp.get(index - 1).getStopWatch().getLatestInterval())
					: "";
			String time2 = (index < temp.size() - 1)
					? StopWatch.getDurationToString(temp.get(index).getStopWatch().getLatestInterval(),
							temp.get(index + 1).getStopWatch().getLatestInterval())
					: "";

			if (index == 0) {
				System.out.println(temp.get(index).getFullName() + " is in the lead");
				if (!empty.equals(time2)) {
					System.out.println(temp.get(index + 1).getFullName() + " is in second with " + time2 + " to first");
				}
			} else if (index > 0 && index < skiers.length - 1) {
				System.out.println(temp.get(index).getFullName() + " is number " + placement + " in the field");
				System.out.println(temp.get(index - 1).getFullName() + " is in front with " + time1);
				if (!empty.equals(time2)) {
					System.out.println(temp.get(index + 1).getFullName() + " is behind with " + time2);
				}
			} else if (index == skiers.length - 1) {
				System.out.println(temp.get(index).getFullName() + " is in last");
				System.out.println(temp.get(index - 1).getFullName() + " is the skier in front with " + time1);
			}
		}
	}
	
	/**
	 * Method checks if skier is finished, have interval or nothing at all.
	 * After checking and sorting, it prints to console.
	 */

	public void checkField() {

		List<Participant> interval = new ArrayList<>();
		List<Participant> finished = new ArrayList<>();

		for (Participant s : skiers) {

			if (s.getStopWatch().getEnd() != null) {
				finished.add(s);
			} else if (s.getStopWatch().getLatestInterval() != null) {
				interval.add(s);
			}
		}

		Collections.sort(interval, new CompareSW());
		Collections.sort(finished, new CompareSW());

		for (Participant p : finished) {
			System.out.println(
					p.getParticipantNumber() + " " + p.getFullName() + ": " + StopWatch.getDurationToString(p.getStopWatch().getStart(), p.getStopWatch().getEnd()) + " Finished");
		}

		for (Participant p : interval) {
			System.out
					.println(p.getParticipantNumber() + " " + p.getFullName() + ": " + p.getStopWatch().getLatestInt());
		}

		for (int i = 0; i < skiers.length; i++) {

			if (!finished.contains(skiers[i]) && !interval.contains(skiers[i])) {
				System.out.println(skiers[i].getParticipantNumber() + " " + skiers[i].getFullName() + ": NO TIME");
			}
		}
	}

	/**
	 * Checks to see if Competition "endclock" is set.
	 * @return boolean
	 */
	
	public boolean isCompDone() {
		for (Participant p : skiers) {
			if (p.getStopWatch().getEnd() == null) {
				return false;
			}
		}
		return true;
	}

	public String getCompTime() {

		if (sw.getEnd() != null) {
			return StopWatch.getDurationToString(this.sw.getStart(), this.sw.getEnd());
		}
		return "Competition has not ended";
	}
	
	/**
	 * Check if Participant number is infact a skier in the race.
	 * @param partNum - Integer partNum
	 * @return boolean
	 */
	
	public boolean isInComp(int partNum) {
		for (Participant p : skiers) {
			if(p.getParticipantNumber() == partNum) {
				return true;
			}
		}
		System.out.println("No skier in competition with that number!");
		return false;
	}
	
	/**
	 * Check if skier have reached the checkpoint.
	 * @param partNum - Integer partNum
	 * @return boolean
	 */
	
	public boolean gotIntTime(int partNum) {
		List<Participant> temp = getAllIntervals();
		return temp.stream().anyMatch(e -> e.getParticipantNumber() == partNum);
	}
	
	/**
	 * Method returns the index of inputed skier.
	 * @param partNum - Integer partNum
	 * @return Integer
	 */
	
	public int getSkierPos(int partNum) {
		
			List<Participant> temp = getAllIntervals();
			for (Participant p : temp) {
				if (p.getParticipantNumber() == partNum) {
					return temp.indexOf(p);
				}
			}
			System.out.println("Skier has not reached a checkpoint yet!");
			return -1;
		}
	
	/**
	 * Method returns a list of all skiers with intervals.
	 * @return List<Participant>
	 */
	
	public List<Participant> getAllIntervals() {
		List<Participant> temp = new ArrayList<>();
		
		for (int i = 0; i < skiers.length; i++) {
			if (skiers[i].getStopWatch().getLatestInterval() != null) {
				temp.add(skiers[i]);
			}
		}
		Collections.sort(temp, new CompareSW());
		return temp;
	}
	
	/**
	 * Method returns a list of all skiers finished.
	 * @return List<Participant>
	 */
	
	public List<Participant> getAllFinishes() {
		List<Participant> temp = new ArrayList<>();
		
		for (int i = 0; i < skiers.length; i++) {
			if (skiers[i].getStopWatch().getEnd() != null) {
				temp.add(skiers[i]);
			}
		}
		Collections.sort(temp, new CompareSW());
		return temp;
	}
}
