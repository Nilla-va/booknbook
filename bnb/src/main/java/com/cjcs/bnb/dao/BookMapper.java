package com.cjcs.bnb.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.cjcs.bnb.dto.BookDto;

@Mapper
public interface BookMapper {
    List<BookDto> findAll();

    List<BookDto> bookAllList(@Param("start") int start, @Param("end") int end);

    int countTotalBooks();

    BookDto findByIsbn(String bIsbn);

    BookDto findBookByIsbnAndSellerId(@Param("isbn") String isbn, @Param("sellerId") String sellerId);

    List<BookDto> findByKwPg(@Param("keyword") String keyword, @Param("start") int start, @Param("end") int end);

    int countKeyword(String keyword);

    // 중분류 카테고리 조회
    List<BookDto> findBooksByMediumCategory(@Param("category_m_id") String category_m_id, @Param("start") int start,
            @Param("end") int end);

    int countBooksByMediumCategory(String category_m_id);
}
