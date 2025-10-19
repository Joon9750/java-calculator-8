package calculator.domain.parser;

import calculator.domain.number.Numbers;
import calculator.domain.validate.Validator;

public interface ParsingStrategy {
    Numbers parseDefaultText(String text);
    Numbers parseCustomText(String text);
    Validator validator();
}