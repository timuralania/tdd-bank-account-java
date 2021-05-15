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

    public Boolean withdraw(Money withdraw) {
        if (!Money.isPositiveAmount(withdraw)) throw new IllegalArgumentException("Withdrawal amount should be greater than 0.");
        if (!this.isEnoughMoney(withdraw)) throw new IllegalArgumentException("Insufficient funds.");
        this.balance = Money.getDifference(this.balance, withdraw);
        return true;
    }

    private Boolean isEnoughMoney(Money money) {
        return Money.compare(this.balance, money) > 0;
    }
}