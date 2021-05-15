package org.xpdojo.bank;


import java.time.LocalDateTime;

public class Transaction {
    private final LocalDateTime dateTime;
    private final Money amount;
    private final Money newBalance;
    private final TransactionType type;

    protected Transaction(Money amount, Money newBalance, TransactionType type) {
        this.dateTime = LocalDateTime.now();
        this.amount = amount;
        this.newBalance = newBalance;
        this.type = type;
    }

    protected LocalDateTime getDateTime() {
        return dateTime;
    }

    protected TransactionType getType() {
        return type;
    }

    @Override
    public String toString() {
        return new StringBuilder()
                .append(this.dateTime)
                .append(" - ")
                .append(this.type)
                .append(": ")
                .append(this.amount)
                .append(" - BALANCE: ")
                .append(this.newBalance)
                .toString();
    }
}
