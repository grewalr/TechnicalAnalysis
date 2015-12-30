package com.rickyg.ta.studies;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.rickyg.ta.domain.Quote;

public class MovingAverageTest
{
	/**
	 * Logger for this class
	 */
	private static final Log LOG = LogFactory.getLog(MovingAverageTest.class);

	private List<Quote> list;
	
	@Before
	public void setUp() throws Exception
	{
		list = new ArrayList<Quote>();
		Quote q1 = new Quote();
		Quote q2 = new Quote();
		Quote q3 = new Quote();
		Quote q4 = new Quote();
		Quote q5 = new Quote();
		Quote q6 = new Quote();
		Quote q7 = new Quote();
		Quote q8 = new Quote();
		Quote q9 = new Quote();
		Quote q10 = new Quote();
		Quote q11 = new Quote();
		Quote q12 = new Quote();
		Quote q13 = new Quote();
		Quote q14 = new Quote();
		Quote q15 = new Quote();
		Quote q16 = new Quote();
		Quote q17 = new Quote();
		Quote q18 = new Quote();
		Quote q19 = new Quote();
		Quote q20 = new Quote();
		Quote q21 = new Quote();
		Quote q22 = new Quote();
		Quote q23 = new Quote();
		Quote q24 = new Quote();
		Quote q25 = new Quote();
		Quote q26 = new Quote();
		Quote q27 = new Quote();
		Quote q28 = new Quote();
		Quote q29 = new Quote();
		Quote q30 = new Quote();
		Quote q31 = new Quote();
		Quote q32 = new Quote();
		Quote q33 = new Quote();
		Quote q34 = new Quote();
		Quote q35 = new Quote();
		Quote q36 = new Quote();
		Quote q37 = new Quote();
		Quote q38 = new Quote();
		Quote q39 = new Quote();
		Quote q40 = new Quote();
		Quote q41 = new Quote();
		Quote q42 = new Quote();
		Quote q43 = new Quote();
		Quote q44 = new Quote();
		Quote q45 = new Quote();
		Quote q46 = new Quote();
		Quote q47 = new Quote();
		Quote q48 = new Quote();
		Quote q49 = new Quote();
		Quote q50 = new Quote();

		// From MT4
		q1.setClose(97.87);
		q2.setClose(97.96);
		q3.setClose(97.90);
		q4.setClose(97.95);
		q5.setClose(97.87);
		q6.setClose(97.84);
		q7.setClose(97.94);
		q8.setClose(97.93);
		q9.setClose(97.99);
		q10.setClose(98.01);
		q11.setClose(97.98);
		q12.setClose(97.95);
		q13.setClose(97.95);
		q14.setClose(97.86);
		q15.setClose(97.94);
		q16.setClose(97.89);
		q17.setClose(97.85);
		q18.setClose(97.86);
		q19.setClose(97.88);
		q20.setClose(97.96);
		q21.setClose(97.96);
		q22.setClose(97.98);
		q23.setClose(97.95);
		q24.setClose(97.85);
		q25.setClose(97.86);
		q26.setClose(97.95);
		q27.setClose(97.95);
		q28.setClose(97.84);
		q29.setClose(97.86);
		q30.setClose(97.90);
		q31.setClose(97.93);
		q32.setClose(97.93);
		q33.setClose(97.97);
		q34.setClose(97.94);
		q35.setClose(97.89);
		q36.setClose(97.95);
		q37.setClose(97.94);
		q38.setClose(97.93);
		q39.setClose(97.95);
		q40.setClose(97.94);
		q41.setClose(97.97);
		q42.setClose(98.00);
		q43.setClose(98.01);
		q44.setClose(97.97);
		q45.setClose(98.02);
		q46.setClose(98.14);
		q47.setOpen(98.09);

		list.add(q1);
		list.add(q2);
		list.add(q3);
		list.add(q4);
		list.add(q5);
		list.add(q6);
		list.add(q7);
		list.add(q8);
		list.add(q9);
		list.add(q10);
		list.add(q11);
		list.add(q12);
		list.add(q13);
		list.add(q14);
		list.add(q15);
		list.add(q16);
		list.add(q17);
		list.add(q18);
		list.add(q19);
		list.add(q20);
		list.add(q21);
		list.add(q22);
		list.add(q23);
		list.add(q24);
		list.add(q25);
		list.add(q26);
		list.add(q27);
		list.add(q28);
		list.add(q29);
		list.add(q30);
		list.add(q31);
		list.add(q32);
		list.add(q33);
		list.add(q34);
		list.add(q35);
		list.add(q36);
		list.add(q37);
		 list.add(q38);
		 list.add(q39);
		 list.add(q40);
		 list.add(q41);
		 list.add(q42);
		 list.add(q43);
		 list.add(q44);
		 list.add(q45);
		 list.add(q46);
		 list.add(q47);

		// list.add(q48);
		// list.add(q49);
		// list.add(q50);

	}

	

	@Test
	public void testCalculateMovingAverage()
	{
		double answerLWMA = 98.068;
		double answerSMA = 0.0;
		
		double valueLWMA = MovingAverageCalculator.calculateMovingAverage(list, 5, "LWMA");
		LOG.info(valueLWMA);
		assertEquals(answerLWMA, valueLWMA, 0.001);
	}

	@Test
	public void testDetermineMACrossOverSignalFromQuotes()
	{
//		fail("Not yet implemented"); // TODO
	}

	@After
	public void tearDown() throws Exception
	{
	}
}
