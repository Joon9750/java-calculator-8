package calculator.domain.validate;

import calculator.domain.parser.ParsingConstants;
import calculator.global.Error;
import java.util.regex.Matcher;

public class ValidatorImpl implements Validator {

    @Override
    public void checkInputTextValidation(String text) {
        checkTextIsNull(text);
        checkTextIsValidFormat(text);
    }

    private void checkTextIsNull(String text) {
        if (text == null) {
            Error.NULL_INPUT_TEXT.throwException();
        }
    }

    private void checkTextIsValidFormat(String text) {
        if (text.isEmpty()) {
            return;
        }
        if (!isCustomPatternMatched(text) && !isDefaultContentValid(text)) {
            Error.INVALID_INPUT_FORMAT.throwException();
        }
    }

    @Override
    public boolean isCustomPatternMatched(String text) {
        Matcher matcher = ParsingConstants.CUSTOM_DELIMITER_PATTERN.matcher(text);
        return matcher.matches();
    }

    private boolean isDefaultContentValid(String text) {
        return ParsingConstants.VALID_DEFAULT_CONTENT_PATTERN.matcher(text).matches();
    }
}