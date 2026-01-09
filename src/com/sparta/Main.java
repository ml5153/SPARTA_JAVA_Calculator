package com.sparta;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        double lastResult = 0;
        boolean hasLastResult = false;
        System.out.println("===나는 계산기야===");
        Scanner scanner = new Scanner(System.in);

        while (true) {
            double num1 = lastResult;
            if (!hasLastResult) {
                System.out.print("첫 번째 숫자를 입력하세요: ");
                num1 = scanner.nextDouble();
            }
            System.out.print("사칙연산 기호를 입력하세요: ");
            char operator = scanner.next().charAt(0);
            System.out.print("두 번째 숫자를 입력하세요: ");
            double num2 = scanner.nextDouble();

            lastResult = calculate(num1, num2, operator);
            System.out.println(num1 + " " + operator + " " + num2 + " = " + lastResult);
            System.out.println("더 계산하시겠습니까? (y: 계산, c: 클리어, exit: 종료)");
            String choice = scanner.next();

            if (choice.equals("exit")) {
                System.out.println("계산기가 종료됩니다. 최종 결과값은 " + lastResult + " 입니다.");
                break;
            } else if (choice.equals("y")) {
                hasLastResult = true;
            } else if (choice.equals("c")) {
                hasLastResult = false;
                lastResult = 0;
                System.out.println("값을 초기화했습니다.");
            }
        }
    }

    private static double calculate(
            final double num1,
            final double num2,
            final char operator
    ) {

        double result = 0;
        switch (operator) {
            case '+': {
                result = num1 + num2;
                break;
            }

            case '-': {
                result = num1 - num2;
                break;
            }

            case '*': {
                result = num1 * num2;
                break;
            }

            case '/': {
                if (num2 != 0) {
                    result = num1 / num2;
                } else {
                    result = num2;
                    System.out.println("분모 0으로 나눌수 없습니다.");
                }

                break;
            }

            case '%': {
                result = num1 % num2;
                break;
            }

            case '^': {
                result = Math.pow(num1, num2);
                break;
            }

            case 'q': {
                result = Math.sqrt(num1);
                break;
            }

        }

        return result;
    }
}
