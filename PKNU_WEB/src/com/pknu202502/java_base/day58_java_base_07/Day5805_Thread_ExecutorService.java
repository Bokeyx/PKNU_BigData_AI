package com.pknu202502.java_base.day58_java_base_07;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/***
 * 3. ExecutorService 라이브러리를 이용한 구현 방법
 */
public class Day5805_Thread_ExecutorService {
    public static void main(String[] args) {
        System.out.println("[ExecutorService 라이브러리를 이용한 구현 방법]");
        System.out.println("-------------------------------------");

        /**
         * ExecutorService: 스레드 풀은 관리하는 인터페이스
         *  - 사용할 스레드 풀의 갯수를 정의 해야함
         *  - newFixedThreadPool(스레드 갯수): 최대 사용할 스레드의 갯수 정의
         */
        ExecutorService executor = Executors.newFixedThreadPool(3);

        // 스레드로 처리할 클래스: TaskA
        executor.submit(new TaskA());
        // 스레드로 처리할 클래스: TaskB
        executor.submit(new TaskB());
        // 스레드로 처리할 클래스(메인 스데드로 처리): MainTask
        for (int i = 0; i < 3; i++) {
            executor.submit(new MainTask(i));
        }
    }
}

/***
 * MainTask: 메인 스레드로 처리
 *  - main() 메소드에서 처리할 내용을 별도 클래스로 정의만 했을 뿐
 *  - 다만, main 스레드를 적용
 */
class MainTask implements Runnable {
    
    // 멤버 변수 만들기
    private int taskNum;

    // 생성자 생성하기
    public MainTask(int taskNum) {
        this.taskNum = taskNum;
    }
    // run() 메소드 재정의하기
    @Override
    public void run() {
        // Thread.currentThread().getName(): 현재 수행중인 스레드(프레스스) 이름
        System.out.println("[MainTask " + this.taskNum + "]작업 시작: " + Thread.currentThread().getName());
        try {
            // 처리할 프로그램
            System.out.println("[MainTask] 여기에서 처리 프로그램이 진행되고 있음");
            
            // 1.5초 대기
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            System.out.println("[MainTask] 오류 발생");
        }
        System.out.println("[MainTask " + this.taskNum + "]작업 종료: "+ Thread.currentThread().getName());
    }
}
/***
 * TaskA 클래스 생성하기
 *  - Runnable 클래스를 implements 하여 생성하기
 */
class TaskA implements Runnable {
    
    // run() 메소드 재정의하기
    @Override
    public void run() {
        // Thread.currentThread().getName(): 현재 수행중인 스레드(프레스스) 이름
        System.out.println("[TaskA] 작업 시작: " + Thread.currentThread().getName());
        try {
            // 처리할 프로그램
            System.out.println("[TaskA] 여기에서 처리 프로그램이 진행되고 있음");
            
            // 1.5초 대기
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            System.out.println("[TaskA] 오류 발생");
        }
        System.out.println("[TaskA] 작업 종료: " + Thread.currentThread().getName());
    }
}


/***
 * TaskB 클래스 생성하기
 *  - Runnable 클래스를 implements 하여 생성하기
 */
class TaskB implements Runnable {
    
    // run() 메소드 재정의하기
    @Override
    public void run() {
        // Thread.currentThread().getName(): 현재 수행중인 스레드(프레스스) 이름
        System.out.println("[TaskB] 작업 시작: " + Thread.currentThread().getName());
        try {
            // 처리할 프로그램
            System.out.println("[TaskB] 여기에서 처리 프로그램이 진행되고 있음");
            
            // 1.2초 대기
            Thread.sleep(1200);
        } catch (InterruptedException e) {
            System.out.println("[TaskB] 오류 발생");
        }
        System.out.println("[TaskB] 작업 종료: " + Thread.currentThread().getName());
    }
}