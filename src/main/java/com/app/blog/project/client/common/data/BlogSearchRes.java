package com.app.blog.project.client.common.data;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.OffsetDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class BlogSearchRes {
    private Page page;
    private List<Data> data;

    public static BlogSearchRes of (Page page, List<Data> data) {
        return new BlogSearchRes(page, data);
    }

    @Getter
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    public static class Page {
        private Integer size;
        private Integer page;
        private Integer totalPage;
        private Integer totalCount;
        private Boolean hasNext;

        public static Page of (Integer size, Integer page, Integer totalPage, Integer totalCount, Boolean hasNext) {
            return new Page(size, page, totalPage, totalCount, hasNext);
        }
    }

    @Getter
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    public static class Data {
        private String title;
        private String contents;
        private String url;
        private String blogName;
        private String thumbnail;
        @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX")
        private OffsetDateTime dateTime;

        public static Data of(String title, String contents, String url, String blogName, String thumbnail, OffsetDateTime dateTime) {
            return new Data(title, contents, url, blogName, thumbnail, dateTime);
        }
    }
}
