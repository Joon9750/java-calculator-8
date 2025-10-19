package calculator.domain.calulator;

import calculator.domain.number.Numbers;
import calculator.domain.parser.ParsingStrategy;
import calculator.domain.parser.ParsingStrategyType;
import calculator.domain.validate.Validator;

public class StringCalculatorImpl implements StringCalculator {

    private final ParsingStrategy parsingStrategy;
    private final Validator validator;

    public StringCalculatorImpl(ParsingStrategy parsingStrategy, Validator validator) {
        this.parsingStrategy = parsingStrategy;
        this.validator = validator;
    }

    @Override
    public Integer calculateInText(String text) {
        validateInputText(text);
        ParsingStrategyType parsingStrategyType = findParsingStrategyType(text);
        Numbers numbers = parseText(text, parsingStrategyType);
        return calculateNumbersSum(numbers);
    }

    private void validateInputText(String text) {
        validator.checkInputTextValidation(text);
    }

    private ParsingStrategyType findParsingStrategyType(String text) {
        return ParsingStrategyType.findStrategyFor(this.parsingStrategy, text);
    }

    private Numbers parseText(String text, ParsingStrategyType parsingStrategyType) {
        return parsingStrategyType.parse(this.parsingStrategy, text);
    }

    private Integer calculateNumbersSum(Numbers numbers) {
        return numbers.sum();
    }
}