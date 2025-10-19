package calculator.domain.number;

import calculator.global.Error;

public class PositiveNumber {

    private final int value;

    private PositiveNumber(int number) {
        validateNegative(number);
        this.value = number;
    }

    public static PositiveNumber from(String text) {
        int number = parseToInt(text.trim());
        return new PositiveNumber(number);
    }

    private void validateNegative(int number) {
        if (number < 0) {
            Error.throwNegativeNumberNotAllowed();
        }
    }

    private static int parseToInt(String text) {
        if (text.isEmpty()) {
            return 0;
        }
        return parseNumber(text);
    }

    private static int parseNumber(String text) {
        try {
            return Integer.parseInt(text);
        } catch (NumberFormatException e) {
            Error.throwInvalidNumberFormat();
            throw new IllegalStateException("도달할 수 없는 코드입니다.");
        }
    }

    public int toInt() {
        return value;
    }
}