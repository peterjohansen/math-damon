package com.actram.math;

import java.util.Objects;

/**
 * Abstract class for immutable ranges with an arbitrary type. All methods are
 * chainable.
 * <p>
 * For ranges containing countable types, see: {@link AbstractCountableRange}
 *
 * @author Peter André Johansen
 *
 * @param <T> the type the range contains
 */
public abstract class AbstractRange<T extends Comparable<T>> {
	protected final T from;
	protected final T to;

	public AbstractRange(T value) {
		this(value, value);
	}

	public AbstractRange(T from, T to) {
		Objects.requireNonNull(from, "minimum cannot be null");
		Objects.requireNonNull(to, "maximum cannot be null");
		if (to.compareTo(from) < 0) {
			throw new IllegalArgumentException("maximum=" + to + " cannot be less than minimum=" + from);
		}

		this.from = from;
		this.to = to;
	}

	/**
	 * Performs a downcast on the given range to the desired type of range.
	 * Extracted to a method to reduce {@code @SuppressWarnings("unchecked")}
	 * -clutter.
	 * 
	 * return the range as the correct type
	 */
	@SuppressWarnings("unchecked")
	protected <R extends AbstractRange<T>, S extends AbstractRange<T>> R cast(S range) {
		return (R) range;
	}

	/**
	 * @return whether this range completely encompasses the given range
	 */
	public boolean contains(AbstractRange<T> range) {
		Objects.requireNonNull(range, "range cannot be null");
		return (this.contains(range.from) && this.contains(range.to));
	}

	/**
	 * @return whether the given value is inside (inclusive) this range
	 */
	public boolean contains(T value) {
		Objects.requireNonNull(value, "value cannot be null");
		return (!isUnder(value) && !isOver(value));
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (obj == null) return false;
		if (getClass() != obj.getClass()) return false;
		AbstractRange<?> other = (AbstractRange<?>) obj;
		if (from == null) {
			if (other.from != null) return false;
		} else if (!from.equals(other.from)) return false;
		if (to == null) {
			if (other.to != null) return false;
		} else if (!to.equals(other.to)) return false;
		return true;
	}

	/**
	 * @return the smallest value (the minimum) of this range
	 */
	public T getFrom() {
		return from;
	}

	/**
	 * @return the largest value (the maximum) of this range
	 */
	public T getTo() {
		return to;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((from == null) ? 0 : from.hashCode());
		result = prime * result + ((to == null) ? 0 : to.hashCode());
		return result;
	}

	/**
	 * @return whether the given value is exactly on either end of this range
	 */
	public boolean isBoundary(T value) {
		Objects.requireNonNull(value, "value cannot be null");
		return (from.compareTo(value) == 0 || to.compareTo(value) == 0);
	}

	/**
	 * @return whether the minimum of this range is larger than the given value
	 */
	public boolean isOver(T value) {
		Objects.requireNonNull(value, "value cannot be null");
		return (from.compareTo(value) > 0);
	}

	/**
	 * @return whether the maximum of this range is smaller than the given value
	 */
	public boolean isUnder(T value) {
		Objects.requireNonNull(value, "value cannot be null");
		return (to.compareTo(value) < 0);
	}

	/**
	 * @return the largest maximum of this and the given range
	 * @throws NullPointerException if the given range is null
	 */
	public T maximum(AbstractRange<T> range) {
		return (this.to.compareTo(range.to) < 0 ? range.to : this.to);
	}

	/**
	 * @return the smallest minimum of this and the given range
	 * @throws NullPointerException if the given range is null
	 */
	public T minimum(AbstractRange<T> range) {
		return (this.from.compareTo(range.from) < 0 ? this.from : range.from);
	}

	/**
	 * @return whether the given range overlaps this range
	 * @throws NullPointerException if the given range is null
	 */
	public boolean overlaps(AbstractRange<T> range) {
		Objects.requireNonNull(range, "range cannot be null");

		return !(this.to.compareTo(range.from) < 0 || range.to.compareTo(this.from) < 0);
	}

	/**
	 * Returns a range with the given values. The smallest value will be used as
	 * the minimum and the largest will be used as the maximum.
	 * <p>
	 * This method will never produce an illegal range (i.e. one where the
	 * maximum is smaller than the minimum).
	 * 
	 * @return the new range
	 */
	public <R extends AbstractRange<T>> R safeSet(T value1, T value2) {
		Objects.requireNonNull(value1, "first value cannot be null");
		Objects.requireNonNull(value2, "second value cannot be null");

		if (value2.compareTo(value1) < 0) {
			T temp = value1;
			value1 = value2;
			value2 = temp;
		}
		return this.set(value1, value2);
	}

	/**
	 * @return this range with the given value as both the minimum and maximum
	 */
	public <R extends AbstractRange<T>> R set(T value) {
		return cast(this.set(value, value));
	}

	/**
	 * @throws IllegalArgumentException if the minimum is larger than the
	 *             maximum
	 * @return a range with the given boundaries
	 */
	public abstract <R extends AbstractRange<T>> R set(T from, T to);

	/**
	 * @return this range with the given minimum
	 * @throws IllegalArgumentException if the given value is larger than the
	 *             maximum
	 */
	public <R extends AbstractRange<T>> R setFrom(T from) {
		Objects.requireNonNull(from, "minimum cannot be null");
		return this.set(from, this.to);
	}

	/**
	 * <strong>Note:</strong> This method sets the maximum of this range, not
	 * the range itself.
	 * 
	 * @return this range with the given maximum
	 * @throws IllegalArgumentException if the given value is smaller than the
	 *             minimum
	 */
	public <R extends AbstractRange<T>> R setTo(T to) {
		Objects.requireNonNull(to, "maximum cannot be null");
		return this.set(this.from, to);
	}

	@Override
	public String toString() {
		return String.format("%s[%s, %s]", getClass().getSimpleName(), from, to);
	}
}