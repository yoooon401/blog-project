package com.app.blog.project.repository;

import com.app.blog.project.entity.KeywordHistory;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface KeywordHistoryRepository extends JpaRepository<KeywordHistory, Long> {
    KeywordHistory findByKeyword(String keyword);
    List<KeywordHistory> findAllByOrderByCountDesc(Pageable pageable);
    List<KeywordHistory> findAllByOrderByLastSearchedAtDesc(Pageable pageable);
}
