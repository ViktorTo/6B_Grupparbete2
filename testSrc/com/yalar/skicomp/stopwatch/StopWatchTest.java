package com.yalar.skicomp.stopwatch;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalTime;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class StopWatchTest {
	
	private static StopWatch sw1;
	private static StopWatch sw2;
	private static LocalTime lt1;
	private static LocalTime lt2;
	private static LocalTime lt3;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		
		sw1 = new StopWatch();
		sw2 = new StopWatch();
		lt1 = LocalTime.MIDNIGHT;
		lt2 = LocalTime.now();
		sw1.setStart(lt2);
		
	}

	@Test
	void testGetStart() {
		assertEquals(lt2, sw1.getStart());
	}


	@Test
	void testGetDurationToString() {
		assertEquals("00:00:00:00", StopWatch.getDurationToString(lt1, lt1));
	}

}
