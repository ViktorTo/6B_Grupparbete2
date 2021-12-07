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
	 * in the array. If startClock is null, the clock will be set in the method.
	 * 
	 * @param skiers
	 */

	public void startAllSkiers(Participant[] skiers) {

		if (sw.getStart() == null) {
			sw.startClock();
		}

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
			System.out.println(skiers[j].getParticipantNumber() + ", " + skiers[j].getFullName() + ", "
					+ skiers[j].getCountry() + " is starting");

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

	/**
	 * Method will find the entered number (if it is available in the participant
	 * array) sort a temporary array to find out where in the field the skier is
	 * located. When located, the method will print out placement and time between
	 * skiers.
	 * 
	 * @param skiNum
	 */

	public void checkPlacementet(int skiNum) {

		Participant[] temp = skiers.clone();

		int placement = 0;
		Arrays.sort(temp);
		String time1 = "";
		String time2 = "";
		boolean timeUp;
		boolean timeDown;

		for (int i = 0; i < temp.length; i++) {

			if (skiNum == temp[i].getParticipantNumber()) {
				placement = i + 1;
				if (i != 0 && i != temp.length - 1) {

					timeUp = (temp[i - 1].getStopWatch().getLatestInterval() != null) ? true : false;
					timeDown = (temp[i + 1].getStopWatch().getLatestInterval() != null) ? true : false;

					if (temp[i].getStopWatch().getLatestInterval() != null) {

						if (timeUp && timeDown) {
							Duration dur1 = Duration.between(temp[i].getStopWatch().getLatestInterval(),
									temp[i - 1].getStopWatch().getLatestInterval());
							long HH1 = Math.abs(dur1.toHoursPart());
							long MM1 = Math.abs(dur1.toMinutesPart());
							long SS1 = Math.abs(dur1.toSecondsPart());
							long MS1 = Math.abs(dur1.toMillisPart());
							time1 = String.format("%02d:%02d:%02d:%02d", HH1, MM1, SS1, MS1);

							Duration dur2 = Duration.between(temp[i].getStopWatch().getLatestInterval(),
									temp[i + 1].getStopWatch().getLatestInterval());
							long HH2 = dur2.toHoursPart();
							long MM2 = dur2.toMinutesPart();
							long SS2 = dur2.toSecondsPart();
							long MS2 = dur2.toMillisPart();
							time2 = String.format("%02d:%02d:%02d:%02d", HH2, MM2, SS2, MS2);

							System.out.println(temp[i].getParticipantNumber() + " " + temp[i].getFullName()
									+ " is number " + placement + " in the field and have " + time1
									+ " to the skier in front!");
							System.out.println("The time to the skier behind is " + time2);

						}else if(timeUp) {
							Duration dur1 = Duration.between(temp[i].getStopWatch().getLatestInterval(),
									temp[i - 1].getStopWatch().getLatestInterval());
							long HH1 = Math.abs(dur1.toHoursPart());
							long MM1 = Math.abs(dur1.toMinutesPart());
							long SS1 = Math.abs(dur1.toSecondsPart());
							long MS1 = Math.abs(dur1.toMillisPart());
							time1 = String.format("%02d:%02d:%02d:%02d", HH1, MM1, SS1, MS1);
							
							System.out.println(temp[i].getParticipantNumber() + " " + temp[i].getFullName()
									+ " is number " + placement + " in the field and have " + time1
									+ " to the skier in front!");
							System.out.println("No time for skier behind");
						}

					} else {
						System.out.println(
								temp[i].getParticipantNumber() + " " + temp[i].getFullName() + ": 00:00:00:00");
					}
					i = temp.length;

				} else if (i == 0) {

					timeDown = (temp[i + 1].getStopWatch().getLatestInterval() != null) ? true : false;

					if (temp[i].getStopWatch().getLatestInterval() != null && timeDown) {
						Duration dur1 = Duration.between(temp[i].getStopWatch().getLatestInterval(),
								temp[i + 1].getStopWatch().getLatestInterval());
						long HH = dur1.toHoursPart();
						long MM = dur1.toMinutesPart();
						long SS = dur1.toSecondsPart();
						long MS = dur1.toMillisPart();
						String time = String.format("%02d:%02d:%02d:%02d", HH, MM, SS, MS);

						System.out.println(
								temp[i].getParticipantNumber() + " " + temp[i].getFullName() + " is in the lead!");
						System.out.println(temp[i + 1].getParticipantNumber() + " " + temp[i + 1].getFullName()
								+ " is in second with " + time + " to first!");
					} else if (temp[i].getStopWatch().getLatestInterval() != null) {
						System.out.println(temp[i].getParticipantNumber() + " " + temp[i].getFullName() + " "
								+ temp[i].getStopWatch().getLatestInt());
						System.out.println("Nobody in second yet!");
					} else {

						System.out.println(
								temp[i].getParticipantNumber() + " " + temp[i].getFullName() + " has no time yet!");
					}
					i = temp.length;
				} else {

					if (temp[i].getStopWatch().getLatestInterval() != null) {

						Duration dur1 = Duration.between(temp[i].getStopWatch().getLatestInterval(),
								temp[i - 1].getStopWatch().getLatestInterval());
						long HH = Math.abs(dur1.toHoursPart());
						long MM = Math.abs(dur1.toMinutesPart());
						long SS = Math.abs(dur1.toSecondsPart());
						long MS = Math.abs(dur1.toMillisPart());
						String time = String.format("%02d:%02d:%02d:%02d", HH, MM, SS, MS);

						System.out.println(temp[i].getParticipantNumber() + " " + temp[i].getFullName() + " is last!");
						System.out.println(temp[i - 1].getParticipantNumber() + " " + temp[i - 1].getFullName()
								+ " is the skier in front, they have " + time + " between the two skiers");

					} else {
						System.out.println(
								temp[i].getParticipantNumber() + " " + temp[i].getFullName() + ": 00:00:00:00");
					}

					i = temp.length;
				}
			}

		}

		if (placement == 0)

		{
			System.out.println("Nobody with that number is in this competition!");

		}

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
