package com.pknu202502.java_base.day58_java_base_07;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/***
 * 파일 입력(Write) / 출력(Read)
 */
public class Day5802_FIleReadWrite {
    public static void main(String[] args) {

        /**
         * <파일 입력 및 출력>
         *  - 보통 파일 입/출력 I/O(Input/Output)이라고 표현합니다;
         *  - 파일 입력(저장) 처리
         *   : FileWriter 클래스 사용
         *   : 파일에 대한 자원에 접근하는 클래스로
         *   : 파일을 생성하고, 작성 가능
         *   : 입력 처리가 종료되면, 파일 자원을 종료해야함(close)
         * 
         *  - 파일 출력(열기) 처리
         *   : FileReader, BufferedReader 클래스 함께 사용
         *   : FileReader 클래스를 통해서 출력할 파일을 메모리에 적재하는 기능 수행
         *   : BufferedReader 클래스를 통해서 메모리에서 행단위로 읽어들인 후 출력하는 기능을 수행
         *    -> 보통 while문과 함께 사용됨: 읽어들일 수 있을 때까지 읽어들인 후 출력하는 기능을 수행
         *                                 : 더 이상 읽어들이 내용(다음 행)이 없으면 종료
         *   : 파일에 대한 자원을 종료해야함(close)
         * 
         *  - 파일 입력/출력 처리는 다양한 방법들이 많으며, 보통 기업에서 사용하는 방식을 따름
         */

        System.out.println("[파일 입/출력]");
        System.out.println("--------------------------------------");

        /**
         * 파일명 지정
         *  - 파일처리는 물리적 위치 공간을 사용하기에 패키지 경로를 사용하지 않습니다.
         *  - 실제 물맂거 전체 경로를 기준으로 합니다.
         *  - 현재 VS-Code에 작업디렉토리부터가 root 결로의 시작이 됩니다.
         */
        String filePath = "./src/com/pknu202502/java_base/day58_java_base_07/files/";
        String fileName = "day_5802_test01.txt";
        String fullName = filePath + fileName;
        System.out.println("저장할 파일의 전체 경로 및 파일명" + fullName);

        /**
         * 파일 입력/출력을 위한 클래스 변수 선언하기
        */
        // 파일 입력(쓰기) 처리 클래스
        FileWriter writer;
        // 파일 입력(읽) 처리 클래스
        FileReader reader;
        // 파일 입력(쓰기) 처리 클래스
        BufferedReader br;

        /**
         * 파일 처리하기
         *  - 예외처리하기: 예외 클래스(IOException)
         */
        try {
            // 파일 쓰기
            writer = new FileWriter(fullName);
            writer.write("안녕하세요. \n Java 파일 입출력 예제 입니다.");
            writer.close();

            // 파일 정보를 메모리에 올리기
            reader = new FileReader(fullName);

            // 메모리의 정보를 행단위로 불러들이기 위한 클래스 생성
            br = new BufferedReader(reader);

            // 행단위로 가져온 문장을 담을 변수 선언
            String line;

            // 행단위로 데이터 추출하여 출력하기
            //  - readLine(): 행단위로 읽어들이기
            while ((line = br.readLine()) != null) { 
                // 문자열 객체가 존재한다면. 반복 수행
                System.out.println("[출력 내용]: " + line);
            }

            // 사용한 자원 반환하기
            //  - 버퍼 클래스만 반환하면 자동으로 반환됨
            br.close();

        } catch (IOException e) {
            System.out.println("파일 처리 중 오류가 발생하였습니다. : " + e.getMessage());
            System.out.println("파일 처리 중 오류가 발생하였습니다. : " + e.toString());
        }
    }
}
