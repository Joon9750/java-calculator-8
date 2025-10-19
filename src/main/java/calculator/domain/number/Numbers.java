package calculator.domain.number;

import java.util.List;

public class Numbers {

    private final List<PositiveNumber> positiveNumbers;

    public Numbers(List<String> stringNumbers) {
        this.positiveNumbers = stringNumbers.stream()
                .map(PositiveNumber::from)
                .toList();
    }

    public int sum() {
        return positiveNumbers.stream()
                .mapToInt(PositiveNumber::toInt)
                .sum();
    }
}