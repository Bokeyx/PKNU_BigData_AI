package com.pknu.project.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pknu.project.model.Prod;
import com.pknu.project.service.ProdService;

import lombok.RequiredArgsConstructor;

// Controller 클래스임을 정의..
//  :서버 실행시 자동으로 ProdController클래스 생성시킴

// // 상품관리 카테고리 URL 패턴을 분리하여 관리
// //  - /prod 로 요청이 들어오면, ProdController 이 클래스를 호출하도록 정의
// @RequestMapping("/prod")

@RestController
@RequestMapping("/prod")
@RequiredArgsConstructor // 서버실행시에 미리 생성해야하는 생성자를 호출하여 생성시킴
public class ProdController {

    // 서비스 클래스 멤버 객체 선언만 하기 : final로 선언
    private final ProdService prodService;

    // 생성자 정의하기
    // @Autowired
    // public ProdController(ProdService prodService){
    //     this.prodService = prodService;
    // }

    // 상품전체조회 : /list  -> getProdList()
    @GetMapping(path="/list")
    public String getProdList() {
        
        // Service객체를 통해서 상품전체조회 메소드 호출하기
        List<Prod> list = this.prodService.getProdList();

        // 받아온 값(객체)이 없는 경우
        if(list == null) {
            return "전체 상품에 대한 조회 결과가 없습니다.!!";
        }

        // 받아온 값(객체)가 있는 경우 처리
        StringBuffer sb = new StringBuffer();
        sb.append("<h1>상품 전체 목록</h1>");
        sb.append("<hr/>");
        sb.append("<table border=1 width=800>");
        sb.append("   <tr><th>상품아이디</th><th>상품명</th><th>상품 판매가격</th></tr>");

        // 상품전체 정보는 List내에 prod클래스에 포함되어 있음
        // List를 반복하면서, prod를 받아온후, getProd_xxx()를 이용해서 추출
        for (Prod prod : list) {            
            sb.append("   <tr><td>%s</td><td>%s</td><td>%s</td></tr>".formatted(prod.getProd_id(), 
                                                                                prod.getProd_name(), 
                                                                                prod.getProd_sale()));
        }
        
        sb.append("</table>");

        // return "ProdController : 상품 전체 조회 성공!!";
        return sb.toString();
    }

    // 상품상세조회 : /view  -> getProdView()
    // - get방식으로 파라메터 전송 : http://localhost:8080/prod/view?prod_id                                                                              prod.getProd_sale()=a001
    @GetMapping(path="/view")
    public String getProdView(@RequestParam String prod_id) {

        // @RequestParam : get방식으로 전송요청된 모든 파라메터 변수들을 가지고 있음
        //               : 전송시 사용하는 파라메터(key)이름과 매개변수이름과 같아야함

        // 서비스 클래스에게 상세조회 메소드 호출하여 처리시키기..
        Prod prod = this.prodService.getProdView(prod_id);

        // 조회결과가 없을 때 응답할 내용 정의
        if(prod == null){
            return "상품아이디[%s]에 대한 조회결과가 없습니다!!".formatted(prod_id);
        }

        // 조회결과가 있을 때 응답할 내용 정의
        // 받아온 값(객체)가 있는 경우 처리
        StringBuffer sb = new StringBuffer();
        sb.append("<h1>상품 상세 조회</h1>");
        sb.append("<hr/>");
        sb.append("<table border=1 width=800>");
        sb.append("   <tr><th>상품아이디</th><th>상품명</th><th>상품 판매가격</th></tr>");
        
        // 조회결과 추출하여 응답 결과 만들기
        sb.append("   <tr><td>%s</td><td>%s</td><td>%s</td></tr>".formatted(prod.getProd_id(), 
                                                                            prod.getProd_name(), 
                                                                            prod.getProd_sale()));
        sb.append("</table>");

        // return "ProdController : 상품 상세 조회 성공!!";
        return sb.toString();
    }

    // 요청 URL 예시 : http://localhost:8080/prod/delete
    // 상품정보입력 : /insert  -> getProdInset()
    //  - PostMapping : 사용자로부터 전송받는 데이터가 많은 경우에 사용
    //  - RequestBody : post 전송방식으로 사용자 데이터를 받고자 할 때 사용
    //                : 사용자의 입력 form 태그 내의 모든 key:value를 가지고 있습니다.
    //                : RequestBody의 모든 데이터는 Model 클래스와 자동 바인딩 됩니다.
    //                  (@RequestBody Prod prod)
    //                   --> 사용자의 입력 form태그 내에 key 이름은 Model 클래스의 멤버 변수명과 동일해야 합니다.
    @PostMapping(path="/insert")
    public String setProdInset(@RequestBody Prod prod) {
        String msg = this.prodService.setProdInsert(prod);
        return "ProdController : " + msg;
    }
 
    // 상품정보수정 : /update  -> getProdUpdate()
    // - Get방식 요청 URL 예시 : http://localhost:8080/prod/update
    // - post전송방식으로 수정...
    // @GetMapping(path="/update")
    @GetMapping(path="/update_get")
    public String setProdUpdateGet(@RequestParam String prod_id, @RequestParam String prod_name) {

        // 서비스 return 결과 msg 변수가 받아서 처리
        String msg = this.prodService.setProdUpdateGet(prod_id, prod_name);
        return "ProdController : " + msg;
    }
    @PostMapping(path="/update")
    public String setProdUpdate(@RequestBody Prod prod) {

        // 서비스 return 결과 msg 변수가 받아서 처리
        String msg = this.prodService.setProdUpdate(prod);
        return "ProdController : " + msg;
    }

    // 요청 URL 예시 : http://localhost:8080/prod/delete?prod_id=x001
    // 상품정보삭제 : /delete  -> getProdDetele()
    @GetMapping(path="/delete")
    public String setProdDetele(@RequestParam String prod_id) {
        String msg = this.prodService.setProdDelete(prod_id);
        return "ProdController : " + msg;
    }


}
