package com.actram.math.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;

import com.actram.math.AbstractRange;
import com.actram.math.IntegerRange;

@RunWith(Parameterized.class)
public class AbstractRangeTest {

	@Parameters
	public static Collection<Object> data() {
		int value = (int) (Math.random() * Math.sqrt(Integer.MAX_VALUE - 1));
		// @formatterOff
		return Arrays.asList(new Object[] {
			new IntegerRange(-value, value),
			new IntegerRange(-value * value, -value),
			new IntegerRange(-value, 0),
			new IntegerRange(0, value),
			new IntegerRange(value, value * 2),
		});
		// @formatterOn
	}

	@Parameter public AbstractRange<Integer> range;

	@Test
	public void testBoundaries() {
		assertFalse(range.isUnder(range.getFrom()));
		assertFalse(range.isOver(range.getFrom()));
		assertTrue(range.isBoundary(range.getFrom()));

		assertFalse(range.isUnder(range.getTo()));
		assertFalse(range.isOver(range.getTo()));
		assertTrue(range.isBoundary(range.getTo()));
	}

	@Test
	public void testContains() {
		assertTrue(range.contains(range));
		assertTrue(range.contains(range.set(range.getFrom() + 1, range.getTo() - 1)));
		assertTrue(range.contains(range.getFrom()));
		assertTrue(range.contains(range.getTo()));
	}

	@Test
	public void testGetFrom() {
		assertEquals(range.getFrom(), range.setFrom(range.getFrom()).getFrom());
	}

	@Test
	public void testGetTo() {
		assertEquals(range.getTo(), range.setTo(range.getTo()).getTo());
	}

	@Test
	public void testIsOver() {
		assertTrue(range.isOver(range.getFrom() - 1));
	}

	@Test
	public void testIsUnder() {
		assertTrue(range.isUnder(range.getTo() + 1));
	}

	@Test
	public void testMaximum() {
		assertEquals((int) range.maximum(range.setTo(range.getTo() + 1)), range.getTo() + 1);
	}

	@Test
	public void testMinimum() {
		assertEquals((int) range.minimum(range.setFrom(range.getFrom() - 1)), range.getFrom() - 1);
	}

	@Test
	public void testMinimumLessThanMaximum() {
		assertTrue(range.getFrom().compareTo(range.getTo()) <= 0);
	}

	@Test
	public void testOverlaps() {
		assertTrue(range.overlaps(range.setTo(range.getTo() + 1)));
		assertTrue(range.overlaps(range.setFrom(range.getFrom() - 1)));
		assertTrue(range.overlaps(range));
	}

	@Test
	public void testSafeSet() {
		assertEquals(range, range.safeSet(range.getFrom(), range.getTo()));
		assertEquals(range, range.safeSet(range.getTo(), range.getFrom()));
	}

	@Test
	public void testSet() {
		assertEquals(range, range.set(range.getFrom(), range.getTo()));
		assertEquals(range.set(range.getFrom()), range.setTo(range.getFrom()));
		assertEquals(range.set(range.getTo()), range.setFrom(range.getTo()));
	}

	@Test
	public void testSetFrom() {
		assertEquals(range, range.setFrom(range.getFrom()));
	}

	@Test
	public void testSetTo() {
		assertEquals(range, range.setTo(range.getTo()));
	}
}