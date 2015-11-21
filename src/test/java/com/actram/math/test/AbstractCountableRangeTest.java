package com.actram.math.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.Collection;
import java.util.Random;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;

import com.actram.math.AbstractCountableRange;
import com.actram.math.IntegerRange;

@RunWith(Parameterized.class)
public class AbstractCountableRangeTest {

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

	@Parameter public AbstractCountableRange<Integer> range;

	@Test
	public void testDistance() {
		assertTrue(range.distance() >= 0);
		assertEquals(range.getFrom() + range.distance(), (int) range.getTo());
	}

	@Test
	public void testExtend() {
		AbstractCountableRange<Integer> range2 = range.extend(1);
		assertEquals((int) range.getFrom(), range2.getFrom() + 1);
		assertEquals((int) range.getTo(), range2.getTo() - 1);
		assertEquals((int) range.distance(), range2.distance() - 2);
	}

	@Test
	public void testExtendMaximum() {
		AbstractCountableRange<Integer> range2 = range.extendMaximum(1);
		assertEquals((int) range.distance(), range2.distance() - 1);
		assertEquals(range.getFrom(), range2.getFrom());
		assertNotEquals(range.getTo(), range2.getTo());
		assertEquals((int) range.getTo(), range2.getTo() - 1);
	}

	@Test
	public void testExtendMinimum() {
		AbstractCountableRange<Integer> range2 = range.extendMinimum(1);
		assertEquals((int) range.distance(), range2.distance() - 1);
		assertEquals(range.getTo(), range2.getTo());
		assertNotEquals(range.getFrom(), range2.getFrom());
		assertEquals((int) range.getFrom(), range2.getFrom() + 1);
	}

	@Test
	public void testRandomValue() {
		Integer value = range.randomValue();
		assertTrue(value >= range.getFrom() && value <= range.getTo());

		long seed = value;
		assertEquals(range.randomValue(new Random(seed)), range.randomValue(new Random(seed)));
	}

	@Test
	public void testShift() {
		AbstractCountableRange<Integer> range2 = range.shift(1);
		assertNotEquals(range, range2);
		assertEquals(range.distance(), range2.distance());
		assertEquals(range.getFrom() + 1, (int) range2.getFrom());
		assertEquals(range.getTo() + 1, (int) range2.getTo());

		AbstractCountableRange<Integer> range3 = range.shift(-1);
		assertNotEquals(range, range3);
		assertEquals(range.distance(), range3.distance());
		assertEquals(range.getFrom() - 1, (int) range3.getFrom());
		assertEquals(range.getTo() - 1, (int) range3.getTo());
	}

	@Test
	public void testTrim() {
		AbstractCountableRange<Integer> range2 = range.trim(1);
		assertEquals((int) range.getFrom(), range2.getFrom() - 1);
		assertEquals((int) range.getTo(), range2.getTo() + 1);
		assertEquals((int) range.distance(), range2.distance() + 2);
	}

	@Test
	public void testTrimMaximum() {
		AbstractCountableRange<Integer> range2 = range.trimMaximum(1);
		assertEquals((int) range.distance(), range2.distance() + 1);
		assertEquals(range.getFrom(), range2.getFrom());
		assertNotEquals(range.getTo(), range2.getTo());
		assertEquals((int) range.getTo(), range2.getTo() + 1);

		assertEquals(range.setTo(range.getFrom()), range.trimMaximum(range.distance() + 1));
	}

	@Test
	public void testTrimMinimum() {
		AbstractCountableRange<Integer> range2 = range.trimMinimum(1);
		assertEquals((int) range.distance(), range2.distance() + 1);
		assertEquals(range.getTo(), range2.getTo());
		assertNotEquals(range.getFrom(), range2.getFrom());
		assertEquals((int) range.getFrom(), range2.getFrom() - 1);

		assertEquals(range.setFrom(range.getTo()), range.trimMinimum(range.distance() + 1));
	}
}