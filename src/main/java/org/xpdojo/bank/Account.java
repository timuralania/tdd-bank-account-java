package org.xpdojo.bank;

import java.time.LocalDateTime;

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

    public Boolean transfer(Money transfer, Account receiver) {
        if (!Money.isPositiveAmount(transfer))
            throw new IllegalArgumentException("Transfer amount should be greater than 0.");
        if (!this.isEnoughMoney(transfer)) throw new IllegalArgumentException("Insufficient funds.");
        return this.withdraw(transfer) && receiver.deposit(transfer);
    }

    public void printBalance() {
        StringBuilder prettyBalance = new StringBuilder()
                .append(LocalDateTime.now())
                .append(" - BALANCE: ")
                .append(this.balance)
                .append("\n");
        System.out.println(prettyBalance);
    }

    private Boolean isEnoughMoney(Money money) {
        return Money.compare(this.balance, money) > 0;
    }
}