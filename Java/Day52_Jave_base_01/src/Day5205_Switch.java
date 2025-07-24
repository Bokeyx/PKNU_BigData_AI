/***
 * Day5205_Switch 클래스
 *  - 선택문(switch) 실습
 */
public class Day5205_Switch {
    public static void main(String[] args){
        System.out.println("Day5205_Switch : switch 선택문");

        /**
         * 선택문(switch)
         * - if문과 유사한 개념
         * - 조건은 없으며, 값을 이용하여 선택하게됨
         * - 작성 방법
         *   switch(값or변수){
         *      case 비교값1 -> 처리로직(최종 결과값을 뽑아내는 로직처리)
         *      case 비교값2 -> 처리로직(최종 결과값을 뽑아내는 로직처리)
         *      default -> 기타로직(최종 결과값을 뽑아내는 로직처리)
         *   }   
         */

         /*
          * 1. 임의 변수에 임의 정수값 정의하기
          * 2. 임의 변수값이 1이면, 월요일 출력
          *                  2이면, 화요일 출력
          *                  3이면, 수요일 출력
          *                  1~3이 아니면, 기타요일 출력
          */
        
        int day = 2;
        
        switch(day){
            case 1 -> System.out.println("월요일");
            case 2 -> System.out.println("화요일");
            case 3 -> System.out.println("수요일");
            default -> System.out.println("기타요일");
        }

        System.out.println("\n -------------------------------------------------- \n");
        /**
         * 1. 임의 변수에 임의 값을 정의하기
         *  - 임의 변수의 값이 짝수이면, 짝수 입니다. 
         *                     홀수이면, 홀수 입니다. 출력하기
         */
        int num = 25;
        switch(num % 2){
            case 0 -> System.out.println("짝수 입니다.");
            default -> System.out.println("홀수 입니다.");
        }
    }
}
