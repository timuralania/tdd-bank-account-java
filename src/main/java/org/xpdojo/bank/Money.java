package org.xpdojo.bank;

import java.math.BigDecimal;

/**
 * Immutable class to represent Money as a concept.
 * This class should have no accessor methods.
 */
public class Money {
    private final BigDecimal amount;

    public Money(String amount) {
        this(new BigDecimal(amount));
    }

    public Money(BigDecimal amount) {
        if(amount.signum() < 0) throw new IllegalArgumentException("The amount cannot be negative.");
        this.amount = amount;
    }

    @Override
    public String toString() {
        return this.amount.toString();
    }
}