package com.sparta;

public class Main {

    public static void main(String[] args) {
        // 계산기
        Calculator calc = new Calculator();
        calc.start();

    }


    /**
     * TODO 자체평가
     * Generic으로 구현하는 방식은 연습이 엄청 필요해보인다. (제네릭 클래스, 제네릭 메서드 언제 어떻게 구현해야할지?)
     * 클래스의 분리가 더필요한지에 대한 판단이 잘안선다. (메뉴 클래스를 분리했어야했을까? ... )
     현재 Calculator 에 계산기를 전부만들어놓았다. 계산기라는 하나의 클래스에  역할과 책임이 하나씩만 있는 메서드륿 분리하여 구현했다고 생각은 들지만,
     하나의 클래스에서 모든 작업이 이루어졌다는게 찜찜하다. 클아직 이런부분이 스스로 많이 부족한 것 같다.
     * Optional이라는걸 잘활용하면 helper클래스나 extension을 만들어놓으면 별도 null처리 없이 자바도 편하게 이용할 수 있어보인다.
     */

}
