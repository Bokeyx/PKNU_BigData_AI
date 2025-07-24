package com.pknu202502.java_base.day58_java_base_07;

/***
 * 2. Runnable 인터페이스를 사용하는 방법
 */
public class Day5804_Thread_Runnable {
    public static void main(String[] args) {
        System.out.println("[Runnable 방식]");
        System.out.println("-------------------------------------");

        // Runnable_Task 클래스 생성하기
        // - 부모클래스의 타입으로 생성하기
        // - 부모 타입으로 업캐스팅(타입 변환)됨
        Runnable task = new Runnable_Task();

        // Thread에 적용하기
        Thread t = new Thread(task);

        // Thread 시작하기
        t.start();

        /**
         * 메인 메소드에서 처리할 내용은 계속해서 처리 진행(메인 스레드) 
         */
        
        try {
            for (int i = 0; i < 5; i++) {
                System.out.println("[Main 메소드] >> 실행 중=============");

                // 0.4초 대기
                Thread.sleep(400);
            }   
        } catch (InterruptedException e) {
            System.out.println("[Main 메소드] >>> 실행 중 인터럽트 발생");
        }

    }
}

/***
 * 스레드로 처리할 클래스 선언 및 정의하기
 *  - Runnable 인터페이스 클래스 사용
 *  - run 메소드 재정의(Override) 하여 사용
 */

class Runnable_Task implements Runnable {
    
    /**
     * Runnable 인터페이스 클래스 내에 run() 메소드 재정의하기
     */

    @Override
    public void run() {
        // 실행할 처리 프로그램 작성
        // - 스레드는 항상 try{} catch(){}추가
        try {
            for (int i = 0; i <= 5; i++) {
                System.out.println("[Runnable_Task] >>>> 실행 중: " + i);

                // 0.8초 잠시 대기
                Thread.sleep(800);
            }
        } catch (InterruptedException e) {
            System.out.println("Interrupted Runnable_Task");
        }
    }
}
