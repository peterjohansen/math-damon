package com.actram.math;

import java.util.Random;

/**
 * Immutable {@code int} range with chainable methods.
 *
 * @author Peter André Johansen
 */
public class IntegerRange extends AbstractCountableRange<Integer> {
	public IntegerRange() {
		this(0);
	}

	public IntegerRange(int value) {
		super(value);
	}

	public IntegerRange(int from, int to) {
		super(from, to);
	}

	@Override
	protected Integer add(Integer value1, Integer value2) {
		return (value1 + value2);
	}

	@Override
	public <R extends AbstractRange<Integer>> R center() {
		Integer min = (from + distance() / 2);
		Integer max = min;
		if (distance() % 2 != 0) {
			max++;
		}
		return cast(this.set(min, max));
	}

	@Override
	protected Integer half(Integer value) {
		return (value / 2);
	}

	@Override
	protected boolean isNegative(Integer value) {
		return (value < 0);
	}

	@Override
	protected Integer random(Random random) {
		return (int) (random.nextDouble() * (to - from) + from);
	}

	@Override
	public <R extends AbstractRange<Integer>> R set(Integer from, Integer to) {
		return cast(new IntegerRange(from, to));
	}

	@Override
	protected Integer subtract(Integer value1, Integer value2) {
		return (value1 - value2);
	}
}