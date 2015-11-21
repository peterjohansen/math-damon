package com.actram.math.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;

import com.actram.math.Rational;

@RunWith(Parameterized.class)
public class RationalTest {

	public static final int N = 100;

	@Parameters
	public static Collection<Object> data() {
		Object[] data = new Object[N];
		for (int j = 0; j < N; j++) {
			Object[] params = new Object[3];
			for (int i = 0; i < params.length; i++) {
				int a = (int) (Math.random() * Math.sqrt(Integer.MAX_VALUE - 1));
				int b = (int) (Math.random() * Math.sqrt(Integer.MAX_VALUE - 1));
				data[j] = new Rational(a, b);
			}
			data[j] = params;
		}
		return Arrays.asList(data);
	}

	@Parameter(0) public Rational real1;
	@Parameter(1) public Rational real2;
	@Parameter(2) public Rational real3;

	@Test
	public void testAbsolute() {
		assertEquals(real1.absolute(), new Rational(real1.numerator(), real1.denominator()));
		if (real1.isNegative()) {
			assertNotEquals(real1, real1.absolute());
		}
	}

	@Test
	public void testAdd() {
		assertTrue(real1.compareTo(real1.add(real1)) > 0);
		assertEquals(real1.subtract(1), real1.add(-1));
		assertEquals(real1.multiply(2), real1.add(real1));
		assertEquals(real1, real1.add(Rational.ZERO));
		assertEquals(real1.add(Rational.ONE), real1.add(1));
	}

	@Test
	public void testAddAssociative() {
		fail();
	}

	@Test
	public void testMultiply() {
		assertEquals(real1, real1.multiply(1));
		assertEquals(0, real1.multiply(0));
		assertTrue(real1.multiply(2).compareTo(real1) > 0);
		assertTrue(real1.multiply(0.5).compareTo(real1) < 0);
	}

	@Test
	public void testLowestCommonDenominator() {
		fail();
	}

	@Test
	public void testMultiplyCommutative() {
		assertEquals(real1.multiply(real2), real2.multiply(real1));
	}

	@Test
	public void testAddCommutative() {
		assertEquals(real1.add(real2), real2.add(real1));
	}

	@Test
	public void testCompareTo() {
		assertEquals(real1.toDouble() < real1.add(1).toDouble(), real1.compareTo(real1.add(1)) < 0);
		assertEquals(real1.toDouble() > real1.subtract(1).toDouble(), real1.compareTo(real1.add(1)) > 0);
		assertTrue(real1.compareTo(real1) == 0);
	}

	@Test
	public void testDenominator() {
		assertTrue(real1.denominator() > 0);
	}

	@Test
	public void testDivide() {
		assertEquals(real1, real1.divide(1));
		assertTrue(real1.divide(2).compareTo(real1) < 0);
		assertTrue(real1.divide(0.5).compareTo(real1) > 0);
	}

	@Test
	public void testInverse() {
		assertEquals(real1.numerator(), real1.inverse().denominator());
		assertEquals(real1.denominator(), real1.inverse().numerator());
		assertEquals(real1.signum(), real1.inverse().signum());
	}
}