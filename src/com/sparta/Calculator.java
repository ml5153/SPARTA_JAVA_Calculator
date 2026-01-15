package com.sparta;

import com.sparta.data.CalculatorMenu;
import com.sparta.data.Operator;

import java.util.*;

public class Calculator {

    private final List<String> history = new ArrayList<>();

    /**
     * 데이터의 불변성을 지키기 위한 캡슐화를 잘하는 것이 중요점인듯하다. 그런부분을 바로바로 생각하는게 잘안되는 것 같다.
     * history 를 그대로 전달하면 외부에서 받아 .add 혹은 .clear를 통해 히스토리 내역을 write 할 수 있다. (불변성 해침) !!
     * 1. 수정불가 원본: Collections.unmodifiableList(history)
     * 2. 복사본 전달: new ArrayList<>(history)
     * 3. 수정불가 복사본: List.copyOf(history)  = Collections.unmodifiableList(new ArrayList<>(history))
     */


//    public List<String> getHistory() {
//        return history;
//    }

//    public List<String> getHistory() {
//        return Collections.unmodifiableList(history);
//    }

//    public List<String> getHistory() {
//        return new ArrayList<String>(history);
//    }

//    public List<String> getHistory() {
//        return List.copyOf(history);
//    }
    public List<String> getHistory() {
        return Collections.unmodifiableList(new ArrayList<>(history));
    }


    public void start() {
        boolean isRunning = true;
        Scanner scanner = new Scanner(System.in);
        System.out.println("===나는 계산기야===");
        // menu
        while (isRunning) {
            System.out.println("메뉴를 고르세요. ( c: 계산기, h: 히스토리, exit: 종료 )");
            CalculatorMenu menu = CalculatorMenu.fromString(scanner.next());
            if (menu == null) {
                System.out.println("잘못된 입력입니다. 다시 골라주세요.");
                continue;
            }

            switch (menu) {
                case CALCULATOR -> {
                    calculatorMenu(scanner);
                    break;
                }
                case HISTORY -> {
                    historyMenu(scanner);
                    break;
                }
                case EXIT -> {
                    System.out.println("계산기 프로그램을 종료합니다.");
                    isRunning = false;
                    break;
                }
                default -> System.out.println("잘못된 입력입니다. 다시 선택해주세요.");
            }
        }
    }


    private void calculatorMenu(final Scanner scanner) {
        System.out.println("\n--- 계산기 ---");
        boolean isRunning = true;
        double lastResult = 0;
        boolean hasLastResult = false;


        while (isRunning) {
            double num1 = hasLastResult ? lastResult : 0;
            if (!hasLastResult) {
                num1 = getSafeDouble(scanner, "첫 번째 숫자를 입력하세요: ");
            }
            Operator operator = getSafeOperator(scanner);
            double num2 = getSafeDouble(scanner, "두 번째 숫자를 입력하세요: ");

            lastResult = calculate(num1, num2, operator);


            System.out.println("결과: " + num1 + " " + operator + " " + num2 + " = " + lastResult);


            System.out.println("더 계산하시겠습니까? ( co: 계속하기, r: 리셋, h: 히스토리, p: 메뉴로 이동 )");
            CalculatorMenu menu = CalculatorMenu.fromString(scanner.next());
            if (menu == null) {
                System.out.println("잘못된 입력입니다. 다시 골라주세요.");
                continue;
            }
            switch (menu) {
                case CONTINUE: {
                    hasLastResult = true;
                    break;
                }

                case RESET: {
                    hasLastResult = false;
                    lastResult = 0;
                    System.out.println("값을 초기화했습니다.");
                    break;
                }

                case HISTORY: {
                    hasLastResult = true;
                    historyMenu(scanner);
                    break;
                }

                case PREVIOUS: {
                    System.out.println("최종 결과값은: " + lastResult + " 입니다.");
                    isRunning = false;
                    break;
                }
                default: {
                    System.out.println("잘못된 입력입니다. 다시 선택해주세요.");
                    break;
                }

            }
        }
    }


    public void removeOldestHistory() {
        if (!history.isEmpty()) {
            history.remove(0);
        }
    }

    private void historyMenu(final Scanner scanner) {
        System.out.println("\n--- 계산 기록 ---");
        boolean isRunning = true;


        while (isRunning) {
            // 이때 내부 history값을 이용해야할지? 외부에 전달하는 history 메서드중 어떤걸 이용할지 고민이됩니다.
            // 단방향 데이터 흐름을 지키고 싶어서 고민을 했습니다.
            List<String> currentHistory = getHistory();
            if (currentHistory.isEmpty()) {
                System.out.println("기록이 없습니다.");
                return;
            } else {
                for (int i = 0; i < currentHistory.size(); i++) {
                    System.out.printf("[%d] %s%n", (i + 1), currentHistory.get(i));
                }
            }

            System.out.println("\n( delete: 오래된 기록 삭제, p: 뒤로 가기 )");
            CalculatorMenu menu = CalculatorMenu.fromString(scanner.next());
            if (menu == null) {
                System.out.println("잘못된 입력입니다. (delete 혹은 p를 입력해주세요)");
                continue;
            }

            switch (menu) {
                case DELETE_OLDEST:
                    removeOldestHistory();
                    System.out.println("삭제되었습니다.");
                    break;
                case PREVIOUS:
                    isRunning = false;
                    break;
                default:
                    System.out.println("잘못된 입력입니다. 다시 선택해주세요.");
                    break;
            }
        }

    }


    private double getSafeDouble(Scanner scanner, String message) {
        while (true) {
            System.out.print(message);
            try {
                return scanner.nextDouble();
            } catch (InputMismatchException e) {
                System.out.println("[오류] 숫자만 입력 가능합니다.");
                scanner.next();
            }
        }
    }


    private Operator getSafeOperator(Scanner scanner) {
        while (true) {
            System.out.print("사칙연산 기호를 입력하세요 (+, -, *, /, %, ^, q): ");
            String input = scanner.next();
//            Operator op = Operator.fromString(input.charAt(0));
            Operator op = Operator.fromString(input.charAt(0)).orElse(null); // 기본값 설정 가능
//            Operator op = Operator.fromString(input.charAt(0)).orElseThrow(() -> new IllegalArgumentException("잘못된 연산자!"));

            if (op != null) {
                return op;
            }
            System.out.println("[오류] 지원하지 않는 연산자입니다.");
        }
    }


    private double calculate(
            final double num1,
            final double num2,
            final Operator operator
    ) {

        if (operator == null) {
            return 0;
        }

        double result = 0;
        // Switch 문도 람다가 있었다^^ (kotlin의 when 문과 비슷해서 편하다.)
        switch (operator) {
            case PLUS -> result = num1 + num2;
            case MINUS -> result = num1 - num2;
            case MULTIPLY -> result = num1 * num2;
            case DIVIDE -> {
                if (num2 == 0) {
                    throw new ArithmeticException("분모 0으로 나눌수 없습니다.");
                } else {
                    result = num1 / num2;
                }
            }
            case MOD -> result = num1 % num2;
            case POW -> result = Math.pow(num1, num2);
            case SQRT -> result = Math.sqrt(num1);

        }

        // history (String.format 으로 성능을 향상 시켰다)
        String record = String.format("%s %s %s = %f", num1, operator, num2, result);
        history.add(record);

        return result;
    }
}



