package calculator.domain.parser;

import calculator.domain.number.Numbers;

import calculator.global.Error;
import java.util.Arrays;
import java.util.stream.Stream;

public enum ParsingStrategyType {

    DEFAULT {
        @Override
        boolean supports(ParsingStrategy parsingStrategy, String text) {
            return !parsingStrategy.validator().isCustomPatternMatched(text);
        }

        @Override
        public Numbers parse(ParsingStrategy parsingStrategy, String text) {
            return parsingStrategy.parseDefaultText(text);
        }
    },
    CUSTOM {
        @Override
        boolean supports(ParsingStrategy parsingStrategy, String text) {
            return parsingStrategy.validator().isCustomPatternMatched(text);
        }

        @Override
        public Numbers parse(ParsingStrategy parsingStrategy, String text) {
            return parsingStrategy.parseCustomText(text);
        }
    };

    abstract boolean supports(ParsingStrategy parsingStrategy, String text);
    public abstract Numbers parse(ParsingStrategy parsingStrategy, String text);

    public static ParsingStrategyType findStrategyFor(ParsingStrategy parsingStrategy, String text) {
        Stream<ParsingStrategyType> strategies = Arrays.stream(values());
        return strategies
                .filter(strategy -> strategy.supports(parsingStrategy, text))
                .findFirst()
                .orElseThrow(Error.STRATEGY_NOT_FOUND::getException);
    }
}