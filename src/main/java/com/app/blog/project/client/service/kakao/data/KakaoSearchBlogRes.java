package com.app.blog.project.client.service.kakao.data;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class KakaoSearchBlogRes {
    private KaKaoMeta meta;
    private List<KakaoSearchBlogDocument> documents;
}
