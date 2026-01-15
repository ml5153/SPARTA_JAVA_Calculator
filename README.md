# Java Calculator Project
자바의 기초 문법을 활용하여 사칙연산과 계산 기록 관리가 가능한 계산기 프로그램입니다.

### 주요 기능
사칙연산: +, -, *, / 기본 연산 및 %, ^, sqrt 등 특수 연산 지원.

계산기 모드:
- 결과값을 유지하여 연속으로 계산하는 Continue 기능.
- 값을 초기화하고 새로 계산하는 Reset 기능.

기록 관리:
- 모든 계산 내역 저장 및 조회.
- 가장 오래된 기록 삭제 기능.

사용자 편의:
- 잘못된 입력(문자 입력, 0으로 나누기 등)에 대한 예외 처리.

### 실행 방법
- 프로젝트를 클론합니다.
- com.sparta.Calculator 클래스의 start() 메서드를 실행합니다.
- 콘솔의 안내에 따라 메뉴를 선택하고 숫자를 입력합니다.

### 사용 기술 및 라이브러리
Language: Java 17
Tool: IntelliJ IDEA
Object: Enum, Optional, Stream API, List, Scanner 등

### 프로젝트 구조
Main.java: 메인 클래스
Calculator.java: 계산 로직 및 전체 프로그램 흐름 제어.
com.sparta.data.Operator: 연산자를 관리하는 Enum.
com.sparta.data.CalculatorMenu: 메뉴 옵션을 관리하는 Enum.
