package calculator.domain.parser;

import calculator.domain.number.Numbers;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ParsingStrategyImpl implements ParsingStrategy {

    @Override
    public Numbers parseDefaultText(String text) {
        return new Numbers(splitText(text, ParsingConstants.DEFAULT_DELIMITER_REGEX));
    }

    @Override
    public Numbers parseCustomText(String text) {
        Matcher matcher = ParsingConstants.CUSTOM_DELIMITER_PATTERN.matcher(text);
        matcher.matches();
        String customDelimiter = matcher.group(1);
        String numbersText = matcher.group(2);
        String escapedDelimiter = Pattern.quote(customDelimiter);
        return new Numbers(splitText(numbersText, escapedDelimiter));
    }

    private List<String> splitText(String text, String delimiter) {
        return Arrays.asList(text.split(delimiter));
    }
}