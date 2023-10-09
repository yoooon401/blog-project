package com.app.blog.project.domain.keyword.controller;

import com.app.blog.project.domain.keyword.service.KeywordService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "keyword")
public class KeywordController {

    private final KeywordService service;

    @GetMapping(value = "popular")
    public ResponseEntity<?> getPopular() {
        return new ResponseEntity<>(service.getPopular(), HttpStatus.OK);
    }

    @GetMapping(value = "recent")
    public ResponseEntity<?> getRecent() {
        return new ResponseEntity<>(service.getRecent(), HttpStatus.OK);
    }
}
