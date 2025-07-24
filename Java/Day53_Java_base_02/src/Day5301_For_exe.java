public class Day5301_For_exe {
    public static void main(String[] args) {
        System.out.println("For문 구구단 실습");

        /*
         * <문제>
         * @ 구구단을 2단~9단까지 밑으로 출력해주세요.
         */

        
        for(int i = 2; i <= 9; i++){
            System.out.println("[%d단 입니다.]".formatted(i));
            for(int j = 1; j <= 9; j++){
                System.out.println("%d x %d = %d".formatted(i, j, i * j));
            }
        }

        /**
         * 구구단을 아래와 같이 수평 출력되도록 처리해 주세요.
         * <출력예시>
         * 2x1=2 2x2=2 .... 2x9=18
         * 3x1=2 3x2=6 .... 3x9=27
         * ...
         * 9x1=9 9x2=18 .... 9x9=81
         */

        for (int i = 2; i <= 9; i++) {
            System.out.println("\n[%d단 입니다.]".formatted(i));
            for (int j = 1; j <= 9; j++) {
                System.out.print("%d x %d = %d | ".formatted(i, j, i * j));
            }
        }

        System.out.println("\n------------------------------\n");

        /**
         * 구구단을 아래와 같이 수평 출력되도록 처리해 주세요.
         * <출력예시>
         * 2x1=2 3x1=3 ... 9x1=9
         * 2x2=4 3x2=6 ... 9x2=18
         * 2x9=18 3x9=27 ... 9x9=81
         */
        
        for (int j = 1; j <= 9; j++) {
            for (int i = 2; i <= 9; i++){
                System.out.print("%d x %d = %d\t".formatted(i, j, i * j));
            }
            System.out.println();
        }

        System.out.println("\n------------------------------\n");

        /**
         * @ break: 반복을 종료할 때 사용(보통 조건문과 함께 사용됨)
         *          (for, while, do-while 모두 사용가능)
         * - 사용방법
         *  : 반복문 내에서 종료하고 싶은 시점에 break; 만 작성하면 됩니다.
         * 
         * @ continue: 현재 반복을 건너띄고 다음 반복을 수행(보통 조건문과 함께 사용됨)
         *             (for, while, do-while 모두 사용가능)
         * - 사용방법
         *  : 반복문 내에서 건너띄고 싶은 시점에 continue; 만 작성하면 됩니다.
         */
    }
}
