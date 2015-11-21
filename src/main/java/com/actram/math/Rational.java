package com.actram.math;

/**
 * Immutable real number with chainable methods.
 * <p>
 * Expressed as {@code a/b, b!= 0}.
 *
 * @author Peter André Johansen
 */
public class Rational implements Comparable<Rational> {
	public static final Rational NEGATIVE_ONE = new Rational(-1);
	public static final Rational ZERO = new Rational(0);
	public static final Rational ONE = new Rational(1);

	public static Rational valueOf(String str) {
		return null;
	}

	/** The numerator. Also contains the sign. */
	private final int p;

	/** The denominator. */
	private final int q;

	public Rational(int p) {
		this(p, 1);
	}

	public Rational(int p, int q) {
		this.p = p;
		this.q = q;
	}

	public Rational absolute() {
		return null;
	}

	public Rational add(double n) {
		return this;
	}

	public Rational add(Rational real) {
		return null;
	}

	@Override
	public int compareTo(Rational o) {
		return 0;
	}

	public int denominator() {
		return -1;
	}

	public Rational divide(double i) {
		return null;
	}

	public Rational divide(Rational real) {
		return null;
	}

	public Rational inverse() {
		return null;
	}

	public boolean isNegative() {
		return p < 0;
	}

	public Rational multiply(double n) {
		return this;
	}

	public Rational multiply(Rational real) {
		return null;
	}

	public int numerator() {
		return -1;
	}

	public Rational opposite() {
		return null;
	}

	public int signum() {
		return -1;
	}

	public Rational subtract(double n) {
		return null;
	}

	public double toDouble() {
		return -1;
	}

	public int toInteger() {
		return -1;
	}

	public String toString() {
		return null;
	}
}