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

	private static final int N = 100;

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

	@Parameter(0) public Rational rational1;
	@Parameter(1) public Rational rational2;
	@Parameter(2) public Rational rational3;

	@Test
	public void testAbsolute() {
		assertEquals(rational1.absolute(), new Rational(rational1.numerator(), rational1.denominator()));
		if (rational1.isNegative()) {
			assertNotEquals(rational1, rational1.absolute());
		}
	}

	@Test
	public void testAdd() {
		assertTrue(rational1.compareTo(rational1.add(rational1)) > 0);
		assertEquals(rational1.subtract(1), rational1.add(-1));
		assertEquals(rational1.multiply(2), rational1.add(rational1));
		assertEquals(rational1, rational1.add(Rational.ZERO));
		assertEquals(rational1.add(Rational.ONE), rational1.add(1));
	}

	@Test
	public void testAddAssociative() {
		fail();
	}

	@Test
	public void testAddCommutative() {
		assertEquals(rational1.add(rational2), rational2.add(rational1));
	}

	@Test
	public void testCompareTo() {
		assertEquals(rational1.toDouble() < rational1.add(1).toDouble(), rational1.compareTo(rational1.add(1)) < 0);
		assertEquals(rational1.toDouble() > rational1.subtract(1).toDouble(), rational1.compareTo(rational1.add(1)) > 0);
		assertTrue(rational1.compareTo(rational1) == 0);
	}

	@Test
	public void testDenominator() {
		assertTrue(rational1.denominator() > 0);
	}

	@Test
	public void testDivide() {
		assertEquals(rational1, rational1.divide(1));
		assertTrue(rational1.divide(2).compareTo(rational1) < 0);
		assertTrue(rational1.divide(0.5).compareTo(rational1) > 0);
	}

	@Test
	public void testInverse() {
		assertEquals(rational1.numerator(), rational1.inverse().denominator());
		assertEquals(rational1.denominator(), rational1.inverse().numerator());
		assertEquals(rational1.signum(), rational1.inverse().signum());
	}

	@Test
	public void testIsNegative() {
		assertEquals(rational1.signum() < 0, rational1.isNegative());
	}

	@Test
	public void testLowestCommonDenominator() {
		fail();
	}

	@Test
	public void testMultiply() {
		assertEquals(rational1, rational1.multiply(1));
		assertEquals(0, rational1.multiply(0));
		assertTrue(rational1.multiply(2).compareTo(rational1) > 0);
		assertTrue(rational1.multiply(0.5).compareTo(rational1) < 0);
	}

	@Test
	public void testMultiplyCommutative() {
		assertEquals(rational1.multiply(rational2), rational2.multiply(rational1));
	}

	@Test
	public void testOpposite() {
		if (rational1.signum() != 0) {
			assertEquals(rational1.multiply(-1), rational1.opposite());
		}
	}

	@Test
	public void testSignum() {
		int sign = rational1.signum();
		assertTrue(sign == -1 || sign == 0 || sign == 1);
		assertEquals((int) Math.signum(rational1.toInteger()), sign);
	}
}