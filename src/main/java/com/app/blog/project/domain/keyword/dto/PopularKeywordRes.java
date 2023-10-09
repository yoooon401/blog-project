package com.app.blog.project.domain.keyword.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PopularKeywordRes {
    Integer rank;
    String keyword;
    Integer count;

    public static PopularKeywordRes of(Integer rank, String keyword, Integer count) {
        return new PopularKeywordRes(rank, keyword, count);
    }
}
