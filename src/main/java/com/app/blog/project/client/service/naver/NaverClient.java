package com.app.blog.project.client.service.naver;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "naverClient", url = "${service.naver.url}", configuration = NaverFeignConfig.class)
public interface NaverClient {

    @GetMapping(value = "search/blog.json")
    NaverSearchBlogRes searchBlog(
            @RequestParam("query") String query,
            @RequestParam("sort") String sort,
            @RequestParam("start") Integer page,
            @RequestParam("display") Integer size
    );
}
