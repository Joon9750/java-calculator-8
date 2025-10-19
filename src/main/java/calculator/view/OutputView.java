package calculator.view;

public class OutputView {

    private static final String RESULT_FORMAT = "결과 : %d";

    public void printResult(int result) {
        System.out.printf((RESULT_FORMAT) + "%n", result);
    }
}