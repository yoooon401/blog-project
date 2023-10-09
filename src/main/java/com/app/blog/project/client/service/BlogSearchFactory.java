package com.app.blog.project.client.service;

import com.app.blog.project.client.service.kakao.KakaoBlogSearchService;
import com.app.blog.project.client.service.naver.NaverBlogSearchService;
import com.app.blog.project.common.type.SearchSourceType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class BlogSearchFactory {
    private final KakaoBlogSearchService kakaoBlogSearchService;
    private final NaverBlogSearchService naverBlogSearchService;
    
    public BlogSearchService get(SearchSourceType sourceType) {
        final BlogSearchService blogSearchService;
        switch(sourceType) {
            case KAKAO:
                blogSearchService = kakaoBlogSearchService;
                break;
            case NAVER:
                blogSearchService = naverBlogSearchService;
                break;
            default:
                throw new IllegalArgumentException();
        };

        return blogSearchService;
    }
}
