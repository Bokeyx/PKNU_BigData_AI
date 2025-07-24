package Day54_Java_base_03.src;

import java.util.Arrays;
import java.util.Scanner;

/***
 * 배열(Array) 클래스
 */
public class Day5402_Array {

    /**
     * <배열(array)>
     * - 여러개의 값을 저장할 수 있는 타입
     * - 배열은 대괄호로 정의합니다.
     * - java에서는 배열에 넣을 수 있는 데이터 타입은
     *  -> 최초 변수 또는 객체 선언 시에 정의된 타입의 데이터만 저장 가능함
     * - 배열 변수 또는 객체를 선언 할 때는 배열 내 저장할 공간의 갯수를 정의해야함
     * - 배열 내에 데이터는 보통 반복문을 이용해서 조회합니다.
     * - 배열 내에 공간 메모리 주소는 0부터 시작합니다.(파이썬과 동일)
     * - 배열에 값을 넣을 때는 주소를 이용해서 넣을 수 있으며,
     *   최초 선언시 정의하면서 값을 정의할 수 있음
     * - 배열은 메모리 공간을 가지는 객체입니다.
     *  -> 따라서, 배열은 생성해서 사용해야 합니다.
     */

    public static void main(String[] args) {
        System.out.println("[배열(array)]");
        System.out.println("\n----------------------------------------\n");

        // 정수값을 담을 수 있는 정수형 배열을 선언만 하기
        // - 배열 공간은 3개로 정의
        // - 값을 담을 수 있는 공간 3개를 만들어 놓습니다.
        int[] intVal =  new int[3];
        
        // 배열 주소 공간에 값 넣기
        intVal[0] = 55;
        intVal[1] = 88;
        intVal[2] = 77;

        // 배열에 들어있는 값의 사이즈(길이) 확인
        System.out.println("배열의 길이: %d".formatted(intVal.length));

        // 배열에 들어있는 값을 출력해주세요.
        System.out.println("배열에 들어있는 값: 첫번째 = %d | 두번째 = %d | 세번째 = %d".formatted(intVal[0],intVal[1],intVal[2]));

        for (int i = 0; i<intVal.length; i++) {
            System.out.println("[%d]배열에 들어있는 값: %d".formatted(i, intVal[i]));
        } 

        for (int val : intVal) {
            System.out.println("배열에 들어있는 값: " + val);
        }

        // Arrays: 배열 원형 객체
        // toString: 문자열로 변환하는 형변환 메소드
        String msg = Arrays.toString(intVal);
        System.out.println("배열에 저장된 값 전체 출력: %s".formatted(msg));

        Scanner sc = new Scanner(System.in);
        System.out.print("출력받을 위치의 인덱스 값을 입력해주세요: ");
        int rs_int = sc.nextInt();
        if (rs_int < intVal.length) {
            System.out.println("배열에 들어있는 값: %d".formatted(intVal[rs_int]));
        } else {
            System.out.println("배열에 없는 값이 입력 되었습니다.");
        }
        sc.close();

        /**
         * 정수형 배열 scores 변수명으로 선언과 동시에 배열값 정의
         * - 값 정의는 중괄호를 이용하여 값을 정의
         * - 이때 중괄호는 딕셔너리를 의미하지는 않음
         * - 선언과 동시에 배열값을 정의할 때는 new하지 않음
         *  -> 값을 넣는 순간 메모리가 만들어짐(자동 생성됨)
         */

        int[] scores = {33, 44, 55, 66, 66};

        for (int i = 0; i<scores.length; i++) {
            System.out.println("[%d]배열에 들어있는 값: %d".formatted(i, intVal[i]));
        } 

        for (int val : scores) {
            System.out.println("배열에 들어있는 값: " + val);
        }
    }
}
