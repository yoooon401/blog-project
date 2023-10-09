package com.app.blog.project.client.service;

import com.app.blog.project.client.common.data.BlogSearchReq;
import com.app.blog.project.client.common.data.BlogSearchRes;

public abstract class BlogSearchService {
    public abstract BlogSearchRes search(BlogSearchReq req);
}
