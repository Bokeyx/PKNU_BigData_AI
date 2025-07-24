package com.pknu.project.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.pknu.project.dto.CartDTO;
import com.pknu.project.dto.CartMemberDTO;
import com.pknu.project.dto.CartProdDTO;
import com.pknu.project.exception.ResourceNotFoundException;
import com.pknu.project.model.Cart;
import com.pknu.project.model.CartPk;
import com.pknu.project.repository.CartRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * <예외처리>
 *  - 예외처리가 필요한 기능 : 상세조회, 입력, 수정, 삭제
 *    -> RunTime(실행 시점) 시에 발생하는 오류 처리...
 *  - 보통 Service 클래스에서 처리함
 *  
 * <처리 순서>
 *  - 1. 사용자 정의 예외 클래스를 생성 : 오류 확인 편리함
 *  - 2. 사용자 정의 예외 클래스를 -> Back-end 전역적으로 사용할 수 있도록 처리
 */


/***
 * Repository 클래스에게 세부 지시를 내리는 클래스
 *  - CRUD(입력/수정/삭제/조회) 메소드를 호출합니다.
 */

// 서비스클래스임을 정의함
@Service
@RequiredArgsConstructor // 서버실행시에 미리 생성해야하는 생성자를 호출하여 생성시킴
@Slf4j // 터미널 창에 진행중인 로그 남기기
public class CartService {
    // Repository 클래스 선언만 해놓기, final로 선언
    private final CartRepository cartRepository;

    // 생성자 정의하기
    // 생성할 때 사용할 클래스를 생성하여 사용할 수 있도록 함(DI, 의존성 주입이 되어 있음)
    // @Autowired
    // public CartService(CartRepository cartRepository){
    //     this.cartRepository = cartRepository;
    // }


    /**
     * <DTO를 Entity로, Entity를 DTO로 변환하는 메소드(함수) 정의>
     *  - Controller로부터 DTO를 전달받은 경우
     *    -> Entity로 변환하여 Repository에 전달
     * 
     *  - Repository로부터 Entiry로 전달받은 경우
     *    -> DTO로 변환하여 Controller에 전달
     */

    // Entiry를 DTO로 변환하는 메소드 정의
    private CartDTO convertToDTO(Cart cart){
        // .builder() : CartDTO 클래스에서 정의한 Builder 클래스 자원 활용(생성자)
        return CartDTO.builder()
                // Cart Entiry 내에 cart_no의 값을 추출하여 CartDTO의 cart_no에 넣기
                .cart_no(cart.getCart_no())
                // Cart Entiry 내에 cart_member의 값을 추출하여 CartDTO의 cart_member에 넣기
                .cart_member(cart.getCart_member())
                // Cart Entiry 내에 cart_prod의 값을 추출하여 CartDTO의 cart_prod에 넣기
                .cart_prod(cart.getCart_prod())
                // Cart Entiry 내에 cart_qty의 값을 추출하여 CartDTO의 cart_qty에 넣기
                .cart_qty(cart.getCart_qty())
                // 위에 설정한 필드(컬럼)들을 사용하여 CartDTO 객체를 생성
                .build();
    }

    // DTO를 Entiry로 변환하는 메소드 정의
    private Cart convertToEntity(CartDTO dto){
        // .builder() : Cart 클래스에서 정의한 Builder 클래스 자원 활용(생성자)
        return Cart.builder()
                // CartDTO 내에 cart_no의 값을 추출하여 Cart의 cart_no에 넣기
                .cart_no(dto.getCart_no())
                // CartDTO 내에 cart_member의 값을 추출하여 Cart의 cart_member에 넣기
                .cart_member(dto.getCart_member())
                // CartDTO 내에 cart_prod의 값을 추출하여 Cart의 cart_prod에 넣기
                .cart_prod(dto.getCart_prod())
                // CartDTO 내에 cart_qty의 값을 추출하여 Cart의 cart_qty에 넣기
                .cart_qty(dto.getCart_qty())
                // 위에 설정한 필드(컬럼)들을 사용하여 Cart 객체를 생성
                .build();
    }

