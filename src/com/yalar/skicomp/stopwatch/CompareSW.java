package com.yalar.skicomp.stopwatch;

import java.util.Comparator;

import com.yalar.skicomp.participant.Participant;

public class CompareSW implements Comparator<Participant> {
	

	@Override
	public int compare(Participant o1, Participant o2) {
		int i = 0;
		if(o1.getStopWatch().getDuration().compareTo(o2.getStopWatch().getDuration()) == 1)  {
			i = 1;
		}else if(o1.getStopWatch().getDuration().compareTo(o2.getStopWatch().getDuration()) == -1) {
			i = -1;
		}
		return i;
	}

}


