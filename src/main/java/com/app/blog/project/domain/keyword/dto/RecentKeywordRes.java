package com.app.blog.project.domain.keyword.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class RecentKeywordRes {
    Integer rank;
    String keyword;
    LocalDateTime dateTime;

    public static RecentKeywordRes of(Integer rank, String keyword, LocalDateTime dateTime) {
        return new RecentKeywordRes(rank, keyword, dateTime);
    }
}