    /**
     * 주문내역 전체 정보 조회하기 비즈니스 로직
     * @return List<Day6001_cart>
     */
    // public List<Cart> getCartList(){
    //     return this.cartRepository.findAll();
    // }

    // Controller에 리턴할 때는 DTO로 변환해서 반환
    public List<CartDTO> getCartList(){
        return this.cartRepository.findAll()
                // - stream() 메소드
                //   : 컬렉션(Collection) 또는 배열(Array)들을
                //      -> 함수형 방식으로 처리할 수 있도록 해주는 자원이 있음
                //      -> 함수형 방식을 사용하겠다는 의미적 선언임
                //   : 문자열 스트리밍(streaming)과는 무관함
                .stream()
                // 함수형 방식 시작
                //  - map()함수 내에 처리하고자하는 메소드(기능) 정의
                //  - 실무에서 가장 많이 사용되는 방식(DTO <-> Entity)
                // - this::는 현재 클래스 내에 있는 멤버를 의미합니다.
                // - fineAll()의 결과는 List<Cart>로 리스트 내에 있는 
                //   -> 모든 Cart들을 DTO로 변환하는 작업을 합니다.
                .map(this::convertToDTO)
                // - 변환된 모든 DTO들을 List로 다시 묶는 작업을 합니다.
                .collect(Collectors.toList());
    }

    
    //////////////////////// paging 처리 ///////////////////
    // Controller에 리턴할 때는 DTO로 변환해서 반환
    public Page<CartDTO> getCartListPaging(int page, int size){
        // PageRequest 객체 생성
        Pageable pageable = PageRequest.of(page, size); 

        // 페이징 처리된 Cart 목록 조회
        Page<Cart> cartPage = cartRepository.findAll(pageable); 

        // Cart 엔티티를 CartDTO 변환
        return cartPage.map(cart -> convertToDTO(cart));
    }


    
    /***
     * 주문내역 상세 조회하기
     * -------------------------
     * @param cart_no
     * @param cart_member
     * @return Cart
     */
    public CartDTO getCartView(String cart_no, String cart_prod){
        // Optional : 객체를 감싸서 null 체크를 편리하게하는 클래스
        // Optional : 객체를 감싸서 null 체크를 편리하게하는 클래스

        // Optional<Cart> cart = this.cartRepository.findById(new CartPk(cart_no, cart_prod));

        // // 조회결과가 있다면...
        // // - isPresent() : Optional에 담겨있는 객체에 데이터가 있는 경우..
        // if(cart.isPresent()){
            //     // 터미널에 log 메시지 남기기
            //     log.info("주문 내역[{}]의 정보를 정상적으로 조회하였습니다..........", cart_no);
            
            //     // get() : Optional객체에 담겨있는 Cart 객체를 추출합니다.
            //     return convertToDTO(cart.get());
            
            // }else{
                //     // 조회결과가 없다면...
                //     return null;
                // }

        /**
         * Optional : Entity객체를 감싸서 null 체크를 편리하게하는 클래스
         *          : Entity를 추출하기 위해서는 get() 메소드를 사용
         *          : findById()를 통해 직접 Entity로 받아서 사용할 수 있음
         *            -> 단, 예외 처리(orElseThrow)를 해야함
         *               (현업에서 사용되는 방식 입니다.)
         */
        Cart cart = this.cartRepository.findById(new CartPk(cart_no, cart_prod))
                        // 조회 결과가 있으면, Entity를 반환하고, 
                        // 조회 결과가 없으면, 예외를 발생시킴
                        .orElseThrow(() -> new ResourceNotFoundException
                            ("### [해당 정보가 존재하지 않습니다.] : getCartView(%s, %s) ###".formatted(cart_no, cart_prod)));
        
        return convertToDTO(cart);
    }

