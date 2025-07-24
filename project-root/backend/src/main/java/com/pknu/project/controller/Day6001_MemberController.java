package com.pknu.project.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pknu.project.model.Day6001_Member;
import com.pknu.project.service.Day6001_MemberService;

import lombok.RequiredArgsConstructor;

// Controller 클래스임을 정의..
//  :서버 실행시 자동으로 Day6001_MemberController클래스 생성시킴

// // 회원관리 카테고리 URL 패턴을 분리하여 관리
// //  - /member 로 요청이 들어오면, Day6001_MemberController 이 클래스를 호출하도록 정의
// @RequestMapping("/member")

@RestController
@RequestMapping("/member")
@RequiredArgsConstructor // 서버실행시에 미리 생성해야하는 생성자를 호출하여 생성시킴
public class Day6001_MemberController {

    // 서비스 클래스 멤버 객체 선언만 하기 : final로 선언
    private final Day6001_MemberService day6001_MemberService;

    // 생성자 정의하기
    // @Autowired
    // public Day6001_MemberController(Day6001_MemberService day6001_MemberService){
    //     this.day6001_MemberService = day6001_MemberService;
    // }

    // 회원전체조회 : /list  -> getMemberList()
    @GetMapping(path="/list")
    public String getMemberList() {
        
        // Service객체를 통해서 회원전체조회 메소드 호출하기
        List<Day6001_Member> list = this.day6001_MemberService.getMemberList();

        // 받아온 값(객체)이 없는 경우
        if(list == null) {
            return "전체 회원에 대한 조회 결과가 없습니다.!!";
        }

        // 받아온 값(객체)가 있는 경우 처리
        StringBuffer sb = new StringBuffer();
        sb.append("<h1>회원 전체 목록</h1>");
        sb.append("<hr/>");
        sb.append("<table border=1 width=800>");
        sb.append("   <tr><th>아이디</th><th>패스워드</th><th>이름</th></tr>");

        // 회원전체 정보는 List내에 Member클래스에 포함되어 있음
        // List를 반복하면서, Member를 받아온후, getMem_xxx()를 이용해서 추출
        for (Day6001_Member mem : list) {            
            sb.append("   <tr><td>%s</td><td>%s</td><td>%s</td></tr>".formatted(mem.getMem_id(), 
                                                                                mem.getMem_pass(), 
                                                                                mem.getMem_name()));
        }
        
        sb.append("</table>");

        // return "Day6001_MemberController : 회원 전체 조회 성공!!";
        return sb.toString();
    }

    // 회원상세조회 : /view  -> getMemberView()
    // - get방식으로 파라메터 전송 : http://localhost:8080/member/view?mem_id=a001
    @GetMapping(path="/view")
    public String getMemberView(@RequestParam String mem_id) {

        // @RequestParam : get방식으로 전송요청된 모든 파라메터 변수들을 가지고 있음
        //               : 전송시 사용하는 파라메터(key)이름과 매개변수이름과 같아야함

        // 서비스 클래스에게 상세조회 메소드 호출하여 처리시키기..
        Day6001_Member mem = this.day6001_MemberService.getMemberView(mem_id);

        // 조회결과가 없을 때 응답할 내용 정의
        if(mem == null){
            return "회원아이디[%s]에 대한 조회결과가 없습니다!!".formatted(mem_id);
        }

        // 조회결과가 있을 때 응답할 내용 정의
        // 받아온 값(객체)가 있는 경우 처리
        StringBuffer sb = new StringBuffer();
        sb.append("<h1>회원 상세 조회</h1>");
        sb.append("<hr/>");
        sb.append("<table border=1 width=800>");
        sb.append("   <tr><th>아이디</th><th>패스워드</th><th>이름</th></tr>");
        
        // 조회결과 추출하여 응답 결과 만들기
        sb.append("   <tr><td>%s</td><td>%s</td><td>%s</td></tr>".formatted(mem.getMem_id(), 
                                                                            mem.getMem_pass(), 
                                                                            mem.getMem_name()));
        sb.append("</table>");

        // return "Day6001_MemberController : 회원 상세 조회 성공!!";
        return sb.toString();
    }

    // 요청 URL 예시 : http://localhost:8080/member/delete
    // 회원정보입력 : /insert  -> getMemberInset()
    //  - PostMapping : 사용자로부터 전송받는 데이터가 많은 경우에 사용
    //  - RequestBody : post 전송방식으로 사용자 데이터를 받고자 할 때 사용
    //                : 사용자의 입력 form 태그 내의 모든 key:value를 가지고 있습니다.
    //                : RequestBody의 모든 데이터는 Model 클래스와 자동 바인딩 됩니다.
    //                  (@RequestBody Day6001_Member member)
    //                   --> 사용자의 입력 form태그 내에 key 이름은 Model 클래스의 멤버 변수명과 동일해야 합니다.
    @PostMapping(path="/insert")
    public String setMemberInset(@RequestBody Day6001_Member member) {
        String msg = this.day6001_MemberService.setMemberInsert(member);
        return "Day6001_MemberController : " + msg;
    }
 
    // 회원정보수정 : /update  -> getMemberUpdate()
    // - Get방식 요청 URL 예시 : http://localhost:8080/member/update?mem_id=a001&mem_name=홍길동
    // - post전송방식으로 수정...
    // @GetMapping(path="/update")
    @GetMapping(path="/update_get")
    public String setMemberUpdateGet(@RequestParam String mem_id, @RequestParam String mem_name) {

        // 서비스 return 결과 msg 변수가 받아서 처리
        String msg = this.day6001_MemberService.setMemberUpdateGet(mem_id, mem_name);
        return "Day6001_MemberController : " + msg;
    }
    @PostMapping(path="/update")
    public String setMemberUpdate(@RequestBody Day6001_Member member) {

        // 서비스 return 결과 msg 변수가 받아서 처리
        String msg = this.day6001_MemberService.setMemberUpdate(member);
        return "Day6001_MemberController : " + msg;
    }

    // 요청 URL 예시 : http://localhost:8080/member/delete?mem_id=x001
    // 회원정보삭제 : /delete  -> getMemberDetele()
    @GetMapping(path="/delete")
    public String setMemberDetele(@RequestParam String mem_id) {
        String msg = this.day6001_MemberService.setMemberDelete(mem_id);
        return "Day6001_MemberController : " + msg;
    }


}
