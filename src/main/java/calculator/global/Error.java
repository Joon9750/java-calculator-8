package calculator.global;

public enum Error {

    NULL_INPUT_TEXT("[ERROR] 입력 문자열은 null일 수 없습니다."),
    NEGATIVE_NUMBER_NOT_ALLOWED("[ERROR] 음수는 허용되지 않습니다."),
    INVALID_NUMBER_FORMAT("[ERROR] 숫자 형식으로 변환할 수 없는 문자열이 포함되어 있습니다.");

    private final String message;

    Error(String message) {
        this.message = message;
    }

    public void throwException() {
        throw new IllegalArgumentException(message);
    }
}