    // /***
    //  * 주문내역정보 수정하기 (Get 전송방식)
    //  * -------------------------
    //  * @param cart_no (PK)
    //  * @param cart_qty (수정할 데이터)
    //  * @return String msg
    //  */
    // public String setCartUpdateGet(String cart_no, String cart_member, int cart_qty){
    //     // 파라메터 cart_no에 대한 상세정보 조회하기(1건 조회)
    //     // - 변수명 : optionalCard
    //     Optional<Cart> optionalCard = this.cartRepository.findById(new CartPk(cart_no, cart_member));

    //     // 조회결과가 있는지 없는지 확인
    //     if(optionalCard.isPresent()){
    //         // 조회결과가 있으면 수정 진행
    //         // - Optional 클래스에서 cart 클래스 추출하기 (변수명 : cart)
    //         //   -- 수정할 cart_no에 대한 주문내역정보 1건이 담겨져 있습니다.
    //         Cart cart = optionalCard.get();

    //         // 해당 주문내역의 정보에서 이름만 변경하기
    //         cart.setCart_qty(cart_qty);

    //         // DB에 수정 반영하기
    //         this.cartRepository.save(cart);

    //         // 터미널에 출력
    //         log.info("주문내역[{}]에 대하여 이름[{}]으로 정상 수정 되었습니다!!!", cart_no, cart_qty);

    //         return "주문내역[%s]에 대하여 정상적으로 수정 되었습니다!!!".formatted(cart_no);

    //     }else{
    //         return "해당 주문내역[%s]이 존재하지 않습니다!!".formatted(cart_no);
    //     }
    // }

    /***
     * 주문내역정보 수정하기 (Post 전송방식)
     * -------------------------
     * @param cart_no (PK)
     * @param cart_qty (수정할 데이터)
     * @return String msg
     */
    public CartDTO setCartUpdate(String cart_no, String cart_prod, int cart_qty){

        Cart cart = this.cartRepository.findById(new CartPk(cart_no, cart_prod))
                        .orElseThrow(() -> new ResourceNotFoundException
                                ("### [해당 정보가 존재하지 않습니다.] : getCartUpdate(%s, %s) ###".formatted(cart_no, 
                                                                                                              cart_prod)));
        
        // 조회한 Entity에 수정할 값에 대한 컬럼을 변경(setter 사용)
        cart.setCart_qty(cart_qty);

        // 수정 시키기
        return convertToDTO(this.cartRepository.save(cart));
    }


    /***
     * 주문내역 정보 삭제하기
     * --------------------------
     * @param cart_no (PK)
     * @return String msg
     */
    public void setCartDelete(String cart_no, String cart_prod){

        Cart cart = this.cartRepository.findById(new CartPk(cart_no, cart_prod))
                        .orElseThrow(() -> new ResourceNotFoundException
                                ("### [해당 정보가 존재하지 않습니다.] : getCartDelete(%s, %s) ###".formatted(cart_no, cart_prod)));
        
        // 삭제 처리하기
        this.cartRepository.delete(cart);
    }

    /***
     * 주문내역 삭제하기
     * ----------------------------
     * @param cart
     * @return String
     */
    public CartDTO setCartInsert(CartDTO cartDTO){      
        // 주문내역 정보 입력 처리하기
        return convertToDTO(this.cartRepository.save(convertToEntity(cartDTO)));
    }

    /****************************************************
     * Join 처리
     ****************************************************/

    // Cart와 Member의 Inner Join 결과를 조회하는 메소드 정의
    public List<CartMemberDTO> getCartMemberData(){
        return this.cartRepository.findCartMemberData();
    }

    // Cart와 Prod의 Inner Join 결과를 조회하는 메소드 정의
    public List<CartProdDTO> getCartProdData(){
        return this.cartRepository.findAllWithProduct();
    }
    
    // Cart와 Prod의 Inner Join 결과를 조회하는 메소드 정의
    //  -  단, 전달 받은 상품코드에 대해서만 조회
    public List<CartProdDTO> getByProdId(String prodId){
        return this.cartRepository.findByProdId(prodId);
    }

    // Cart, Member, Prod의 Inner Join 결과를 조회하는 메소드 정의
    public List<CartProdDTO> getAllCarMemberProdJoin(){
        return this.cartRepository.findCartMemberProdJoinAll();
    }
}

