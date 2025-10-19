package calculator.domain.validate;

public interface Validator {
    void checkInputTextValidation(String text);
    boolean isCustomPatternMatched(String text);
}