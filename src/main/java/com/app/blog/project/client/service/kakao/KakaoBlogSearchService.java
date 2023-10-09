package com.app.blog.project.client.service.kakao;

import com.app.blog.project.client.common.data.BlogSearchReq;
import com.app.blog.project.client.common.data.BlogSearchRes;
import com.app.blog.project.client.service.BlogSearchService;
import com.app.blog.project.client.service.kakao.data.KakaoSearchBlogRes;
import com.app.blog.project.client.service.naver.NaverBlogSearchService;
import com.app.blog.project.common.type.SortType;
import com.app.blog.project.common.utils.JsonUtils;
import feign.FeignException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class KakaoBlogSearchService extends BlogSearchService {

    private final KakaoClient kakaoClient;
    private final NaverBlogSearchService naverBlogSearchService;

    @Override
    public BlogSearchRes search(BlogSearchReq req) {
        KakaoSearchBlogRes res = null;
        try {
            res = JsonUtils.readValue(kakaoClient.searchBlog(req.getQuery(), getSort(req.getSort()), req.getPage(), req.getSize()), KakaoSearchBlogRes.class);
        } catch (FeignException fe) {
            return naverBlogSearchService.search(req);
        } catch (IOException e) {
            throw new RuntimeException();
        }

        return BlogSearchRes.of(
                BlogSearchRes.Page.of(req.getSize()
                        , req.getPage()
                        , res.getMeta().getTotalPage(req.getSize())
                        ,  res.getMeta().getPageableCount()
                        , !res.getMeta().getIsEnd())
                , res.getDocuments().stream()
                        .map(document -> BlogSearchRes.Data.of(document.getTitle()
                                , document.getContents()
                                , document.getUrl()
                                , document.getBlogname()
                                , document.getThumbnail()
                                , document.getDatetime()))
                        .collect(Collectors.toList())
        );
    }

    private String getSort(SortType sortType) {
        return switch (sortType) {
            case RECENCY -> "recency";
            case ACCURACY -> "accuracy";
        };
    }
}
