package com.pknu202502.java_base.day53_java_base_02.src;


import java.util.Scanner;

/***
 * 반복문
 * - 반복문 종류: for, while
 */
public class Day5301_For{
    public static void main(String[] args) {
        System.out.println("반복문 시작하기");

        /**
         * <for문>: 반복문
         * @ 문법
         *  for(시작변수 선언 및 값 정의; 끝의 범위 조건; 증감연산자){
         *      프로그램 처리...
         *  }
         * 
         *  for(int i=0; i<10000; i++){
         *      System.out.println(i);
         *  }
         * 
         * @ 배열 데이터인 경우 For문 사용하는 방법 2가지
         *  - 인덱스 번호를 이용하는 방식: 위의 for문에 대한 문법 적용
         *  - 값을 이용하는 방식: 향상된 for문 문법에 따름
         *   -> 향상된 for문 뭄번
         *      for(String str: 배열변수){
         *          //배열변수에서 0번째부터 값만 추출하여 str변수에 담아줍니다.
         *          System.out.println(str);
         *      }
         */

        /*
         * <문제>
         * - 1부터 5까지 출력해주세요..
         *  -> 출력 예시: "for문 1번 반복합니다."...."for문 5번 반복합니다."
         */

        for(int i=1; i<=5; i++){
            System.out.println("for문 %d번 반복합니다".formatted(i));
        }

        /*
         * <문제>
         * - 1부터 10까지의 총합을 출력해주세요.
         *  - 출력 예시: "1부터 10까지의 총합은 55 입니다."
         */

        int sum = 0;
        for(int i=1; i<=10; i++){
            sum += i;
        }
        System.out.println("1부터 10까지의 총합은 %d 입니다.".formatted(sum));

        System.out.println("\n--------------------------------------------\n");

        /*
         * <문제>
         * @ 1부터 10까지의 숫자 중에 짝수값의 총합을 출력해주세요.
         *  - 출력 예시: "1부터 10까지의 짝수의 총합은 55 입니다."
         *  - 두가지 방법(for문의 증감, 짝수 계산)으로 해주세요. 할 수 있는 만큼
         */

        sum = 0;
        for(int i=0; i<=10; i++){
            // 짝수 계싼 후 조건처리
            if (i % 2 == 0){
                 sum += i;
            }
        }
        System.out.println("1부터 10까지의 짝수의 총합은 %d 입니다.".formatted(sum));

        System.out.println("\n--------------------------------------------\n");

        sum = 0;
        for(int i=2; i<=10; i+=2){
            sum += i;
        }
        System.out.println("1부터 10까지의 짝수의 총합은 %d 입니다.".formatted(sum));

        /**
         * 사용자로부터(키보드 입력 받는 클래스 사용)
         *  - 클래스 생성: Scanner sc = Scanner(System.in) 클래스 사용
         *  - 사용방법
         *   @ 정수값을 입력 받아오고자 할 때: sc.nextInt() 사용
         *   @ 문자열을 입력 받아오고자 할 때: sc.nextLine() 사용
         *   -> 간혹, 예외 처리가 필요한 경우가 발생할 수 있음
         */

        // 사용자로부터 입력 받은 값을 출력하기
        // - 클래스 생성하기
        // - System.in: 사용자 PC의 키보드로부터 값을 전달받기 위해 대기상태로 기다립니다.
        // - 사용자로부터 입력을 받은 후, 더 이상 입력을 받지 않는 경우에는 Scanner를 종료해야함
        //  -> sc.close()
        Scanner sc = new Scanner(System.in);

        // 사용자로부터 입력 받기
        // - 안내 메시지 보여주기
        System.out.print("정수값을 입력하여 주세요: ");
        // - 사용자로부터 입력 받기
        int rs_int = sc.nextInt();
        System.out.println("사용자로부터 입력 받은 값: %d".formatted(rs_int));
        System.out.println("\n--------------------------------------------\n");

        // Scanner 종료하기(리소스 반환하기)
        // sc.close();

        /*
         * 임의 값을 사용자로 부터 입력 받아서 짝수인지 홀수인지 출력해주세요.
         *  - 3번 반복하면서 입력 받고, 출력하고.. 반복
         */
        
    
        for(int i = 1; i <= 3; i++){
            System.out.print("정수값을 입력하여 주세요: ");
            int test_int = sc.nextInt();
            if (test_int % 2 == 0) {
                System.out.println("사용자로부터 입력 받은 값: %d은 짝수입니다.".formatted(test_int));
            } else {
                System.out.println("사용자로부터 입력 받은 값: %d은 홀수입니다.".formatted(test_int));
            }
            System.out.println("\n--------------------------------------------\n");
        }

        //문자열 입력 받아보기
        sc.nextLine();
        System.out.println("문자열을 입력하여 주세요: ");
        String msg = sc.nextLine();
        System.out.println("입력 받은 문자열: %s".formatted(msg));
        System.out.println("\n--------------------------------------------\n");

        // Scanner 종료하기(리소스 반환하기)
        sc.close();
    }
}