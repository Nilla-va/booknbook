package com.cjcs.bnb.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.PathVariable;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


import com.cjcs.bnb.service.FileService;

import com.cjcs.bnb.dto.MemberDto;

import com.cjcs.bnb.service.MemberService;
import com.cjcs.bnb.service.NotificationService;
import com.cjcs.bnb.service.OrderService;
import com.cjcs.bnb.service.PurchaseService;
import com.cjcs.bnb.service.RentalService;

import org.springframework.ui.Model;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/seller")
public class SellerPageController {

    @Autowired
    private MemberService mSer;

    @Autowired
    private PurchaseService pSer;

    @Autowired
    private RentalService rSer;

    @Autowired
    private OrderService oSer;


    @Autowired
    private FileService fileService; // MyBatis mapper

    // 서점 정보 페이지
    @GetMapping
    public String sellerpage() {

        return "seller/sellerInfo";
    }

    // 서점 정보 수정 페이지
    @GetMapping("/seller/settings/account")
    public String editSeller(@PathVariable String sellerId) {
    
        // TODO: 필요한 로직 (예: 서점 정보 로드)
        return "seller/sellerInfoDetail"; // 'editSeller'는 서점 정보를 수정하는 HTML 페이지를 가리킵니다.
    }

    @GetMapping("/main")
    public String sellermain(String s_id, String rr_s_id, Model model) {

        int getTodaySellCnt = oSer.getTodaySellCnt(s_id);
        model.addAttribute("SellCnt", getTodaySellCnt);

        int getTodayRentCnt = oSer.getTodayRentCnt(s_id);
        model.addAttribute("RentCnt", getTodayRentCnt);

        int getTodayRentResCnt = oSer.getTodayRentResCnt(rr_s_id);
        model.addAttribute("RentResCnt", getTodayRentResCnt);

        int TodayOrderCnt = getTodaySellCnt + getTodayRentCnt + getTodayRentResCnt;
        model.addAttribute("OrderCnt", TodayOrderCnt);

        int getTodayDeliveryPrepare = oSer.getTodayDeliveryPrepare(s_id);
        model.addAttribute("DeliPre", getTodayDeliveryPrepare);
        
        int getTodayDeliverShip = oSer.getTodayDeliverShip(s_id);
        model.addAttribute("DeliShip", getTodayDeliverShip);

        int getTodayDeliverComplete = oSer.getTodayDeliverComplete(s_id);
        model.addAttribute("DeliComplete", getTodayDeliverComplete);

        int getBookmarkMemberCnt = oSer.getBookmarkMemberCnt(s_id);
        model.addAttribute("BookmarkMemCnt", getBookmarkMemberCnt);

        int getMonthCancelRequest = oSer.getMonthCancelRequest(s_id);
        model.addAttribute("CanelCnt", getMonthCancelRequest);
        
        

        return "seller/sellerMain";
    }

    @PostMapping("/alert")
    public String selleralert() {
        return "seller/selleralert";
    }

    @GetMapping("/infoupdate")
    public String sellerinfoupdate() {
        return "seller/sellerinfoupdate";
    }
    
    // @PostMapping("/infoupdate")
    // public String sellerinfoupdate(MemberDto mDto) {
    //     boolean result = mSer.updateinfo(mDto);
    //     if (result) {
    //         alert("정보가 수정되었습니다.");
    //     } else {
    //         alert("정보 수정에 실패했습니다.");
    //     }
    //     return "sellerinfoupdate";
    // }
    
    @GetMapping("/csmember")
    public String sellercsmember(){
        return "seller/sellerCSMember";
    }

    @GetMapping("/book/list")
    public String sellerbooklist(){
        return "seller/sellerBookList";
    }

    @GetMapping("/book/add")
    public String sellerbookadd(){
        return "seller/sellerBookAdd";
    }

    @GetMapping("/book/detail")
    public String sellerbookdetail(){
        return "seller/sellerBookDetail";
    }
    

    @GetMapping("/rent/reserve")
    public String sellerrentreserve(){
        return "seller/sellerRentReserve";
    }

    @GetMapping("/rent/curr")
    public String sellerrentcurr(){
        return "seller/sellerRentCurr";
    }

    @GetMapping("/rent/return")
    public String sellerrentreturn(){
        return "seller/sellerRentReturn";
    }
    
    @GetMapping("/sell/history")
    public String sellersellhistory(){
        return "seller/sellerSellHistory";
    }

    @GetMapping("/sell/cancel")
    public String sellersellcancel(){
        return "seller/sellerSellCancel";
    }

    @GetMapping("/return/manage")
    public String sellerreturnmanage(){
        return "seller/sellerReturnManage";
    }

    @GetMapping("/account")
    public String selleraccount(){
        return "seller/sellerAccount";
    }
}

