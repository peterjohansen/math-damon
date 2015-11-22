package com.actram.math.test;

import static com.actram.math.MathUtil.gcd;
import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Test;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;

public class MathUtilTest {

	private static final int N = 100;

	@Parameters
	public static Collection<Object> data() {
		Object[] data = new Object[N];
		for (int j = 0; j < N; j++) {
			Object[] params = new Object[3];
			for (int i = 0; i < params.length; i++) {
				for (int k = 0; k < 2; k++) {
					data[j + k] = (int) (Math.random() * Math.sqrt(Integer.MAX_VALUE - 1));
				}
			}
			data[j] = params;
		}
		return Arrays.asList(data);
	}

	@Parameter(0) public int intValue1;
	@Parameter(1) public int intValue2;

	@Test
	public void testGcd() {
		assertEquals(gcd(intValue1, intValue1), intValue1);
		assertEquals(gcd(intValue1, intValue1 * 2), intValue1);
		assertEquals(gcd(-intValue1, intValue2), -gcd(intValue1, intValue2));
	}
}