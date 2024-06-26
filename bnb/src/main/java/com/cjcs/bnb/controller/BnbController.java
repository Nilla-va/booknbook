package com.cjcs.bnb.controller;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.cjcs.bnb.dao.CategoryDao;
import com.cjcs.bnb.dao.MemberDao;
import com.cjcs.bnb.dao.OrderDao;
import com.cjcs.bnb.dao.PurchaseDao;
import com.cjcs.bnb.dao.RentalDao;
import com.cjcs.bnb.dao.ReportBoardDao;
import com.cjcs.bnb.dto.BookDto;
import com.cjcs.bnb.dto.MemberDto;
import com.cjcs.bnb.dto.ReportBoardDto;
import com.cjcs.bnb.dto.SearchDto;
import com.cjcs.bnb.service.BoardService;
import com.cjcs.bnb.service.BookService;
import com.cjcs.bnb.service.MainService;
import com.cjcs.bnb.service.MemberService;
import com.cjcs.bnb.service.RentalService;
import com.cjcs.bnb.service.SellerService;

import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class BnbController {

    @Autowired
    private BoardService bSer;
    @Autowired
    private RentalService rSer;
    @Autowired
    private SellerService sSer;
    @Autowired
    private BookService bookService;

    @Autowired
    private MemberDao mDao;

    @Autowired
    private OrderDao oDao;
    @Autowired
    private RentalDao rDao;
    @Autowired
    private PurchaseDao pDao;
    @Autowired
    private ReportBoardDao rbDao;
    @Autowired
    private CategoryDao categoryDao;
    @Autowired
    private MainService mainService;
    @Autowired
    private BoardService boardService;



    // 메인
    @GetMapping("/")
    public String main(Model model) {
        List<MemberDto> mainInfos = mainService.getMainInfos();
        List<BookDto> books = bookService.getDistinctBooks();
        List<BookDto> bookrandom = bookService.getRandomBooks();

        Map<String, Object> randomSellerWithFile = sSer.getRandomSellerWithFile(); // 랜덤 서점과 파일 정보 가져오기

        model.addAttribute("books", books);
        model.addAttribute("bookrandom", bookrandom);
        model.addAttribute("mainInfos", mainInfos);
        model.addAttribute("randomSeller", randomSellerWithFile.get("seller")); // 랜덤 서점 정보를 모델에 추가
        model.addAttribute("randomFile", randomSellerWithFile.get("file")); // 랜덤 파일 정보를 모델에 추가

        return "main";
    }

    // 지도
    @RequestMapping(value = "/map", method = RequestMethod.GET)
    public String map() {
        return "/map/map";
    }

    // 매일밤 12시 실행할 작업
    @Scheduled(cron = "1 0 0 ? * *")
    public void dailyCheck() {

        // 대여관련
        rSer.updateLatefee(); // 연체료 업데이트 후
        rSer.checkReturn(); // 신규연체 처리

        // 대여예약관련
        rSer.checkReservation(); // 대여예약 처리

    }

    // 여기부터 관리자페이지

    @GetMapping("/admin") // 관리자페이지 홈
    public String admin(Model model) {

        // 카테고리
        HashMap<String, List<HashMap<String, String>>> categoryNames = new HashMap<>();

        List<BookDto> categoryNamesM = categoryDao.listMediumCategories();

        for (BookDto categoryNameM : categoryNamesM) {

            String nameM = categoryNameM.getCategory_m();
            List<HashMap<String, String>> NamesS = categoryDao.listSmallCategoryNames(categoryNameM.getCategory_m_id());

            categoryNames.put(nameM, NamesS);
        }

        model.addAttribute("categoryNames", categoryNames);

        // 통계
        int num_of_seller = mDao.countSellers(null);
        int num_of_customer = mDao.countCustomers(null);
        int num_of_order = ((BigDecimal) oDao.countAllOrders().get("num_of_orders")).intValue();
        int num_of_rentalItem = ((BigDecimal) rDao.countAllRentalItems().get("num_of_rentals")).intValue();
        int num_of_purchaseItem = ((BigDecimal) pDao.countAllPurchaseItems().get("num_of_purchases")).intValue();

        model.addAttribute("num_of_seller", num_of_seller);
        model.addAttribute("num_of_customer", num_of_customer);
        model.addAttribute("num_of_order", num_of_order);
        model.addAttribute("num_of_rentalItem", num_of_rentalItem);
        model.addAttribute("num_of_purchaseItem", num_of_purchaseItem);

        // 상습연체범 (기간내3회이상)
        List<HashMap<String, String>> blackList = rDao.getCustomerBlackList(3);
        model.addAttribute("blackList", blackList);

        return "admin/admin";
    }

    @GetMapping("/admin/categoryadd") // 카테고리(중/소) 추가
    public String addCategory(@RequestParam HashMap<String, String> category, RedirectAttributes rttr) {

        String category_m_id = category.get("category_m_id");

        try {

            if (category_m_id == null) {

                category_m_id = categoryDao.getMediumCategoryIdByName(category.get("category_m"));
                categoryDao.addSmallCategory(category_m_id, category.get("category_s_id"), category.get("category_s"));

            } else {

                categoryDao.addMediumCategory(category_m_id, category.get("category_m"));
            }

        } catch (Exception e) {

            rttr.addFlashAttribute("msg", "이미 존재하는 카테고리ID입니다.");
        }

        return "redirect:/admin";
    }

    @GetMapping("/admin/categorymdelete/{category_m}") // 카테고리(중) 삭제
    public String deleteCategoryM(@PathVariable String category_m) {

        String category_m_id = categoryDao.getMediumCategoryIdByName(category_m);
        categoryDao.deleteMediumCategory(category_m_id);

        return "redirect:/admin";
    }

    @GetMapping("/admin/categorysdelete/{category_s_id}") // 카테고리(소) 삭제
    public String deleteCategoryS(@PathVariable String category_s_id) {

        log.info("s_id:{}", category_s_id);
        categoryDao.deleteSmallCategory(category_s_id);

        return "redirect:/admin";
    }

    @GetMapping("/admin/customerlist") // 일반회원리스트
    public String adminCustomerList(SearchDto sDto, Model model, HttpSession session) {

        List<MemberDto> customerList = mDao.getCustomerListByKeyword(sDto);
        String pageHtml = bSer.getPageboxHtml(sDto, "/admin/customerlist");

        if (customerList != null) {

            for (MemberDto mDto : customerList) {

                int overdues = rDao.countLateReturnsByCId(mDto.getM_id());
                mDto.setOverdues(overdues);
            }

            session.setAttribute("pageNum", sDto.getPageNum());

            if (sDto.getColname() != null) {
                session.setAttribute("sDto", sDto);
            } else {
                session.removeAttribute("sDto");
            }

            model.addAttribute("customerList", customerList);
            model.addAttribute("pageHtml", pageHtml);

        }

        return "admin/adminCustomerList";
    }

    @GetMapping("/admin/sellerlist") // 서점회원리스트
    public String adminSellerList(SearchDto sDto, Model model, HttpSession session) {

        List<MemberDto> sellerList = mDao.getSellerListByKeyword(sDto);
        log.info("sellerList:{}", sellerList.size());

        String pageHtml = bSer.getPageboxHtml(sDto, "/admin/sellerlist");

        if (sellerList != null) {

            session.setAttribute("pageNum", sDto.getPageNum());

            if (sDto.getColname() != null) {
                session.setAttribute("sDto", sDto);
            } else {
                session.removeAttribute("sDto");
            }

            model.addAttribute("sellerList", sellerList);
            model.addAttribute("pageHtml", pageHtml);
        }

        return "admin/adminSellerList";
    }

    @GetMapping("/admin/reportlist") // 제보글리스트
    public String adminReportList(SearchDto sDto, Model model, HttpSession session) {

        List<ReportBoardDto> reportList = rbDao.getReportListByKeyword(sDto);
        log.info("reportList:{}", reportList);
        log.info("reportList size:{}", reportList.size());

        String pageHtml = bSer.getPageboxHtml(sDto, "/admin/reportlist");

        if (reportList != null) {

            session.setAttribute("pageNum", sDto.getPageNum());

            if (sDto.getColname() != null) {
                session.setAttribute("sDto", sDto);
            } else {
                session.removeAttribute("sDto");
            }

            model.addAttribute("reportList", reportList);
            model.addAttribute("pageHtml", pageHtml);
        }

        return "admin/adminReportList";
    }

    @GetMapping("/admin/reportdetail/{report_id}") // 제보글상세보기
    public String adminReportDetail(@PathVariable("report_id") int report_id, Model model) {

        ReportBoardDto rbDto = rbDao.getReportByRId(report_id);
        log.info("rbDto:{}", rbDto);

        model.addAttribute("rbDto", rbDto);

        return "admin/adminReportDetail";
    }

    @GetMapping("admin/reportdelete/{report_id}") // 제보글삭제
    public String adminReportDelete(@PathVariable("report_id") int report_id, Model model) {

        rbDao.deleteReportByRId(report_id);

        return "redirect:/admin/reportlist";
    }

    @GetMapping("/report")
    public String report() {
        return "report/report";
    }
    
    @PostMapping("/report")
    public String report(@ModelAttribute ReportBoardDto reportBoardDto, RedirectAttributes redirectAttributes) {
        boardService.insertReport(reportBoardDto);
        // redirectAttributes.addFlashAttribute("message", "성공적으로 글이 올라갔습니다.");
        return "redirect:/"; 
    }


}
