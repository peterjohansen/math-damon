package com.actram.math;

import java.util.Random;

/**
 * Immutable {@code long} range with chainable methods.
 *
 * @author Peter André Johansen
 */
public class LongRange extends AbstractCountableRange<Long> {
	public LongRange() {
		this(0);
	}

	public LongRange(long value) {
		super(value);
	}

	public LongRange(long from, long to) {
		super(from, to);
	}

	@Override
	protected Long add(Long value1, Long value2) {
		return (value1 + value2);
	}

	@Override
	public <R extends AbstractRange<Long>> R center() {
		Long min = (from + distance() / 2);
		Long max = min;
		if (distance() % 2 != 0) {
			max++;
		}
		return cast(this.set(min, max));
	}

	@Override
	protected Long half(Long value) {
		return (value / 2);
	}

	@Override
	protected boolean isNegative(Long value) {
		return (value < 0);
	}

	@Override
	protected Long random(Random random) {
		return (long) (random.nextDouble() * (to - from) + from);
	}

	@Override
	public <R extends AbstractRange<Long>> R set(Long from, Long to) {
		return cast(new LongRange(from, to));
	}

	@Override
	protected Long subtract(Long value1, Long value2) {
		return (value1 - value2);
	}
}