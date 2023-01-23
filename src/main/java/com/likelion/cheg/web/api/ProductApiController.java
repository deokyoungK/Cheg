package com.likelion.cheg.web.api;

import com.likelion.cheg.config.auth.PrincipalDetail;
import com.likelion.cheg.service.ProductService;
import com.likelion.cheg.web.dto.CMResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@RequiredArgsConstructor
@Controller
public class ProductApiController {

    private final ProductService productService;

    @PostMapping("api/{productId}/delete")
    public ResponseEntity deleteProduct(@PathVariable int productId){
        productService.deleteProduct(productId);
        return new ResponseEntity<>(new CMResponseDto<>(1,"상품 삭제 성공",""),HttpStatus.OK);
    }

}
