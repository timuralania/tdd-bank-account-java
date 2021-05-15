package org.xpdojo.bank;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import static org.assertj.core.api.Assertions.*;

public class AccountTest {

    private Account insufficientFundsAccount;
    private Account sufficientFundsAccount;


    @BeforeEach
    public void setUp() {
        insufficientFundsAccount = new Account();
        sufficientFundsAccount = new Account();
        sufficientFundsAccount.deposit(new Money("12.34"));
    }

    @Test
    public void depositAnAmountToIncreaseTheBalance() {
        // Arrange
        Money moneyToDeposit = new Money("12.34");

        // Act
        Boolean isDeposited = insufficientFundsAccount.deposit(moneyToDeposit);
        Money balanceAfterDeposit = insufficientFundsAccount.getBalance();

        // Assert
        assertThat(isDeposited).isTrue();
        assertThat(Money.compare(moneyToDeposit, balanceAfterDeposit)).isEqualTo(0);
    }

    @Test
    public void depositAnAmountWhenInvalidAmount() {
        // Arrange
        Money moneyToDeposit = new Money("0");

        // Assert
        assertThatThrownBy(() -> insufficientFundsAccount.deposit(moneyToDeposit))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Deposit amount should be greater than 0.");
    }

    @Test
    public void withdrawAnAmountToDecreaseTheBalanceWhenSufficientFunds() {
        // Arrange
        Money moneyToWithdraw = new Money("1");

        // Act
        Money balanceBeforeWithdrawal = sufficientFundsAccount.getBalance();
        Boolean isWithdrawn = sufficientFundsAccount.withdraw(moneyToWithdraw);
        Money balanceAfterWithdrawal = sufficientFundsAccount.getBalance();
        Money difference = Money.getDifference(balanceBeforeWithdrawal, moneyToWithdraw);

        // Assert
        assertThat(isWithdrawn).isTrue();
        assertThat(Money.compare(difference, balanceAfterWithdrawal)).isEqualTo(0);
    }

    @Test
    public void withdrawAnAmountWhenInsufficientFunds() {
        // Arrange
        Money moneyToWithdraw = new Money("1");

        // Assert
        assertThatThrownBy(() -> insufficientFundsAccount.withdraw(moneyToWithdraw))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Insufficient funds.");
    }

    @Test
    public void withdrawAnAmountWhenInvalidAmount() {
        // Arrange
        Money moneyToWithdraw = new Money("0");

        // Assert
        assertThatThrownBy(() -> insufficientFundsAccount.withdraw(moneyToWithdraw))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Withdrawal amount should be greater than 0.");
    }


    @Test
    public void transferAnAmountToDecreaseTheBalanceWhenSufficientFunds() {
        // Arrange
        Money moneyToTransfer = new Money("1");

        // Act
        Money senderBalanceBeforeTransfer = sufficientFundsAccount.getBalance();
        Money receiverBalanceBeforeTransfer = insufficientFundsAccount.getBalance();
        Boolean isTransferred = sufficientFundsAccount.transfer(moneyToTransfer, insufficientFundsAccount);
        Money senderBalanceAfterTransfer = sufficientFundsAccount.getBalance();
        Money receiverBalanceAfterTransfer = insufficientFundsAccount.getBalance();

        Money senderDifference = Money.getDifference(senderBalanceBeforeTransfer, moneyToTransfer);
        Money receiverDifference = Money.getDifference(receiverBalanceAfterTransfer, moneyToTransfer);

        // Assert
        assertThat(isTransferred).isTrue();
        assertThat(Money.compare(senderDifference, senderBalanceAfterTransfer)).isEqualTo(0);
        assertThat(Money.compare(receiverDifference, receiverBalanceBeforeTransfer)).isEqualTo(0);
    }

    @Test
    public void transferAnAmountWhenInsufficientFunds() {
        // Arrange
        Money moneyToTransfer = new Money("1");

        // Assert
        assertThatThrownBy(() -> insufficientFundsAccount.transfer(moneyToTransfer, sufficientFundsAccount))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Insufficient funds.");
    }

    @Test
    public void transferAnAmountWhenInvalidAmount() {
        // Arrange
        Money moneyToTransfer = new Money("0");

        // Assert
        assertThatThrownBy(() -> sufficientFundsAccount.transfer(moneyToTransfer, insufficientFundsAccount))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Transfer amount should be greater than 0.");
    }
}