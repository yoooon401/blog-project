package com.app.blog.project.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class KeywordHistory {
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    @Column(columnDefinition = "int comment 'id'")
    private Long id;

    @Column(columnDefinition = "varchar comment '검색어'")
    private String keyword;

    @Column(columnDefinition = "int comment '검색횟수'")
    private Integer count;

    @Column(nullable = false,columnDefinition = "timestamp not null default current_timestamp comment '최근검색일'")
    @LastModifiedDate
    private LocalDateTime lastSearchedAt;

    public KeywordHistory(String keyword, Integer count, LocalDateTime searchedAt) {
        this.keyword = keyword;
        this.count = count;
        this.lastSearchedAt = searchedAt;
    }

    public static KeywordHistory of(String keyword) {
        return new KeywordHistory(keyword, 1, LocalDateTime.now());
    }

    public void countUp() {
        this.count +=1;
    }
}
