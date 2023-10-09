package com.app.blog.project.domain.keyword.service;

import com.app.blog.project.domain.keyword.dto.PopularKeywordRes;
import com.app.blog.project.domain.keyword.dto.RecentKeywordRes;
import com.app.blog.project.entity.KeywordHistory;
import com.app.blog.project.repository.KeywordHistoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class KeywordService {
    private final KeywordHistoryRepository keywordHistoryRepository;

    @Transactional(readOnly = true)
    public List<PopularKeywordRes> getPopular() {
        List<KeywordHistory> popularKeywords = keywordHistoryRepository.findAllByOrderByCountDesc(PageRequest.of(0, 10));
        List<PopularKeywordRes> res = new ArrayList<>();
        int rank = 1;
        int beforeCount = 0;
        for (KeywordHistory popularKeyword : popularKeywords) {
            if(beforeCount == popularKeyword.getCount()) {
                rank -= 1;
            }
            res.add(PopularKeywordRes.of(rank++, popularKeyword.getKeyword(), popularKeyword.getCount()));
            beforeCount = popularKeyword.getCount();
        }
        return res;
    }

    @Transactional(readOnly = true)
    public List<RecentKeywordRes> getRecent() {
        List<KeywordHistory> popularKeywords = keywordHistoryRepository.findAllByOrderByLastSearchedAtDesc(PageRequest.of(0, 10));
        List<RecentKeywordRes> res = new ArrayList<>();
        int rank = 1;
        LocalDateTime beforeDateTime = null;
        for (KeywordHistory recentKeyword : popularKeywords) {
            if(!ObjectUtils.isEmpty(beforeDateTime) && beforeDateTime.isEqual(recentKeyword.getLastSearchedAt())) {
                rank -= 1;
            }
            res.add(RecentKeywordRes.of(rank++, recentKeyword.getKeyword(), recentKeyword.getLastSearchedAt()));
            beforeDateTime = recentKeyword.getLastSearchedAt();
        }
        return res;
    }

    @Transactional
    public void saveKeyword(String keyword) {
        KeywordHistory keywordHistory = keywordHistoryRepository.findByKeyword(keyword);
        if(ObjectUtils.isEmpty(keywordHistory)) {
            keywordHistory = KeywordHistory.of(keyword);
        } else {
            keywordHistory.countUp();
        }
        keywordHistoryRepository.save(keywordHistory);
    }
}
