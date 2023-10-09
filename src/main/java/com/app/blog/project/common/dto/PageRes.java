package com.app.blog.project.common.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.data.domain.Page;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter
@AllArgsConstructor
public class PageRes<T> {
    private List<T> data;
    private Integer size;
    private Integer page;
    private Integer totalPage;
    private Integer totalCount;
    private Boolean hasNext;

    public static <T> PageRes<T> of (List<T> data, Integer size, Integer page, Integer totalPage, Integer totalCount, Boolean hasNext) {
        return new PageRes<T>(data, size, page, totalPage, totalCount, hasNext);
    }
}
