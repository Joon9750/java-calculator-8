package calculator.domain.parser;

import calculator.domain.number.Numbers;

import calculator.domain.number.PositiveNumber;
import calculator.domain.validate.Validator;
import calculator.global.Error;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public record ParsingStrategyImpl(Validator validator) implements ParsingStrategy {

    @Override
    public Numbers parseDefaultText(String text) {
        List<String> stringNumbers = splitText(text, ParsingConstants.DEFAULT_DELIMITER_REGEX);
        return convertToNumbers(stringNumbers);
    }

    @Override
    public Numbers parseCustomText(String text) {
        Matcher matcher = ParsingConstants.CUSTOM_DELIMITER_PATTERN.matcher(text);
        matcher.matches();
        String customDelimiter = matcher.group(1);
        String numbersText = matcher.group(2);
        String escapedDelimiter = Pattern.quote(customDelimiter);
        List<String> stringNumbers = splitText(numbersText, escapedDelimiter);
        return convertToNumbers(stringNumbers);
    }

    private List<String> splitText(String text, String delimiter) {
        return Arrays.asList(text.split(delimiter));
    }

    private Numbers convertToNumbers(List<String> stringNumbers) {
        List<PositiveNumber> positiveNumbers = stringNumbers.stream()
                .map(stringNum -> parsePositiveNumber(stringNum.trim()))
                .toList();
        return new Numbers(positiveNumbers);
    }

    private PositiveNumber parsePositiveNumber(String text) {
        if (text.isEmpty()) {
            return new PositiveNumber(0);
        }
        int number = parseNumber(text);
        return new PositiveNumber(number);
    }

    private int parseNumber(String text) {
        try {
            return Integer.parseInt(text);
        } catch (NumberFormatException e) {
            Error.INVALID_NUMBER_FORMAT.throwException();
            throw new IllegalStateException("도달할 수 없는 코드입니다.");
        }
    }
}