package calculator.domain.number;

import java.util.List;

public class Numbers {

    private final List<PositiveNumber> positiveNumbers;

    public Numbers(List<PositiveNumber> positiveNumbers) {
        this.positiveNumbers = positiveNumbers;
    }

    public int sum() {
        return positiveNumbers.stream()
                .mapToInt(PositiveNumber::toInt)
                .sum();
    }
}