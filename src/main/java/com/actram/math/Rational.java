package com.actram.math;

/**
 * Immutable rational number with chainable methods.
 * <p>
 * Expressed as: {@code a/b, b!= 0}
 *
 * @author Peter André Johansen
 */
public class Rational implements Comparable<Rational> {
	public static final Rational NEGATIVE_ONE = new Rational(-1);
	public static final Rational NEGATIVE_HALF = new Rational(-1, 2);
	public static final Rational ZERO = new Rational(0);
	public static final Rational HALF = new Rational(1, 2);
	public static final Rational ONE = new Rational(1);

	public static Rational approximate(double value) {
		throw new UnsupportedOperationException("not implemented yet");
	}
	
	/** The numerator. Also contains the sign. */
	private final int p;

	/** The denominator. Always positive. */
	private final int q;

	public Rational(int p) {
		this(p, 1);
	}

	public Rational(int p, int q) {
		if (q == 0) {
			throw new IllegalArgumentException("the denominator cannot be zero");
		}

		// Store the sign in the numerator
		if (q < 0) {
			p = -p;
			q = -q;
		}

		// Reduce the fraction as much as possible
		int gcd = Math.abs(MathUtil.gcd(p, q));
		this.p = (p / gcd);
		this.q = (q / gcd);
	}

	public Rational absolute() {
		return (p < 0 ? negate() : this);
	}

	public Rational add(int n) {
		return this.set(p * 1 + n * q, q * 1);
	}

	public Rational add(Rational r) {
		return this.set(p * r.q + r.p * q, q * r.q);
	}

	@Override
	public int compareTo(Rational r) {
		final long lhs = p * r.q;
		final long rhs = q * r.p;
		if (lhs < rhs) return -1;
		if (lhs > rhs) return +1;
		return 0;
	}

	public Rational divide(int n) {
		return this.set(p, q * n);
	}

	public Rational divide(Rational r) {
		return this.set(p * r.q, q * r.p);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (obj == null) return false;
		if (getClass() != obj.getClass()) return false;
		Rational other = (Rational) obj;
		if (p != other.p) return false;
		if (q != other.q) return false;
		return true;
	}

	public int getDenominator() {
		return this.q;
	}

	public int getNumerator() {
		return Math.abs(p);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + p;
		result = prime * result + q;
		return result;
	}

	public Rational inverse() {
		return this.set(q, p);
	}

	public boolean isNegative() {
		return (p < 0);
	}

	public Rational mediant(Rational r) {
		return this.set(p + r.p, q + r.q);
	}

	public Rational multiply(int n) {
		return this.set(p * n, q);
	}

	public Rational multiply(Rational r) {
		return this.set(p * r.p, q * r.q);
	}

	public Rational negate() {
		return this.set(-p, q);
	}

	public Rational set(int p, int q) {
		return new Rational(p, q);
	}

	public int signum() {
		return (int) Math.signum(p);
	}

	public Rational subtract(int n) {
		return this.set(p * 1 - (n * q), q * 1);
	}

	public Rational subtract(Rational r) {
		return this.set(p * r.q - (r.p * q), q * r.q);
	}

	public double toDouble() {
		return (((double) p) / q);
	}

	public int toInteger() {
		return (int) toDouble();
	}

	public String toString() {
		return getClass().getSimpleName() + "[" + p + "/" + q + "]";
	}
}