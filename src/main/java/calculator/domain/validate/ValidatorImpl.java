package calculator.domain.validate;

import calculator.global.Error;

public class ValidatorImpl implements Validator {

    @Override
    public void checkInputTextValidation(String text) {
        checkTextIsNull(text);

    }

    private void checkTextIsNull(String text) {
        if (text == null) {
            Error.NULL_INPUT_TEXT.throwException();
        }
    }
}