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
}