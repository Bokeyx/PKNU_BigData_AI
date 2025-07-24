package com.pknu202502.java_base.day54_java_base_03.src;

import com.pknu202502.java_base.day54_java_base_03.src.java_day54.Book;

/***
 * 참조형 배열 객체(Reference Object Array)
 */

public class Day5403_RefType_ObjectArray {

    /**
     * <참조형 배열 객체>
     *  - 임의 클래스를 배열 타입으로 정의하여 사용하는 방식
     *  - 배열 공간에는 타입으로 정의된 클래스가 저장되는 방식임
     *  - 클래스는 메모리 가지는 주소값을 가지기에
     *   -> 저장되는 값은 주소값 입니다.
     *   -> 주소값을 사용한다고해서 "참조형 배열 객체"라고 칭합니다.
     *      ("클래스 배열"이라고 짧게 칭하기도 합니다.)
     *   -> 각 배열 데이터의 주소값을 참조하여 사용하게 됨
     */

    public static void main(String[] args) {
        System.out.println("[참조형 배열 객체]");
        System.out.println("\n----------------------------------------\n");
        
        // Book 클래스를 배열 타입으로 3개의 객체가 들어갈 수 있도록 배열 생성해 주세요
        // - 배열 이름은: books
        // - 배열 생성시의 new Book[3]의 의미
        // -> 메모리 공간 3개를 생성
        // -> 긱 메모리에 넣을 수 있는 타입은 Book 타입만 가능하다는 의미
        // -> Book 클래스가 생성되는 것이 아니며, 공간의 타입을 정의할 뿐입니다
        Book[] books = new Book[3];

        // 3개의 공간에 Book 객체를 각각 넣어주세요..
        // - title은 자유롭게.
        //   (객체는 곧, 클래스 변수명을 의미함, 주소값이 저장됨)
        Book book1 = new Book("어린 왕자");
        Book book2 = new Book("신데렐라");
        Book book3 = new Book("백설공주");

        books[0] = book1;
        books[1] = book2;
        books[2] = book3;
        
        // books[0] = new Book("어린 왕자");
        // books[1] = new Book("신데렐라");
        // books[2] = new Book("백설공주");

        // books 배열에 있는 각 객체의 title 값을 출력하기
        // - 반복문 이용
        for (int i = 0; i < books.length; i++) {
            System.out.println("책 제목%d: %s".formatted(i + 1, books[i].getTitle()));
        }

        for (Book b : books ) {
            String title = b.getTitle();
            System.out.println("책 제목: %s".formatted(title));
        }

        // Book 클래스를 외부 파일로 작성하기
        // - 디렉토리 위치: java_54day
    }
}

/**
 * Book 클래스 생성하기
 *  - 멤버 변수는 title, 문자열 타입으로, 외부에서 접근 못하게 하빈다.
 *  - 생성자 메소드 만들기: 임의 값을 받아서 -> title에 저장하는 기능 처리
 *  - 외부에서 접근 가능한 getter 함수 생성하기
 */
// class Book {
//     private String title;

//     public Book(String title) {
//         this.title = title;
//     }

//     public String getTitle (){
//         return this.title;
//     }
// }