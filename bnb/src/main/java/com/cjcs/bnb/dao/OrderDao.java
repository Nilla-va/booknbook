package com.cjcs.bnb.dao;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.cjcs.bnb.dto.CartDto;
import com.cjcs.bnb.dto.SearchDto;

@Mapper
public interface OrderDao {

    // 킹효진

    int addToCart(CartDto cartDto);

    int updateCartItem(CartDto cartDto);

    CartDto findPurCartItem(CartDto cartDto);

    CartDto findRentCartItem(CartDto cartDto);

    CartDto insertCartItem(CartDto cartDto);

    //수희
    public List<CartDto> getCartByCId(String c_id);
    public CartDto getCartByCartId(Integer cart_id);
    public List<CartDto> getPurchaseCartByCId(String c_id);
    public List<CartDto> getRentalCartByCId(String c_id);

    public int deleteCartItem(int cart_id);

    public int updateCartAmount(int cart_id, int cart_amount);
    public int updateCartRentalPeriod(int cart_id, int cart_rentalperiod);

    public int addOrderSelectKey(HashMap<String, Object> orderMap);

    public List<HashMap<String, String>> getOrderListGroupByOId(String c_id);
    public List<HashMap<String, String>> getOrderListByDateRange(SearchDto sDto);
    public HashMap<String, Object> getOrderInfoByOId(int o_id);

    public Integer countOrdersByDateRange(SearchDto sDto);
    public Integer countAllOrders();
    public Integer countAllOrdercancels();

    // 예림
    public int getTodaySellCnt(String s_id);

    public int getTodayRentCnt(String s_id);

    public int getTodayRentResCnt(String rr_s_id);

    public int getTodayDeliveryPrepare(String s_id);

    public int getTodayDeliverShip(String s_id);

    public int getTodayDeliverComplete(String s_id);

    public int getMonthCancelRequest(String s_id);

    public int getMonthReturnRequest(String s_id);

    public List<Integer> CalculateRent(String Start_Date, String End_Date);

    public List<Integer> CalculateLate(String Start_Date, String End_Date);

    public List<Integer> CalculateSell(String Start_Date, String End_Date);

    public List<Integer> CalculateReturn(String Start_Date, String End_Date);

}
