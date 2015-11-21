package com.actram.math;

import java.util.Objects;
import java.util.Random;

/**
 * Abstract class for immutable ranges with a countable type (e.g. numbers). All
 * methods are chainable.
 *
 * @author Peter André Johansen
 *
 * @param <T> the type the range contains
 */
public abstract class AbstractCountableRange<T extends Comparable<T>> extends AbstractRange<T> {
	public AbstractCountableRange(T value) {
		this(value, value);
	}

	public AbstractCountableRange(T from, T to) {
		super(from, to);
	}

	/**
	 * @return the result of {@code value1 + value2}.
	 */
	protected abstract T add(T value1, T value2);

	/**
	 * @return the center, expressed as a range
	 */
	public abstract AbstractCountableRange<T> center();

	/**
	 * @return the distance the range covers
	 */
	public T distance() {
		return subtract(this.to, this.from);
	}

	/**
	 * @return a range with the given amount subtracted and added to the minimum
	 *         and maximum value, respectively, of this range
	 */
	public AbstractCountableRange<T> extend(T amount) {
		Objects.requireNonNull(amount, "amount cannot be null");
		if (isNegative(amount)) {
			throw new IllegalArgumentException("amount cannot be negative");
		}
		return this.extendMinimum(amount).extendMaximum(amount);
	}

	/**
	 * @return a range with the given amount added to the maximum value of this
	 *         rang
	 * @throws IllegalArgumentException if the amount is negative
	 */
	public AbstractCountableRange<T> extendMaximum(T amount) {
		Objects.requireNonNull(amount, "amount cannot be null");
		if (isNegative(amount)) {
			throw new IllegalArgumentException("amount cannot be negative");
		}
		return (AbstractCountableRange<T>) this.setTo(add(this.to, amount));
	}

	/**
	 * @return a range with the given amount subtracted from the minimum value
	 *         of this range
	 * @throws IllegalArgumentException if the amount is negative
	 */
	public AbstractCountableRange<T> extendMinimum(T amount) {
		Objects.requireNonNull(amount, "amount cannot be null");
		if (isNegative(amount)) {
			throw new IllegalArgumentException("amount cannot be negative");
		}
		return (AbstractCountableRange<T>) this.setFrom(subtract(this.from, amount));
	}

	/**
	 * @return half of the given value
	 */
	protected abstract T half(T value);

	/**
	 * @return whether the given value is negative
	 */
	protected abstract boolean isNegative(T value);

	/**
	 * @return a random value from the range (inclusively)
	 */
	protected abstract T random(Random random);

	/**
	 * @return a random value from the range (inclusively)
	 */
	public T randomValue() {
		return this.randomValue(new Random());
	}

	/**
	 * @return a random value from the range (inclusively)
	 */
	public T randomValue(Random random) {
		Objects.requireNonNull(random, "random cannot be null");

		return random(random);
	}

	/**
	 * @return a range moved by the given amount
	 */
	public AbstractCountableRange<T> shift(T amount) {
		Objects.requireNonNull(amount, "amount cannot be null");

		return (AbstractCountableRange<T>) this.set(add(from, amount), add(to, amount));
	}

	/**
	 * @return the result of {@code value1 - value2}.
	 */
	protected abstract T subtract(T value1, T value2);

	/**
	 * Returns a range with the given amount added and subtracted to the minimum
	 * and maximum value, respectively, of this range.
	 * <p>
	 * If the result of this operation creates an illegal range, a range with
	 * equal minimum and maximum values will be returned instead. The values
	 * will be equal the center value of the range.
	 * 
	 * @param amount
	 * @return
	 * @throws IllegalArgumentException if the amount is negative
	 */
	public AbstractCountableRange<T> trim(T amount) {
		Objects.requireNonNull(amount, "amount cannot be null");
		if (isNegative(amount)) {
			throw new IllegalArgumentException("amount cannot be negative");
		}

		T min = add(this.from, amount);
		T max = subtract(this.to, amount);
		if (min.compareTo(max) > 0) {

			// Trimming the range will result in a range
			// with a larger minimum than maximum. Instead
			// we will clamp the values to a midpoint.
			min = max = add(this.from, half(subtract(this.to, this.from)));
		}
		return (AbstractCountableRange<T>) this.set(min, max);
	}

	/**
	 * Returns a range with the given amount subtracted from the maximum value
	 * of this range.
	 * <p>
	 * If the result of this operation creates an illegal range, a range with
	 * equal minimum and maximum values will be returned instead. The values
	 * will equal to the minimum value of this range.
	 * 
	 * @return the new range
	 * @throws IllegalArgumentException if the amount is negative
	 */
	public AbstractCountableRange<T> trimMaximum(T amount) {
		Objects.requireNonNull(amount, "amount cannot be null");
		if (isNegative(amount)) {
			throw new IllegalArgumentException("amount cannot be negative");
		}

		T newTo = subtract(this.to, amount);
		if (newTo.compareTo(this.from) < 0) {
			newTo = this.from;
		}
		return (AbstractCountableRange<T>) this.setTo(newTo);
	}

	/**
	 * Returns a range with the given amount added to the minimum value of this
	 * range.
	 * <p>
	 * If the result of this operation creates an illegal range, a range with
	 * equal minimum and maximum values will be returned instead. The values
	 * will equal to the maximum value of this range.
	 * 
	 * @return the new range
	 * @throws IllegalArgumentException if the amount is negative
	 */
	public AbstractCountableRange<T> trimMinimum(T amount) {
		Objects.requireNonNull(amount, "amount cannot be null");
		if (isNegative(amount)) {
			throw new IllegalArgumentException("amount cannot be negative");
		}

		T newFrom = add(this.from, amount);
		if (newFrom.compareTo(this.to) > 0) {
			newFrom = this.to;
		}
		return (AbstractCountableRange<T>) this.setFrom(newFrom);
	}
}