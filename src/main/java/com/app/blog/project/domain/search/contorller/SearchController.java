package com.app.blog.project.domain.search.contorller;

import com.app.blog.project.domain.search.dto.SearchBlogReq;
import com.app.blog.project.domain.search.service.SearchService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "search")
public class SearchController {

    private final SearchService service;

    @GetMapping(value = "blog")
    public ResponseEntity<?> searchBlog(@Valid SearchBlogReq req) {
        return new ResponseEntity<>(service.searchBlog(req), HttpStatus.OK);
    }
}
