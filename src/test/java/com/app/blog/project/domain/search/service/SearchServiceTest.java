package com.app.blog.project.domain.search.service;

import com.app.blog.project.common.dto.PageRes;
import com.app.blog.project.common.type.SearchSourceType;
import com.app.blog.project.common.type.SortType;
import com.app.blog.project.domain.search.dto.SearchBlogReq;
import com.app.blog.project.repository.KeywordHistoryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class SearchServiceTest {
    @Autowired
    private SearchService searchService;
    @Autowired
    private KeywordHistoryRepository keywordHistoryRepository;

    @BeforeEach
    void setUp() {
        keywordHistoryRepository.deleteAll();
    }

    @Test
    void 블로그_검색() {
        //given
        SearchBlogReq searchBlogReq = new SearchBlogReq();
        searchBlogReq.setKeyword("검색어");
        //when
        PageRes pageRes = searchService.searchBlog(searchBlogReq);

        //then
        assertNotNull(pageRes);
    }

}