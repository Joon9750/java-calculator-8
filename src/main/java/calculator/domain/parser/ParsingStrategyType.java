package calculator.domain.parser;

import calculator.domain.number.Numbers;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.stream.Stream;

public enum ParsingStrategyType {

    DEFAULT {
        @Override
        boolean supports(String text) {
            return !isCustomPatternMatched(text) && isDefaultContentValid(text);
        }

        @Override
        public Numbers parse(ParsingStrategy parsingStrategy, String text) {
            return parsingStrategy.parseDefaultText(text);
        }
    },
    CUSTOM {
        @Override
        boolean supports(String text) {
            return isCustomPatternMatched(text);
        }

        @Override
        public Numbers parse(ParsingStrategy parsingStrategy, String text) {
            return parsingStrategy.parseCustomText(text);
        }
    };

    private static boolean isCustomPatternMatched(String text) {
        Matcher matcher = ParsingConstants.CUSTOM_DELIMITER_PATTERN.matcher(text);
        return matcher.matches();
    }

    private static boolean isDefaultContentValid(String text) {
        return ParsingConstants.VALID_DEFAULT_CONTENT_PATTERN.matcher(text).matches();
    }

    abstract boolean supports(String text);
    public abstract Numbers parse(ParsingStrategy parsingStrategy, String text);

    public static ParsingStrategyType findStrategyFor(String text) {
        Stream<ParsingStrategyType> strategies = Arrays.stream(values());
        return strategies
                .filter(strategy -> strategy.supports(text))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("적절한 파싱 전략을 찾을 수 없습니다."));
    }
}