package com.pknu202502.java_base.day58_java_base_07.day5801;

import java.util.HashMap;
import java.util.Map;


// 서비스 클래스
public class StudentService {
    private Map<Integer, StudentInfo> studentMap = new HashMap<>();
    private Map<Integer, StudentGrade> gradeMap = new HashMap<>();

    public void registerStudent(int studentId, String studentName) {
        if (studentMap.containsKey(studentId)) {
            throw new StudentException("해당 학번은 이미 등록되어 있습니다.");
        }
        StudentInfo studentInfo = new StudentInfo(studentId, studentName);
        studentMap.put(studentId, studentInfo);
        System.out.println("학생 등록 완료: " + studentName + " (ID: " + studentId + ")");
    }

    public void registerGrade(int studentId, int studentGrade) {
        if (!studentMap.containsKey(studentId)) {
            throw new StudentException("해당 학번은 등록되지 않았습니다.");
        }
        if (studentGrade < 0) {
            throw new StudentException("성적은 음수일 수 없습니다.");
        }
        gradeMap.put(studentId, new StudentGrade(studentGrade));
        System.out.println("성적 등록 완료: 학번(" + studentId + "), 점수(" + studentGrade + ")");
    }

    public void showAllStudents() {
        if (studentMap.isEmpty()) {
            System.out.println("등록된 학생이 없습니다.");
            return;
        }
        for (StudentInfo student : studentMap.values()) {
            System.out.println("학번: " + student.getStudentId() + ", 이름: " + student.getStudentName());
        }
    }

    public void showAllGrades() {
        if (gradeMap.isEmpty()) {
            System.out.println("등록된 성적이 없습니다.");
            return;
        }
        for (Map.Entry<Integer, StudentGrade> entry : gradeMap.entrySet()) {
            StudentInfo info = studentMap.get(entry.getKey());
            System.out.println("학번: " + entry.getKey() + ", 이름: " + info.getStudentName() + ", 성적: " + entry.getValue().getStudentGrade());
        }
    }

    public double showAverageGrade() {
        if (gradeMap.isEmpty()) return 0.0;
        int total = 0;
        for (StudentGrade g : gradeMap.values()) {
            total += g.getStudentGrade();
        }
        return total / (double) gradeMap.size();
    }

    public int getStudentCount() {
        return studentMap.size();
    }
}
