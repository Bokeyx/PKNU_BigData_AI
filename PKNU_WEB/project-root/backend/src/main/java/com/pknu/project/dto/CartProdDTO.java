package com.pknu.project.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data // getter, setter, ToString() 모두 한번에 처리
@NoArgsConstructor
@AllArgsConstructor
public class CartProdDTO {
    private String cartNo;
    private String cartProd;
    private int cartQty;
    private String cartMember;

    private String prodName;
    private int prodPrice;
    private int prodSale;
}
