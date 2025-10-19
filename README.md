# java-calculator-precourse

문자열 덧셈 계산기 미션 저장소입니다.

## 책임 분리 및 관리

본 프로젝트는 MVC(Model-View-Controller) 패턴을 기반으로 객체의 책임을 분리하여 구현했습니다.

- Controller
    - CalculatorController: 사용자의 입력을 View로부터 받아 Domain에 처리를 위임하고, 그 결과를 다시 View로 전달하는 전체 흐름을 제어합니다.
    - 도메인에서 발생한 IllegalArgumentException을 try-catch로 관리하며, 예외 발생 시 에러 메시지를 View에 전달합니다.
- Domain (Model)
    - StringCalculator: 컨트롤러로부터 문자열을 받아, 파싱 전략(ParsingStrategy)을 통해 숫자 목록을 얻고, Numbers 일급 컬렉션에 합계를 요청하여 결과를 반환하는 핵심 도메인 객체입니다.
    - ParsingStrategy: 기본 구분자(, , :) 또는 커스텀 구분자(//와 \n 사이의 문자)를 기준으로 문자열을 분리하는 책임을 가집니다.
    - Validator: 파싱된 문자열이 숫자인지, 음수인지 검증하는 책임을 가집니다.
    - PositiveNumber: int 타입의 숫자를 final 필드로 가지는 원시값 포장 객체입니다. 생성 시점에 Validator를 통해 양수 검증을 완료하여, 이 객체를 사용하는 곳에서는 값이 유효함을 보장받습니다.
    - Numbers: List<PositiveNumber>를 관리하는 일급 컬렉션입니다. 컬렉션에 대한 덧셈(합계) 로직을 캡슐화합니다.
- View
    - InputView: missionutils.Console.readLine()을 사용하여 사용자 입력을 받는 책임을 가집니다.
    - OutputView: "덧셈할 문자열을 입력해 주세요."와 같은 안내 메시지, "결과 : " 형식의 최종 결과, 또는 예외 메시지를 출력하는 책임을 가집니다.

## 기능 목록

### 1. 도메인 (계산기 로직)

- 기본 구분자 파싱: 쉼표(,) 또는 콜론(:)을 구분자로 문자열을 분리한다.
- 커스텀 구분자 파싱: //와 \n 사이에 위치한 문자를 커스텀 구분자로 지원한다.
- 값 검증 (유효성)
    - 입력 문자열이 null이거나 빈 문자열("")일 경우 0을 반환한다.
    - 분리된 문자열이 숫자로 변환 불가능할 경우 IllegalArgumentException을 발생시킨다.
    - 음수가 포함된 경우 IllegalArgumentException을 발생시킨다.
- 원시값 포장: 유효성이 검증된 양수를 PositiveNumber 객체로 포장한다.
- 일급 컬렉션: PositiveNumber의 리스트를 Numbers 일급 컬렉션으로 관리한다.
- 계산: Numbers 컬렉션 내의 모든 숫자의 합을 구하여 반환한다.

### 2. 뷰 (입출력)

- 입력 요청: "덧셈할 문자열을 입력해 주세요." 메시지를 출력한다.
- 입력: 사용자의 문자열 입력을 받는다.
- 결과 출력: "결과 : {합계}" 형식으로 덧셈 결과를 출력한다.
- 예외 출력: IllegalArgumentException 발생 시 해당 에러 메시지를 출력한다.

### 3. 컨트롤러 (흐름 제어)

- Application.main()에서 프로그램 실행을 시작한다.
- InputView를 통해 사용자 입력을 받는다.
- StringCalculator 도메인 객체에게 계산을 요청한다.
- 도메인으로부터 받은 결과를 OutputView를 통해 출력한다.
- 계산 중 예외 발생 시 OutputView를 통해 에러를 출력하고 애플리케이션을 정상 종료한다. (System.exit() 미사용)

## 테스트 항목

- StringCalculatorTest (도메인 테스트)
    - null 또는 빈 문자열 입력 시 0을 반환하는지 테스트
    - 기본 구분자(, , :)로 분리된 숫자들의 합을 정확히 반환하는지 테스트
    - 커스텀 구분자로 분리된 숫자들의 합을 정확히 반환하는지 테스트
    - 음수가 포함된 문자열 입력 시 IllegalArgumentException을 발생시키는지 테스트
    - 숫자가 아닌 문자가 포함된 문자열 입력 시 IllegalArgumentException을 발생시키는지 테스트
- ApplicationTest (기능 테스트)
    - 제공된 테스트 라이브러리(NsTest)를 활용하여 애플리케이션의 전체 입출력 흐름을 테스트
    - 커스텀 구분자 사용 시 정상적인 입출력이 완료되는지 테스트
    - 예외 상황(음수 입력 등) 발생 시 IllegalArgumentException이 발생하는지 테스트