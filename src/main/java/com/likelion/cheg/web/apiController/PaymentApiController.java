package com.likelion.cheg.web.apiController;

import com.likelion.cheg.web.dto.CMResponse;
import com.likelion.cheg.web.dto.pay.PaymentCancelRequestDto;
import com.siot.IamportRestClient.IamportClient;
import com.siot.IamportRestClient.exception.IamportResponseException;
import com.siot.IamportRestClient.request.CancelData;
import com.siot.IamportRestClient.response.IamportResponse;
import com.siot.IamportRestClient.response.Payment;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;
import java.io.IOException;
import java.util.Locale;

@RequiredArgsConstructor
@RestController
public class PaymentApiController {

    private final IamportClient iamportClient;

    public PaymentApiController() {
        this.iamportClient = new IamportClient("3361655256577743","rHKk028PjZVXOHCBs505RgTmkKCqO6kfLeGYT7TwGsTP8ohXb7RjQX1U8T1p4vVAfSIMJF8WyL3tDjoX");
    }

    //결제 검증
    @ResponseBody
    @RequestMapping(value="/api/verifyIamport/{imp_uid}")
    public IamportResponse<Payment> paymentByImpUid(
            Model model
            , Locale locale
            , HttpSession session
            , @PathVariable(value= "imp_uid") String imp_uid) throws IamportResponseException, IOException {

        return iamportClient.paymentByImpUid(imp_uid);
    }

    //결제 취소
    @PostMapping("/api/cancelPayment")
    public ResponseEntity<CMResponse> cancelPayment(@RequestBody PaymentCancelRequestDto paymentCancelRequestDto){
        try{
            cancelPayment(paymentCancelRequestDto.getImpUid());
            return new ResponseEntity<>(new CMResponse<>(1,"결제 취소 성공",""), HttpStatus.OK);
        }catch(IamportResponseException | IOException e){
            return new ResponseEntity<>(new CMResponse<>(0,"결제 취소 실패",""), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // 결제 취소 API 호출
    @Transactional
    public IamportResponse<Payment> cancelPayment(String impUid) throws IamportResponseException, IOException {
        // 아임포트 API를 이용하여 결제 취소를 요청합니다.
        CancelData cancelData = new CancelData(impUid, true);
        IamportResponse<Payment> response = iamportClient.cancelPaymentByImpUid(cancelData);
        return response;
    }

}