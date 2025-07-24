package com.pknu202502.java_base.day58_java_base_07;

import com.pknu202502.java_base.day58_java_base_07.day5801.StudentException;
import com.pknu202502.java_base.day58_java_base_07.day5801.StudentService;
import java.util.Scanner;
/***
 * 컬렉션 및 사용자 정의 예외처리 문제
 * ----------------------------------------
 * <실습문제>
 * 학생 이름과 성적점수를 컬렉션 객체로 관리하는 프로그램 개발
 *  1. List 또는 Map 또는 일반클래스를 자유롭게 활용
 *   - 학번, 이름, 성적점수 저장 관리 (학번 이외에는 중복 허용)
 *   - 학번은 정수값을 저장(중복 허용 안함)
 *   - 일반 클래스를 사용하여 데이터 관리할 경우 별도 폴더를 생성하여 java파일 관리
 *     (폴더명: day5081로 사용)
 *  2. 학생 수 제한: 최대 5명까지만 입력 받기
 *  3. 사용자 입력 방법은 Scanner 클래스 이용
 *  4. while문 이용하여 사용자 정보 받기
 *  5. 사용자 정의 예외 처리하기: 성적점수를 음수로 넣는 경우에 대한 예외 발생하여 처리
 *   -> 사용자 정의 예외 클래스도 day5801 폴더에 java 파일 생성하여 처리
 *  6. 형변환(타입변환)이 필용한 경우 관련 방법은 조별 해결
 *  7. 혹시나 외부 폴더의 파일에 대한 패키지 문제가 발생할 경우에는
 *   -> 하나의 java 파일에 모든 클래스 적용하여 진행하여도 됨
 *  9. 최종 출력 결과
 *   -> 전체 학생정보 출력..
 *   -> 학생들 전체 성적점수에 대한 평균 출력
 */

/**
 * 학생 이름과 성적점수를 컬렉션 객체로 관리하는 프로그램
 */
public class Day5801_Exe {
    public static void main(String[] args) {
        System.out.println("[컬렉션 및 사용자 정의 예외] 문제");
        System.out.println("--------------------------------------------------");

        Scanner sc = new Scanner(System.in);
        StudentService studentService = new StudentService();

        boolean running = true;

        while (running) {
            System.out.println("\n1. 학생 정보 등록");
            System.out.println("2. 전체 학생 목록");
            System.out.println("3. 학생 성적 등록");
            System.out.println("4. 학생 성적 목록");
            System.out.println("5. 평균 성적 출력");
            System.out.println("0. 시스템 종료");
            System.out.print("메뉴 선택: ");
            String input = sc.nextLine().trim();
            int choice;

            try {
                choice = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("숫자를 입력하세요.");
                continue;
            }

            try {
                switch (choice) {
                    case 1 -> {
                        if (studentService.getStudentCount() >= 5) {
                            System.out.println("최대 5명까지만 등록 가능합니다.");
                            break;
                        }
                        System.out.print("학생 학번 입력: ");
                        int studentId = Integer.parseInt(sc.nextLine());
                        System.out.print("학생 이름 입력: ");
                        String studentName = sc.nextLine().trim();
                        studentService.registerStudent(studentId, studentName);
                    }

                    case 2 -> {
                        System.out.println("전체 학생 목록");
                        System.out.println("==================================================");
                        studentService.showAllStudents();
                    }

                    case 3 -> {
                        System.out.print("학생 학번 입력: ");
                        int studentId = Integer.parseInt(sc.nextLine());
                        System.out.print("학생 성적 입력: ");
                        int studentGrade = Integer.parseInt(sc.nextLine());
                        studentService.registerGrade(studentId, studentGrade);
                    }

                    case 4 -> {
                        System.out.println("전체 학생 성적 목록");
                        System.out.println("==================================================");
                        studentService.showAllGrades();
                    }

                    case 5 -> {
                        double avg = studentService.showAverageGrade();
                        System.out.println("전체 평균 성적: " + avg);
                    }

                    case 0 -> {
                        running = false;
                        System.out.println("시스템 종료. 데이터가 저장되었습니다.");
                    }

                    default -> System.out.println("잘못된 메뉴 번호입니다.");
                }
            } catch (StudentException e) {
                System.out.println("[오류] " + e.getMessage());
            } catch (NumberFormatException e) {
                System.out.println("숫자 형식이 잘못되었습니다.");
            } catch (Exception e) {
                System.out.println("알 수 없는 오류 발생: " + e.getMessage());
            }
        }

        sc.close();
    }
}

// // 도메인 클래스
// class StudentInfo {
//     private int studentId;
//     private String studentName;

//     public StudentInfo(int studentId, String studentName) {
//         this.studentId = studentId;
//         this.studentName = studentName;
//     }

//     public int getStudentId() {
//         return studentId;
//     }

//     public String getStudentName() {
//         return studentName;
//     }
// }

// class StudentGrade {
//     private int studentGrade;

//     public StudentGrade(int studentGrade) {
//         this.studentGrade = studentGrade;
//     }

//     public int getStudentGrade() {
//         return studentGrade;
//     }
// }

// 사용자 정의 예외 클래스
// class StudentException extends RuntimeException {
//     public StudentException(String message) {
//         super(message);
//     }
// }

// // 서비스 클래스
// class StudentService {
//     private Map<Integer, StudentInfo> studentMap = new HashMap<>();
//     private Map<Integer, StudentGrade> gradeMap = new HashMap<>();

//     public void registerStudent(int studentId, String studentName) {
//         if (studentMap.containsKey(studentId)) {
//             throw new StudentException("해당 학번은 이미 등록되어 있습니다.");
//         }
//         StudentInfo studentInfo = new StudentInfo(studentId, studentName);
//         studentMap.put(studentId, studentInfo);
//         System.out.println("학생 등록 완료: " + studentName + " (ID: " + studentId + ")");
//     }

//     public void registerGrade(int studentId, int studentGrade) {
//         if (!studentMap.containsKey(studentId)) {
//             throw new StudentException("해당 학번은 등록되지 않았습니다.");
//         }
//         if (studentGrade < 0) {
//             throw new StudentException("성적은 음수일 수 없습니다.");
//         }
//         gradeMap.put(studentId, new StudentGrade(studentGrade));
//         System.out.println("성적 등록 완료: 학번(" + studentId + "), 점수(" + studentGrade + ")");
//     }

//     public void showAllStudents() {
//         if (studentMap.isEmpty()) {
//             System.out.println("등록된 학생이 없습니다.");
//             return;
//         }
//         for (StudentInfo student : studentMap.values()) {
//             System.out.println("학번: " + student.getStudentId() + ", 이름: " + student.getStudentName());
//         }
//     }

//     public void showAllGrades() {
//         if (gradeMap.isEmpty()) {
//             System.out.println("등록된 성적이 없습니다.");
//             return;
//         }
//         for (Map.Entry<Integer, StudentGrade> entry : gradeMap.entrySet()) {
//             StudentInfo info = studentMap.get(entry.getKey());
//             System.out.println("학번: " + entry.getKey() + ", 이름: " + info.getStudentName() + ", 성적: " + entry.getValue().getStudentGrade());
//         }
//     }

//     public double showAverageGrade() {
//         if (gradeMap.isEmpty()) return 0.0;
//         int total = 0;
//         for (StudentGrade g : gradeMap.values()) {
//             total += g.getStudentGrade();
//         }
//         return total / (double) gradeMap.size();
//     }

//     public int getStudentCount() {
//         return studentMap.size();
//     }
// }
