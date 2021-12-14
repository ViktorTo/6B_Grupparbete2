package com.yalar.skicomp.competition;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import com.yalar.skicomp.participant.Participant;
import com.yalar.skicomp.stopwatch.StopWatch;

public abstract class Competition {

	private Participant[] skiers;
	private StopWatch sw = new StopWatch();

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
			a1.add(s.toString() + " " + s.getStopWatch().getDuration());
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
		List<Participant> temp = new ArrayList<>();
		boolean inList = false;
		int placement = 0;
		int index = 0;
		String time1;
		String time2;

		for (int i = 0; i < skiers.length; i++) {

			if (skiers[i].getStopWatch().getLatestInterval() != null) {
				temp.add(skiers[i]);
				inList = (skiers[i].getParticipantNumber() == partNum || inList) ? true : false;
			}
		}

		if (inList) {

			Collections.sort(temp);

			for (Participant p : temp) {
				if (p.getParticipantNumber() == partNum) {
					placement = temp.indexOf(p) + 1;
					index = temp.indexOf(p);
				}
			}

			if (index == 0) {

				if (temp.size() > 1) {

					String time = StopWatch.getDurationToString(temp.get(index).getStopWatch().getLatestInterval(),
							temp.get(index + 1).getStopWatch().getLatestInterval());

					System.out.println(temp.get(index).getParticipantNumber() + " " + temp.get(index).getFullName()
							+ " is in the lead!");
					System.out.println(temp.get(index + 1).getParticipantNumber() + " "
							+ temp.get(index + 1).getFullName() + " is in second with " + time + " to first!");
				} else {
					System.out.println(temp.get(index).getParticipantNumber() + " " + temp.get(index).getFullName()
							+ " is in the lead!");
					System.out.println(
							"First to reach the checkpoint, Time: " + temp.get(index).getStopWatch().getLatestInt());
				}

			} else if (index == skiers.length - 1) {

				String time = StopWatch.getDurationToString(temp.get(index).getStopWatch().getLatestInterval(),
						temp.get(index - 1).getStopWatch().getLatestInterval());

				System.out.println(
						temp.get(index).getParticipantNumber() + " " + temp.get(index).getFullName() + " is last!");
				System.out.println(temp.get(index - 1).getParticipantNumber() + " " + temp.get(index - 1).getFullName()
						+ " is the skier in front, they have " + time + " between the two skiers");

			} else {

				if (temp.size() > 2 && index != temp.size() - 1) {

					time1 = StopWatch.getDurationToString(temp.get(index).getStopWatch().getLatestInterval(),
							temp.get(index - 1).getStopWatch().getLatestInterval());
					time2 = StopWatch.getDurationToString(temp.get(index).getStopWatch().getLatestInterval(),
							temp.get(index + 1).getStopWatch().getLatestInterval());

					System.out.println(
							temp.get(index).getParticipantNumber() + " " + temp.get(index).getFullName() + " is number "
									+ placement + " in the field and have " + time1 + " to the skier in front!");
					System.out.println("The time to the skier behind is " + time2);

				} else {

					time1 = StopWatch.getDurationToString(temp.get(index).getStopWatch().getLatestInterval(),
							temp.get(index - 1).getStopWatch().getLatestInterval());

					System.out.println(
							temp.get(index).getParticipantNumber() + " " + temp.get(index).getFullName() + " is number "
									+ placement + " in the field and have " + time1 + " to the skier in front!");
					System.out.println("Latest skier to the checkpoint!");
				}
			}

		} else {
			System.out.println("Skier has not reached the checkpoint yet!");
		}

	}

	public void checkField() {

		List<Participant> interval = new ArrayList<>();
		List<Participant> finished = new ArrayList<>();
		boolean end = false;

		for (Participant s : skiers) {

			if (s.getStopWatch().getEnd() != null) {
				finished.add(s);
			} else if (s.getStopWatch().getLatestInterval() != null) {
				interval.add(s);
			}
		}

		Collections.sort(interval);
		Collections.sort(finished);

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
}
