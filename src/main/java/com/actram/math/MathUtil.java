package com.actram.math;

/**
 * Static utility methods for mathematical operations.
 *
 * @author Peter André Johansen
 */
public class MathUtil {

	/**
	 * Calculates the greatest common divisor by using the
	 * <a href="https://en.wikipedia.org/wiki/Euclidean_algorithm">Euclidean
	 * algorithm</a>.
	 * 
	 * @param p the numerator
	 * @param q the denominator
	 * @return the greatest common divisor
	 */
	public static int gcd(int p, int q) {
		while (q != 0) {
			int r = q;
			q = p % q;
			p = r;
		}
		return p;
	}

	/** Prevent instantiation. */
	private MathUtil() {}
}