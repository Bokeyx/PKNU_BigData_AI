package com.pknu202502.java_base.day53_java_base_02.src;

/***
 * 반복문: while, do-while
 */
public class Day5302_While_DoWhile {
    public static void main(String[] args) {
        System.out.println("반복문: while문 및 do-while문 실습");

        /**
         * while문
         * - 조건에 따라 반복을 수행함
         * - 주로 무한반복 시에 사용되며, 
         *   조건에 따른 반복의 횟수를 지정하는 경우에는 for문을 사용하는 것이 편리합니다.
         *   (단, for문은 시작과 끝의 범위를 정확히 알고 있는 경우에만,
         *        그렇지 않으면, while문을 사용해도 됩니다.)
         * - **(중요)최초에 조건에 만족해야 반복이 시작됩니다.
         * @ 사용방법
         *  <조건에 따른 반복처리>
         *  while(비교 조건) {
         *     프로그램 처리
         *  }
         * 
         *  <조건없이 무한반복 처리>
         *  while() {
         *     프로그램 처리
         *     if(종료 조건) break;
         *  }
         * 
         * do-while 문
         * - while문과 개념 및 사용법은 동일함
         * - 단, 차이점은 최초에 무조건 1회 반복을 수행합니다.
         * - 조건은 마지막에 있습니다.
         * - do-while문은 무한반복으로는 잘 사용되지 않으며,
         *   **(중요)최초 한번 수행 후 조건처리 해야하는 경우가 있을 때 사용됨
         *           (자주 사용되지 않음)
         * @ 사용방법
         *  do{
         *  }while(비교 조건);
         */

        /**
         * <문제>
         * - 1부터 5까지; 순서대로 출력해 주세요.
         * - while문과 do-while문 각각 사용해보기.
         */

        int i = 1;
        while (true) { 
            System.out.println("while[%d]번 출력".formatted(i++));
            if(i == 6) break;
        }

        System.out.println("\n------------------------------\n");

        i = 1;
        do {
            System.out.println("do-while[%d]번 출력".formatted(i));
            i++;
        } while (i <= 5);

        /*
         * 구구단 밑으로 출력
         */
        
        i = 2;
        while (i <= 9) {
            System.out.printf("[%d]단 입니다\n", i);
            int j = 1;
            while (j <= 9) {
                System.out.println("%d x %d = %d".formatted(i, j, i*j));
                j++;
            }
            i++;
        }
    }
}
