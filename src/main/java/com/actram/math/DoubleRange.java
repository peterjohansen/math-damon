package com.actram.math;

import java.util.Random;

/**
 * Immutable {@code double} range with chainable methods.
 *
 * @author Peter André Johansen
 */
public class DoubleRange extends AbstractCountableRange<Double> {
	public DoubleRange() {
		this(0);
	}

	public DoubleRange(double value) {
		super(value);
	}

	public DoubleRange(double from, double to) {
		super(from, to);
	}

	@Override
	protected Double add(Double value1, Double value2) {
		return (value1 + value2);
	}

	@Override
	public AbstractCountableRange<Double> center() {
		Double value = (from + distance() / 2);
		return (AbstractCountableRange<Double>) this.set(value, value);
	}

	@Override
	protected Double half(Double value) {
		return (value / 2);
	}

	@Override
	protected boolean isNegative(Double value) {
		return (value < 0);
	}

	@Override
	protected Double random(Random random) {
		return random.nextDouble() * (to - from) + from;
	}

	@Override
	public AbstractRange<Double> set(Double from, Double to) {
		return new DoubleRange(from, to);
	}

	@Override
	protected Double subtract(Double value1, Double value2) {
		return (value1 - value2);
	}
}