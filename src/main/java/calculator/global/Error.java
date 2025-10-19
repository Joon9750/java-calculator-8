package calculator.global;

public enum Error {

    NULL_INPUT_TEXT("[ERROR] 입력 문자열은 null일 수 없습니다."),
    BLANK_INPUT_TEXT("[ERROR] 입력이 빈 문자열의 경우 0을 반환합니다."),
    NEGATIVE_NUMBER_NOT_ALLOWED("[ERROR] 음수는 허용되지 않습니다."),
    INVALID_NUMBER_FORMAT("[ERROR] 숫자 형식으로 변환할 수 없는 문자열이 포함되어 있습니다."),
    INVALID_INPUT_FORMAT("입력 형식이 유효하지 않습니다. 커스텀 구분자 형식이 아니거나 숫자와 기본 구분자 외의 문자가 포함되었습니다.");

    private final String message;

    Error(String message) {
        this.message = message;
    }

    public void throwException() {
        throw new IllegalArgumentException(message);
    }

    public static void throwInvalidNumberFormat() {
        throw new IllegalArgumentException(INVALID_NUMBER_FORMAT.message);
    }
    public static void throwNegativeNumberNotAllowed() {
        throw new IllegalArgumentException(NEGATIVE_NUMBER_NOT_ALLOWED.message);
    }
}