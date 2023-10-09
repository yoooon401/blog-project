package com.app.blog.project.domain.search.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.OffsetDateTime;

@Getter
@Setter
@AllArgsConstructor
public class SearchBlogRes {
    private String title;
    private String contents;
    private String url;
    private String blogName;
    private String thumbnail;
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX")
    private OffsetDateTime dateTime;

    public static SearchBlogRes of(String title, String contents, String url, String blogName, String thumbnail, OffsetDateTime dateTime) {
        return new SearchBlogRes(title, contents, url, blogName, thumbnail, dateTime);
    }
}
