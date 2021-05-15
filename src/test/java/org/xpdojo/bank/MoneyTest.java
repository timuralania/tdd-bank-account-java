package org.xpdojo.bank;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;


class MoneyTest {

    @Test
    void createMoneyFromInvalidAmountString() {
        // Arrange
        String invalidAmount = "invalid";

        // Assert
        assertThatThrownBy(() -> new Money(invalidAmount))
                .isInstanceOf(NumberFormatException.class);
    }

    @Test
    void createMoneyFromNegativeAmountString() {
        // Arrange
        String negativeAmount = "-1";

        // Assert
        assertThatThrownBy(() -> new Money(negativeAmount))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("The amount cannot be negative.");
    }

    @Test
    void createMoneyFromPositiveAmountString() {
        // Arrange
        String positiveAmount = "1";

        // Act
        Money validMoney = new Money(positiveAmount);

        // Assert
        assertThat(validMoney).isNotNull();
    }
}