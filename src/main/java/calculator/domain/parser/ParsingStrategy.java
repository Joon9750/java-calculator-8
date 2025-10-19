package calculator.domain.parser;

import calculator.domain.number.Numbers;

public interface ParsingStrategy {
    Numbers parseDefaultText(String text);
    Numbers parseCustomText(String text);
}