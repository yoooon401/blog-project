package com.app.blog.project.client.service.kakao.data;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
public class KaKaoMeta {

    private Integer totalCount;
    private Integer pageableCount;
    private Boolean isEnd;

    public int getTotalPage(Integer size) {
        return pageableCount / size + 1;
    }
}
