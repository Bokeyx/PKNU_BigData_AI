package com.pknu.project.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

//  -> 사용자의 요청과 응답을 담당하는 라이브러리
// ** @RestController : 최초 진입점을 의미하는 Controller 클래스를 호출하겠다는 의미
//              : Rest는 Rest Api를 의미하며, 프론트엔드가 별도로 분리되어있다는 의미
@RestController
public class Day5901_IndexController {
    
    /***
     * 요청 URL : http://localhost:8080/index에 대한 처리 메소드
     * @return
     */
    // @GetMapping(path="/index")
    // public String root(){
    //     return "<h1>스프링부트 메인 메이지 입니다.</h1>";
    // }

    /***
     * path의 root 경로
     *  - "/" : http:localhost:8080
     *  - "/" 없이 : http:localhost:8080
     *  - 위 2개 모두 같은 url을 의미함
     *  - 따라서 메소드는 한개만 지정 가능, path경로도 한가지만 사용가능
     * @return
     */
    // @GetMapping(path="/")
    // public String root2(){
    //     return "<h1>스프링부트 메인 메이지 입니다.2222</h1>";
    // }

    /***
     * 해당 프로젝트의 root 경로가 됨
     *  - 사이트의 시작 페이지를 의미함(index 페이지)
     * @return
     */
    @GetMapping(path={"/", "/index", "/index.html", "/index.jsp", "/index.php"})
    public String root(){        
        // return "<h1>스프링부트 메인 메이지 입니다.3333</h1>";

        // 버퍼 메모리에 문자열을 행단위로 작성하여 문자열 변수처럼 사용하는 객체
        StringBuffer sb = new StringBuffer();

        sb.append("<h1>스프링부트 메인 메이지 입니다.</h1>");
        sb.append("<hr/>");
        sb.append("<table border=1 width=500>");
        sb.append("  <tr><th>페이지 정보</th><th>바로가기 링크</th></tr>");
        sb.append("  <tr><td>숫자 파라메터</td><td><a href='/path_variable/3'>바로가기</a></td></tr>");
        sb.append("  <tr><td>파라메터1개</td><td><a href='/param?mem_id=b001'>바로가기</a></td></tr>");
        sb.append("  <tr><td>파라메터2개</td><td><a href='/param2?mem_id=b001&mem_name=김유신'>바로가기</a></td></tr>");
        // 회원관리 URL
        sb.append("  <tr><td>회원전체조회</td><td><a href='/member/list'>바로가기</a></td></tr>");
        sb.append("  <tr><td>회원상세조회</td><td><a href='/member/view?mem_id=a001'>바로가기</a></td></tr>");
        sb.append("  <tr><td>회원정보입력</td><td><a href='/member/insert'>바로가기</a></td></tr>");
        sb.append("  <tr><td>회원정보수정</td><td><a href='/member/update?mem_id=a001&mem_name=홍길동'>바로가기</a></td></tr>");
        sb.append("  <tr><td>회원정보삭제</td><td><a href='/member/delete?mem_id=x001'>바로가기</a></td></tr>");
        sb.append("</table>");

        // 버퍼 메모리에 저장된 문자열을 데이터를 
        //  - 문자열 타입(toString())으로 변환하여 전달
        return sb.toString();
    }

    /***
     * URL 패스 경로 뒤에 숫자를 넣어서 처리도 가능
     *  - http://localhost:8080/path_variable/숫자
     *  - @PathVariable : 경로 뒤의 숫자를 추출하는 어노테이션(라이브러리)
     * @param id
     * @return
     */
    @GetMapping(path="/path_variable/{id}")
    public String pathVariable(@PathVariable int id){
        return "숫자 파라메터를 받아서 처리하기 : " + id;
    }

    /***
     * - 사용자 폼 데이터를 get방식으로 전송 받을 때 사용하는 방식
     * - http://localhost:8080/param?mem_id=a001
     * - @RequestParam : 사용자가 전달(요청)하기 위해 전달해준 정보를 의미함
     *                 : 사용자 브라우저에서 key = value의 형태로 서버로 전송됩니다.
     *                 : 서버에서는 key 이름을 매개변수로 받습니다. 
     *                   (key는 매개변수명과 동일해야 합니다.)
     * @return
     */
    @GetMapping(path="/param")
    public String paramVariable(@RequestParam String mem_id){
        return "key=value 형태의 파라메터를 받아서 처리 = " + mem_id;
    }


    /***
     * 요청 URL : http://localhost:8080/param2?mem_id=a001&mem_name=홍길동
     * @param mem_id
     * @return
     */
    @GetMapping(path="/param2")
    public String paramVariable2(@RequestParam String mem_id,
                                 @RequestParam String mem_name){
        return "아이디 = %s / 이름 = %s ".formatted(mem_id, mem_name);
    }

    /***
     * 페이지 전환 처리...
     * @return
     */
    @GetMapping(path="/page_change")
    public String pageChange(){
        return "<a href='/'>Home 바로가기</a>";
    }

    /***
     * <스프링부트 프로젝트 신규로 만들기>
     *  - 스프링버전 : 3.4 선택
     *  - Maven 프로젝트 선택
     *  - 도메인명 : com.pknu
     *  - 작업디렉토리, 프로젝트명 : example
     *  - controller에 java 파일 하나 생성하여 
     *    -> 임의 경로(패스) 설정하여 프로젝트 잘 구동되는지 확인..
     *  - 최종 다운로드 위치 : PKNU_SpringBoot\
     *  - VS-Code는 PKNU_SpringBoot\example\ 위치에서 "code ."으로 열어서 진행..
     */


    /**
     * <회원관리 URL패턴 만들기>
     *  - 회원관리 url : /member
     *     -- 회원전체조회 : /list  -> getMemberList()
     *     -- 회원상세조회 : /view  -> getMemberVeiw()
     *     -- 회원정보입력 : /insert -> getMemberInsert()
     *     -- 회원정보수정 : /update -> getMemberUpdate()
     *     -- 회원정보삭제 : /delete -> getMemberDelete()
     * 
     *  - 모두 GetMapping 어노테이션 사용
     *  - 위에 처리 후 결과값(return)은 자유롭게 화면에 출력
     *  - root URL패턴 처리 메소드에 [링크 추가]
     */

    // // 회원전체조회 : /list  -> getMemberList()
    // @GetMapping(path="/member/list")
    // public String getMemberList() {
    //     return "회원 전체 조회 성공!!";
    // }

    // // 회원상세조회 : /view  -> getMemberView()
    // @GetMapping(path="/member/view")
    // public String getMemberView() {
    //     return "회원 상세 조회 성공!!";
    // }

    // // 회원정보입력 : /insert  -> getMemberInset()
    // @GetMapping(path="/member/insert")
    // public String getMemberInset() {
    //     return "회원 정보 입력 성공!!";
    // }

    // // 회원정보수정 : /update  -> getMemberUpdate()
    // @GetMapping(path="/member/update")
    // public String getMemberUpdate() {
    //     return "회원 정보 수정 성공!!";
    // }

    // // 회원정보삭제 : /delete  -> getMemberDetele()
    // @GetMapping(path="/member/delete")
    // public String getMemberDetele() {
    //     return "회원 정보 삭제 성공!!";
    // }

    
}
