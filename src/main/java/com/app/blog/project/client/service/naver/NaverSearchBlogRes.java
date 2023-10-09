package com.app.blog.project.client.service.naver;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class NaverSearchBlogRes {
    private String lastBuildDate;
    private int total;
    private int start;
    private int display;
    private List<Item> items;

    @Getter
    public static class Item {
        private String title;
        private String link;
        private String description;
        private String bloggername;
        private String bloggerlink;
        private String postdate;

        public OffsetDateTime convertOffsetDateTime() {
            return OffsetDateTime.of(
                    LocalDate.parse(postdate, DateTimeFormatter.ofPattern("yyyyMMdd"))
                    , LocalTime.of(0, 0)
                    , ZoneOffset.UTC);
        }
    }

    public int getTotalPage() {
        return total / display + 1;
    }
}
