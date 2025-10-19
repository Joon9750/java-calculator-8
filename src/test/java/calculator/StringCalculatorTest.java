package calculator;

import calculator.domain.calulator.StringCalculator;
import calculator.domain.calulator.StringCalculatorImpl;
import calculator.domain.parser.ParsingStrategy;
import calculator.domain.parser.ParsingStrategyImpl;
import calculator.domain.validate.Validator;
import calculator.domain.validate.ValidatorImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class StringCalculatorTest {

    private StringCalculator calculator;

    @BeforeEach
    void setUp() {
        ParsingStrategy parsingStrategy = new ParsingStrategyImpl();
        Validator validator = new ValidatorImpl();
        calculator = new StringCalculatorImpl(parsingStrategy, validator);
    }

    @Test
    void 빈문자열은_0을_반환() {
        assertThat(calculator.calculateInText("")).isEqualTo(0);
    }

    @Test
    void 기본_구분자_쉼표_콜론() {
        assertThat(calculator.calculateInText("1,2:3")).isEqualTo(6);
    }

    @Test
    void 하나의_숫자만_있는_경우() {
        assertThat(calculator.calculateInText("5")).isEqualTo(5);
    }

    @Test
    void 커스텀_구분자_세미콜론() {
        assertThat(calculator.calculateInText("//;\n1;2;3")).isEqualTo(6);
    }

    @Test
    void 커스텀_구분자_다른_문자() {
        assertThat(calculator.calculateInText("//@\n1@2@3")).isEqualTo(6);
    }

    @Test
    void 음수가_포함되면_IllegalArgumentException_발생() {
        assertThatThrownBy(() -> calculator.calculateInText("1,-2,3"))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 숫자가_아닌_값이_포함되면_IllegalArgumentException_발생() {
        assertThatThrownBy(() -> calculator.calculateInText("1,a,3"))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 커스텀_구분자_사용시_음수가_포함되면_IllegalArgumentException_발생() {
        assertThatThrownBy(() -> calculator.calculateInText("//;\n1;-2;3"))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 공백만_있는_문자열은_0을_반환() {
        assertThat(calculator.calculateInText(" ")).isEqualTo(0);
    }

    @Test
    void 구분자로만_이루어진_문자열은_0을_반환() {
        assertThat(calculator.calculateInText(",:")).isEqualTo(0);
    }

    @Test
    void 숫자와_구분자_사이의_공백은_무시하고_정상_계산() {
        assertThat(calculator.calculateInText("1, 2: 3")).isEqualTo(6);
    }

    @Test
    void 단일_숫자_앞에_공백이_있는_경우_정상_계산() {
        assertThat(calculator.calculateInText(" 5")).isEqualTo(5);
    }

    @Test
    void 기본_구분자와_커스텀_구분자가_혼재된_경우_IllegalArgumentException_발생() {
        assertThatThrownBy(() -> calculator.calculateInText("//;\n1,2;3"))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 기본_구분자_사이에_빈_값은_0으로_계산() {
        assertThat(calculator.calculateInText("1,,2")).isEqualTo(3);
    }

    @Test
    void 허용되지_않는_구분자가_포함된_경우_IllegalArgumentException_발생() {
        assertThatThrownBy(() -> calculator.calculateInText("1+2"))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 커스텀_구분자_뒤에_허용되지_않는_문자가_포함된_경우_IllegalArgumentException_발생() {
        assertThatThrownBy(() -> calculator.calculateInText("//!\n1!2+3"))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 커스텀_구분자가_기본_구분자와_겹치지_않는_특수문자인_경우_정상_계산() {
        assertThat(calculator.calculateInText("//+\n1+2+3")).isEqualTo(6);
    }

    @Test
    void 커스텀_구분자_사이의_빈_값은_0으로_계산() {
        assertThat(calculator.calculateInText("//;\n1;;2")).isEqualTo(3);
    }

    @Test
    void 복수_음수가_포함되면_IllegalArgumentException_발생() {
        assertThatThrownBy(() -> calculator.calculateInText("1,-2,-3"))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 커스텀_구분자_사용() {
        assertThat(calculator.calculateInText("//;\\n1")).isEqualTo(1);
    }


    @Test
    void 커스텀_구분자_형식이_불완전한_경우_IllegalArgumentException_발생() {
        assertThatThrownBy(() -> calculator.calculateInText("//;\n"))
                .isInstanceOf(IllegalArgumentException.class);
    }
}