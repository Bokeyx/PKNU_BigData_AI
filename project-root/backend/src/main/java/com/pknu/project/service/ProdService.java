package com.pknu.project.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.pknu.project.model.Prod;
import com.pknu.project.repository.ProdRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/***
 * Repository 클래스에게 세부 지시를 내리는 클래스
 *  - CRUD(입력/수정/삭제/조회) 메소드를 호출합니다.
 */

// 서비스클래스임을 정의함
@Service
@RequiredArgsConstructor // 서버실행시에 미리 생성해야하는 생성자를 호출하여 생성시킴
@Slf4j // 터미널 창에 진행중인 로그 남기기
public class ProdService {
    // Repository 클래스 선언만 해놓기, final로 선언
    private final ProdRepository prodRepository;

    // 생성자 정의하기
    // 생성할 때 사용할 클래스를 생성하여 사용할 수 있도록 함(DI, 의존성 주입이 되어 있음)
    // @Autowired
    // public ProdService(ProdRepository prodRepository){
    //     this.prodRepository = prodRepository;
    // }

    /**
     * 상품 전체 정보 조회하기 비즈니스 로직
     * @return List<Day6001_Prod>
     */
    public List<Prod> getProdList(){
        return this.prodRepository.findAll();
    }

    /***
     * 상품 상세 조회하기
     * -------------------------
     * @param prod_id
     * @return Day6001_Prod
     */
    public Prod getProdView(String prod_id){
        // Optional : 객체를 감싸서 null 체크를 편리하게하는 클래스
        Optional<Prod> prod = this.prodRepository.findById(prod_id);

        // 조회결과가 있다면...
        // - isPresent() : Optional에 담겨있는 객체에 데이터가 있는 경우..
        if(prod.isPresent()){
            // 터미널에 log 메시지 남기기
            log.info("상품아이디[{}]의 정보를 정상적으로 조회하였습니다..........", prod_id);

            // get() : Optional객체에 담겨있는 Day6001_Prod 객체를 추출합니다.
            return prod.get();

        }else{
            // 조회결과가 없다면...
            return null;
        }

    }

    /***
     * 상품정보 수정하기 (Get 전송방식)
     * -------------------------
     * @param prod_id (PK)
     * @param prod_name (수정할 데이터)
     * @return String msg
     */
    public String setProdUpdateGet(String prod_id, String prod_name){
        // 파라메터 prod_id에 대한 상세정보 조회하기(1건 조회)
        // - 변수명 : optionalProd
        Optional<Prod> optionalProd = this.prodRepository.findById(prod_id);

        // 조회결과가 있는지 없는지 확인
        if(optionalProd.isPresent()){
            // 조회결과가 있으면 수정 진행
            // - Optional 클래스에서 Prod 클래스 추출하기 (변수명 : prod)
            //   -- 수정할 prod_id에 대한 상품정보 1건이 담겨져 있습니다.
            Prod prod = optionalProd.get();

            // 해당 상품의 정보에서 이름만 변경하기
            prod.setProd_name(prod_name);

            // DB에 수정 반영하기
            this.prodRepository.save(prod);

            // 터미널에 출력
            log.info("상품[{}]에 대하여 이름[{}]으로 정상 수정 되었습니다!!!", prod_id, prod_name);

            return "상품[%s]에 대하여 정상적으로 수정 되었습니다!!!".formatted(prod_id);

        }else{
            return "해당 상품[%s]이 존재하지 않습니다!!".formatted(prod_id);
        }
    }

    /***
     * 상품정보 수정하기 (Post 전송방식)
     * -------------------------
     * @param prod_id (PK)
     * @param prod_name (수정할 데이터)
     * @return String msg
     */
    public String setProdUpdate(Prod p_prod){
        // 파라메터 prod_id에 대한 상세정보 조회하기(1건 조회)
        // - 변수명 : optionalProd
        Optional<Prod> optionalProd = this.prodRepository.findById(p_prod.getProd_id());

        // 조회결과가 있는지 없는지 확인
        if(optionalProd.isPresent()){
            // 조회결과가 있으면 수정 진행
            // - Optional 클래스에서 Prod 클래스 추출하기 (변수명 : prod)
            //   -- 수정할 prod_id에 대한 상품정보 1건이 담겨져 있습니다.
            Prod prod = optionalProd.get();

            // 해당 상품의 정보에서 이름만 변경하기
            prod.setProd_name(p_prod.getProd_name());

            // DB에 수정 반영하기
            this.prodRepository.save(prod);

            // 터미널에 출력
            log.info("상품[{}]에 대하여 이름[{}]으로 정상 수정 되었습니다!!!", p_prod.getProd_id(), 
                                                                                      p_prod.getProd_name());

            return "상품[%s]에 대하여 정상적으로 수정 되었습니다!!!".formatted(p_prod.getProd_id());

        }else{
            return "해당 상품[%s]이 존재하지 않습니다!!".formatted(p_prod.getProd_id());
        }
    }


    /***
     * 상품 정보 삭제하기
     * --------------------------
     * @param prod_id (PK)
     * @return String msg
     */
    public String setProdDelete(String prod_id){
        // 해당 상품 아이디에 대한 데이터가 DB에 존재하는지 여부를 확인하는 메소드
        //  - Repository 클래스의 existsById(상품아이디) 메소드 사용
        //    -- existsById 메소드의 리턴값은 true 아니면 false 입니다.
        //  - 존재하면(true) "상품 아이디[x001] 정보 삭제 완료" 리턴
        //  - 존재하지 않으면(false) "상품 아이디[x001]이 존재하지 않습니다." 리턴
        //  - Controller 클래스에서 해당 메소드 결과값 출력까지 테스트 해보기..
        //     -- 메소드명은 setProdDelete() 사용..
        if(this.prodRepository.existsById(prod_id)){
            // 삭제 처리하기
            //  - 사용하는 메소드 : deleteById(pk값)
            this.prodRepository.deleteById(prod_id);

            log.info("상품 아이디[{}] 정보 삭제 완료", prod_id);
            return "상품 아이디[%s] 정보 삭제 완료".formatted(prod_id);

        }else {
            return "상품 아이디[%s]이 존재하지 않습니다.".formatted(prod_id);
        }
    }


    public String setProdInsert(Prod prod){
        // 상품아이디 중복체크 필요 (중복된 상품아이디가 있으면 저장 시키면 안됨)
        // - 상품 정보가 없으면 -> "상품아이디 [n001]이 정상적으로 입력 되었습니다." 리턴
        // - 상품 정보가 있으면 -> "상품아이디 [n001]은 이미 존재하는 아이디 입니다." 리턴
        // - Controller 클래스에서 서비스 호출하여 리턴값 받아서 출력하는 프로그램까지만 작성...
        //   (파라메터 정의하지 말기, 서버 실행은 하지 말기...)

        // 상품아이디 존재여부 확인
        if(this.prodRepository.existsById(prod.getProd_id())){
            return "상품아이디 [%s]은 이미 존재하는 아이디 입니다.".formatted(prod.getProd_id());
        }
        
        // 상품 정보 입력 처리하기
        this.prodRepository.save(prod);

        return "상품아이디 [%s]이 정상적으로 입력 되었습니다.".formatted(prod.getProd_id());
    }
}
