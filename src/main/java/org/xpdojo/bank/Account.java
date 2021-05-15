package org.xpdojo.bank;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Account {
    private Money balance;
    List<Transaction> statement;

    public Account() {
        this.balance = new Money();
        this.statement = new ArrayList<>();
    }

    public Money getBalance() {
        return this.balance;
    }

    public List<Transaction> getStatement() {
        return this.statement;
    }

    public Boolean deposit(Money deposit) {
        if (!Money.isPositiveAmount(deposit)) throw new IllegalArgumentException("Deposit amount should be greater than 0.");
        this.balance = Money.getSum(this.balance, deposit);
        return this.statement.add(new Transaction(deposit, this.balance, TransactionType.DEPOSIT));
    }

    public Boolean withdraw(Money withdraw) {
        if (!Money.isPositiveAmount(withdraw)) throw new IllegalArgumentException("Withdrawal amount should be greater than 0.");
        if (!this.isEnoughMoney(withdraw)) throw new IllegalArgumentException("Insufficient funds.");
        this.balance = Money.getDifference(this.balance, withdraw);
        return this.statement.add(new Transaction(withdraw, this.balance, TransactionType.WITHDRAWAL));
    }

    public Boolean transfer(Money transfer, Account receiver) {
        if (!Money.isPositiveAmount(transfer)) throw new IllegalArgumentException("Transfer amount should be greater than 0.");
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

    public void printStatement(TransactionType type, LocalDate date) {
        List<Transaction> statementToPrint = this.getFilteredStatement(this.statement, type, date);
        for (Transaction transaction : statementToPrint) System.out.println(transaction);
    }

    protected List<Transaction> getFilteredStatement(List<Transaction> statement, TransactionType type, LocalDate date) {
        if (type != null) statement = this.filterStatementByType(statement, type);
        if (date != null) statement = this.filterStatementByDate(statement, date);
        return statement;
    }

    private List<Transaction> filterStatementByType(List<Transaction> statement, TransactionType type) {
        return statement
                .stream()
                .filter(t -> t.getType().equals(type))
                .collect(Collectors.toList());
    }

    private List<Transaction> filterStatementByDate(List<Transaction> statement, LocalDate date) {
        return statement
                .stream()
                .filter(t -> t.getDateTime().toLocalDate().isEqual(date))
                .collect(Collectors.toList());
    }

    private Boolean isEnoughMoney(Money money) {
        return Money.compare(this.balance, money) > 0;
    }
}