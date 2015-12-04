package com.actram.math;

import java.util.Random;

/**
 * Immutable {@code Rational} range with chainable methods.
 *
 * @author Peter André Johansen
 */
public class RationalRange extends AbstractCountableRange<Rational> {
	public RationalRange(int p1, int q1, int p2, int q2) {
		this(new Rational(p1, q1), new Rational(p2, q2));
	}

	public RationalRange(Rational value) {
		super(value);
	}

	public RationalRange(Rational from, Rational to) {
		super(from, to);
	}

	@Override
	protected Rational add(Rational value1, Rational value2) {
		return value1.add(value2);
	}

	@Override
	public <R extends AbstractRange<Rational>> R center() {
		throw new UnsupportedOperationException("now implemented yet");
	}

	@Override
	protected Rational half(Rational value) {
		return value.divide(2);
	}

	@Override
	protected boolean isNegative(Rational value) {
		return value.isNegative();
	}

	@Override
	protected Rational random(Random random) {
		throw new UnsupportedOperationException("not implemented yet");
	}

	@Override
	public <R extends AbstractRange<Rational>> R set(Rational from, Rational to) {
		return cast(new RationalRange(from, to));
	}

	@Override
	protected Rational subtract(Rational value1, Rational value2) {
		return value1.subtract(value2);
	}
}