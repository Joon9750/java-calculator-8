package calculator;

import calculator.controller.CalculatorController;
import calculator.domain.calulator.StringCalculator;
import calculator.domain.calulator.StringCalculatorImpl;
import calculator.domain.parser.ParsingStrategy;
import calculator.domain.parser.ParsingStrategyImpl;
import calculator.domain.validate.Validator;
import calculator.domain.validate.ValidatorImpl;
import calculator.view.InputView;
import calculator.view.OutputView;

public class Application {
    public static void main(String[] args) {
        // TODO: 프로그램 구현
        ParsingStrategy parsingStrategy = new ParsingStrategyImpl();
        Validator validator = new ValidatorImpl();
        StringCalculator stringCalculator = new StringCalculatorImpl(parsingStrategy, validator);

        InputView inputView = new InputView();
        OutputView outputView = new OutputView();

        CalculatorController controller = new CalculatorController(inputView, outputView, stringCalculator);

        controller.run();
    }
}