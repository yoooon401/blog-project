package com.app.blog.project.client.service.naver;

import com.app.blog.project.client.common.data.BlogSearchReq;
import com.app.blog.project.client.common.data.BlogSearchRes;
import com.app.blog.project.client.service.BlogSearchService;
import com.app.blog.project.common.type.SortType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class NaverBlogSearchService extends BlogSearchService {
    private final NaverClient naverClient;

    @Override
    public BlogSearchRes search(BlogSearchReq req) {
        NaverSearchBlogRes res = naverClient.searchBlog(req.getQuery(), getSort(req.getSort()), req.getPage(), req.getSize());
        return BlogSearchRes.of(
                BlogSearchRes.Page.of(req.getSize()
                        , req.getPage()
                        , res.getTotalPage()
                        , res.getTotal()
                        , (req.getPage() == res.getTotalPage()))
                , res.getItems().stream()
                        .map(item -> BlogSearchRes.Data.of(item.getTitle()
                                , item.getDescription()
                                , item.getLink()
                                , item.getBloggername()
                                , null
                                , item.convertOffsetDateTime()))
                        .collect(Collectors.toList()));
    }

    private String getSort(SortType sortType) {
        return switch (sortType) {
            case RECENCY -> "date";
            case ACCURACY -> "sim";
        };
    }
}