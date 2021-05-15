package org.xpdojo.bank;

public class Account {
    private Money balance;

    public Account() {
        this.balance = new Money();
    }

    public Money getBalance() {
        return this.balance;
    }

    public Boolean deposit(Money deposit) {
        if (!Money.isPositiveAmount(deposit)) throw new IllegalArgumentException("Deposit amount should be greater than 0.");
        this.balance = Money.getSum(this.balance, deposit);
        return true;
    }
}