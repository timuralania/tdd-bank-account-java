package org.xpdojo.bank;

import java.math.BigDecimal;

/**
 * Immutable class to represent Money as a concept.
 * This class should have no accessor methods.
 */
public class Money {
    private final BigDecimal amount;

    public Money() {
        this(BigDecimal.ZERO);
    }

    public Money(String amount) {
        this(new BigDecimal(amount));
    }

    public Money(BigDecimal amount) {
        if (amount.signum() < 0) throw new IllegalArgumentException("The amount cannot be negative.");
        this.amount = amount;
    }

    protected static Money getSum(Money one, Money two) {
        return new Money(one.amount.add(two.amount));
    }

    protected static Money getDifference(Money one, Money two) {
        return new Money(one.amount.subtract(two.amount));
    }

    protected static Integer compare(Money one, Money two) {
        return one.amount.compareTo(two.amount);
    }

    protected static Boolean isPositiveAmount(Money money) {
        return money.amount.signum() > 0;
    }

    @Override
    public String toString() {
        return this.amount.toString();
    }
}