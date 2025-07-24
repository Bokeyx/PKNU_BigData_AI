import java.util.Scanner;

/***
 * 메소드 사용하기
 * - 오버로드(Overload)개념을 적용할 수 있음.
 */
public class Day5305_MethodDefine {


    /**
     * 메소드 정의
     *  - 클래스 내에 정의한 메소드는 멤버 메소드라고 칭합니다.
     *  - 모든(변수 또는 메소드) 멤버는 클래스 생성을 통해서만 접근이 가능합니다.
     *  - 메소드는 오버로드(Overload) 개념을 적용하여 생성 가능함
     */

    /***
     * 두개의 매개변수를 전달받아서 더한 값을 반환하는 멤버 메소드
    * @param a
    * @param b
    * @return
    */

    public int add(int a, int b) {
        return a + b;
    }

    public int subtract(int a, int b) {
        return a - b;
    }

    public int multiply(int a, int b) {
        return a * b;
    }

    public int divide(int a, int b) {
        return a / b;
    }

    public boolean even(int a) {
        return a % 2 == 0;
    }

    public boolean odd(int a) {
        return a % 2 != 0;
    }


    public static void main(String[] args) {
        System.out.println("[메소드] 정의하기");

        Day5305_MethodDefine dm = new Day5305_MethodDefine();
        System.out.printf("a + b = %d", dm.add(3, 5));
        System.out.println("\n---------------------------------");

        int rsTot = dm.add(3, 5);
        System.out.println("a + b = %d".formatted(rsTot));
        System.out.println("---------------------------------");

        /**
         * <더하기, 빼기, 곱하기, 나누기, 짝수or홀수>에 대한 결과값을
         * 출력하기...
         *  - 자유롭게 생성자, 메소드, 멤버들 사용해서 만들어보기
         */

        Scanner sc = new Scanner(System.in);
        System.out.print("x의 정수값을 입력하여 주세요: ");
        int x = sc.nextInt();
        System.out.print("y의 정수값을 입력하여 주세요: ");
        int y = sc.nextInt();
        System.out.println("사용자로부터 입력 받은 값: x = %d / y = %d".formatted(x,y));
        System.out.println("int add() 함수 호출 결과: %d + %d = %d".formatted(x, y, dm.add(x, y)));
        System.out.println("int substract() 함수 호출 결과: %d + %d = %d".formatted(x, y, dm.subtract(x, y)));
        System.out.println("int multiply() 함수 호출 결과: %d + %d = %d".formatted(x, y, dm.multiply(x, y)));
        System.out.println("int divide() 함수 호출 결과: %d + %d = %d".formatted(x, y, dm.divide(x, y)));
        System.out.println("boolean even() 함수 호출 결과: %s로 짝수입니다".formatted(dm.even(x)));
        System.out.println("boolean odd() 함수 호출 결과: %s로 홀수입니다".formatted(dm.odd(x)));
        sc.close();
    }
}
