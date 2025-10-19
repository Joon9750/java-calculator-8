package calculator.domain.number;

import calculator.global.Error;

public class PositiveNumber {

    private final int value;

    public PositiveNumber(int number) {
        validateNegative(number);
        this.value = number;
    }

    private void validateNegative(int number) {
        if (number < 0) {
            Error.NEGATIVE_NUMBER_NOT_ALLOWED.throwException();
        }
    }

    public int toInt() {
        return value;
    }
}