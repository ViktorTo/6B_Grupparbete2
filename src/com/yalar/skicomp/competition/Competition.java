package com.yalar.skicomp.competition;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

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
	 * array) sort a temporary arraylist to find out where in the field the skier is
	 * located. When located, the method will print out placement and time between
	 * skiers.
	 * 
	 * @param skiNum
	 */
	
	public void checkPlacement(int partNum) {
		ArrayList<Participant> temp = new ArrayList<Participant>();
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
				
				if(temp.size() > 1) {
				
				Duration dur1 = Duration.between(temp.get(index).getStopWatch().getLatestInterval(),
						temp.get(index + 1).getStopWatch().getLatestInterval());
				long HH = dur1.toHoursPart();
				long MM = dur1.toMinutesPart();
				long SS = dur1.toSecondsPart();
				long MS = dur1.toMillisPart();
				String time = String.format("%02d:%02d:%02d:%02d", HH, MM, SS, MS);

				System.out.println(temp.get(index).getParticipantNumber() + " " + temp.get(index).getFullName()
						+ " is in the lead!");
				System.out.println(temp.get(index + 1).getParticipantNumber() + " "
						+ temp.get(index + 1).getFullName() + " is in second with " + time + " to first!");
				}else {
					System.out.println(temp.get(index).getParticipantNumber() + " " + temp.get(index).getFullName()
							+ " is in the lead!");
					System.out.println("First to reach the checkpoint, Time: " + temp.get(index).getStopWatch().getLatestInt());
				}

			} else if (index == skiers.length - 1) {
				Duration dur1 = Duration.between(temp.get(index).getStopWatch().getLatestInterval(),
						temp.get(index - 1).getStopWatch().getLatestInterval());
				long HH = Math.abs(dur1.toHoursPart());
				long MM = Math.abs(dur1.toMinutesPart());
				long SS = Math.abs(dur1.toSecondsPart());
				long MS = Math.abs(dur1.toMillisPart());
				String time = String.format("%02d:%02d:%02d:%02d", HH, MM, SS, MS);

				System.out.println(temp.get(index).getParticipantNumber() + " " + temp.get(index).getFullName()
						+ " is last!");
				System.out.println(
						temp.get(index - 1).getParticipantNumber() + " " + temp.get(index - 1).getFullName()
								+ " is the skier in front, they have " + time + " between the two skiers");

			} else {
				
				if(temp.size() > 2 && index != temp.size() - 1) {
				Duration dur1 = Duration.between(temp.get(index).getStopWatch().getLatestInterval(),
						temp.get(index - 1).getStopWatch().getLatestInterval());
				long HH1 = Math.abs(dur1.toHoursPart());
				long MM1 = Math.abs(dur1.toMinutesPart());
				long SS1 = Math.abs(dur1.toSecondsPart());
				long MS1 = Math.abs(dur1.toMillisPart());
				time1 = String.format("%02d:%02d:%02d:%02d", HH1, MM1, SS1, MS1);

				Duration dur2 = Duration.between(temp.get(index).getStopWatch().getLatestInterval(),
						temp.get(index + 1).getStopWatch().getLatestInterval());
				long HH2 = dur2.toHoursPart();
				long MM2 = dur2.toMinutesPart();
				long SS2 = dur2.toSecondsPart();
				long MS2 = dur2.toMillisPart();
				time2 = String.format("%02d:%02d:%02d:%02d", HH2, MM2, SS2, MS2);

				System.out.println(temp.get(index).getParticipantNumber() + " " + temp.get(index).getFullName()
						+ " is number " + placement + " in the field and have " + time1 + " to the skier in front!");
				System.out.println("The time to the skier behind is " + time2);
				
				}else {
					
					Duration dur1 = Duration.between(temp.get(index).getStopWatch().getLatestInterval(),
							temp.get(index - 1).getStopWatch().getLatestInterval());
					long HH1 = Math.abs(dur1.toHoursPart());
					long MM1 = Math.abs(dur1.toMinutesPart());
					long SS1 = Math.abs(dur1.toSecondsPart());
					long MS1 = Math.abs(dur1.toMillisPart());
					time1 = String.format("%02d:%02d:%02d:%02d", HH1, MM1, SS1, MS1);
					
					System.out.println(temp.get(index).getParticipantNumber() + " " + temp.get(index).getFullName()
							+ " is number " + placement + " in the field and have " + time1 + " to the skier in front!");
					System.out.println("Latest skier to the checkpoint!");
				}
			}

		} else {
			System.out.println("Skier has not reached the checkpoint yet!");
		}

	}

	public void checkField() {
		
		ArrayList<Participant> temp = new ArrayList<Participant>();
		
		for (int i = 0; i < skiers.length; i++) {
			
			if(skiers[i].getStopWatch().getLatestInterval() != null) {
				temp.add(skiers[i]);
			}
		}
		
		Collections.sort(temp);
		
		for (Participant p : temp) {
			System.out.println(p.getParticipantNumber() + " " + p.getFullName() + ": " + p.getStopWatch().getLatestInt());
		}
		
		for (int i = 0; i < skiers.length; i++) {
			
			if(!(temp.contains(skiers[i]))) {
				System.out.println(skiers[i].getParticipantNumber() + " " + skiers[i].getFullName() + ": NO TIME");
			}
			
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
