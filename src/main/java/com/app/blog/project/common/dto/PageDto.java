package com.app.blog.project.common.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PageDto {
    private Integer page;
    private Integer totalPage;
    private Integer size;
    private Boolean hasNext;
}
