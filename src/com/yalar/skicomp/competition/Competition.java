package com.yalar.skicomp.competition;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;

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

	public ArrayList<String> getArraylist() {

		ArrayList<String> a1 = new ArrayList<String>();

		for (int i = 0; i < skiers.length; i++) {
			a1.add(skiers[i].toString() + " " + skiers[i].getStopWatch().getDuration());
		}

		return a1;

	}

	/**
	 * Method that starts all skiers. The start time will be the same for all skiers
	 * in the array.
	 * 
	 * @param skiers
	 */

	public void startAllSkiers(Participant[] skiers) {

		for (int i = 0; i < skiers.length; i++) {
			skiers[i].getStopWatch().setStart(sw.getStart());
		}

	}

	/**
	 * Method for induvidual start. Skiers will start with intervals, i is seconds.
	 * 
	 * @param skiers
	 * @param i
	 */

	public void startSkierIntervall(Participant[] skiers, int i) {

		for (int j = 0; j < skiers.length; j++) {

			skiers[j].getStopWatch().startClock();
			System.out.println(skiers[j].getFirstName() + skiers[j].getParticipantNumber() + " is starting");

			try {
				Thread.sleep(i * 1000);
			} catch (InterruptedException e) {

				e.printStackTrace();
			}

		}

	}

	public String getDuration(Participant skier) {

		if (skier.getStopWatch().getEnd() != null) {

			Duration dur = Duration.between(this.sw.getStart(), skier.getStopWatch().getEnd());
			long HH = dur.toHoursPart();
			long MM = dur.toMinutesPart();
			long SS = dur.toSecondsPart();
			long MS = dur.toMillisPart();
			return String.format("%02d:%02d:%02d:%02d", HH, MM, SS, MS);
		}

		return "Skier have not finished";

	}

	public String getCompTime() {

		if (sw.getEnd() != null) {

			Duration dur = Duration.between(this.sw.getStart(), this.sw.getEnd());
			long HH = dur.toHoursPart();
			long MM = dur.toMinutesPart();
			long SS = dur.toSecondsPart();
			long MS = dur.toMillisPart();
			return String.format("%02d:%02d:%02d:%02d", HH, MM, SS, MS);

		}

		return "Competition has not ended";

	}

}
