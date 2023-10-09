package com.app.blog.project.domain.keyword.service;

import com.app.blog.project.domain.keyword.dto.PopularKeywordRes;
import com.app.blog.project.domain.keyword.dto.RecentKeywordRes;
import com.app.blog.project.entity.KeywordHistory;
import com.app.blog.project.repository.KeywordHistoryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class KeywordServiceTest {
    @Autowired
    private KeywordService keywordService;
    @Autowired
    private KeywordHistoryRepository keywordHistoryRepository;

    @BeforeEach
    void setUp() {
        keywordHistoryRepository.deleteAll();
    }

    //OK case 테스트
    @Test
    void 인기_검색어_조회() {
        //given
        List<KeywordHistory> keywordHistoryList = Arrays.asList(
                new KeywordHistory("검색어1", 1, LocalDateTime.now())
                , new KeywordHistory("검색어2", 1, LocalDateTime.now())
                , new KeywordHistory("검색어3", 1, LocalDateTime.now())
                , new KeywordHistory("검색어4", 1, LocalDateTime.now())
                , new KeywordHistory("검색어5", 1, LocalDateTime.now())
        );
        keywordHistoryRepository.saveAll(keywordHistoryList);

        //when
        List<PopularKeywordRes> popularKeywordResList = keywordService.getPopular();

        //then
        assertEquals(keywordHistoryList.size(), popularKeywordResList.size());
    }

    //검색이력이 없을경우 테스트
    @Test
    void 인기_검색어_데이터없음_조회() {
        //given

        //when
        List<PopularKeywordRes> popularKeywordResList = keywordService.getPopular();

        //then
        assertEquals(0, popularKeywordResList.size());
    }

    //인기 검색어 10건만을 조호하는지 테스트
    @Test
    void 인기_검색어_조회_10건_체크() {
        //given
        List<KeywordHistory> keywordHistoryList = Arrays.asList(
                new KeywordHistory("검색어1", 1, LocalDateTime.now())
                , new KeywordHistory("검색어2", 1, LocalDateTime.now())
                , new KeywordHistory("검색어3", 1, LocalDateTime.now())
                , new KeywordHistory("검색어4", 1, LocalDateTime.now())
                , new KeywordHistory("검색어5", 1, LocalDateTime.now())
                , new KeywordHistory("검색어6", 1, LocalDateTime.now())
                , new KeywordHistory("검색어7", 1, LocalDateTime.now())
                , new KeywordHistory("검색어8", 1, LocalDateTime.now())
                , new KeywordHistory("검색어9", 1, LocalDateTime.now())
                , new KeywordHistory("검색어10", 1, LocalDateTime.now())
                , new KeywordHistory("검색어11", 1, LocalDateTime.now())
                , new KeywordHistory("검색어12", 1, LocalDateTime.now())
                , new KeywordHistory("검색어13", 1, LocalDateTime.now())
        );
        keywordHistoryRepository.saveAll(keywordHistoryList);

        //when
        List<PopularKeywordRes> popularKeywordResList = keywordService.getPopular();

        //then
        assertEquals(10, popularKeywordResList.size());
    }


    //검색횟수가 많은 순대로 조회되는지 확인
    @Test
    void 인기_검색어_조회_순위_체크() {
        //given
        List<KeywordHistory> keywordHistoryList = Arrays.asList(
                new KeywordHistory("검색어1", 100, LocalDateTime.now())
                , new KeywordHistory("검색어2", 90, LocalDateTime.now())
                , new KeywordHistory("검색어3", 80, LocalDateTime.now())
                , new KeywordHistory("검색어4", 200, LocalDateTime.now())
                , new KeywordHistory("검색어5", 300, LocalDateTime.now())
                , new KeywordHistory("검색어6", 400, LocalDateTime.now())
                , new KeywordHistory("검색어7", 500, LocalDateTime.now())
                , new KeywordHistory("검색어8", 900, LocalDateTime.now())
                , new KeywordHistory("검색어9", 1, LocalDateTime.now())
                , new KeywordHistory("검색어10", 2, LocalDateTime.now())
        );
        keywordHistoryRepository.saveAll(keywordHistoryList);

        keywordHistoryList.sort(Comparator.comparingInt(KeywordHistory::getCount).reversed());

        //when
        List<PopularKeywordRes> popularKeywordResList = keywordService.getPopular();

        //then
        for (int i = 0; i < popularKeywordResList.size(); i++) {
            assertEquals(keywordHistoryList.get(i).getKeyword(), popularKeywordResList.get(i).getKeyword());
            assertEquals(keywordHistoryList.get(i).getCount(), popularKeywordResList.get(i).getCount());
        }
    }

    //검색수가 동일할 경우의 순위 테스트
    @Test
    void 인기_검색어_조회_동점_체크() {
        //given
        List<KeywordHistory> keywordHistoryList = Arrays.asList(
                new KeywordHistory("검색어1", 100, LocalDateTime.now())
                , new KeywordHistory("검색어2", 100, LocalDateTime.now())
                , new KeywordHistory("검색어3", 100, LocalDateTime.now())
                , new KeywordHistory("검색어4", 100, LocalDateTime.now())
                , new KeywordHistory("검색어5", 100, LocalDateTime.now())
                , new KeywordHistory("검색어6", 100, LocalDateTime.now())
                , new KeywordHistory("검색어7", 100, LocalDateTime.now())
                , new KeywordHistory("검색어8", 100, LocalDateTime.now())
                , new KeywordHistory("검색어9", 100, LocalDateTime.now())
                , new KeywordHistory("검색어10", 100, LocalDateTime.now())
        );
        keywordHistoryRepository.saveAll(keywordHistoryList);

        //when
        List<PopularKeywordRes> popularKeywordResList = keywordService.getPopular();

        //then
        for (int i = 0; i < popularKeywordResList.size(); i++) {
            assertEquals(1, popularKeywordResList.get(i).getRank());
        }
    }

    //검색수가 동일할 경우의 순위 테스트2
    @Test
    void 인기_검색어_조회_동점_체크2() {
        //given
        List<KeywordHistory> keywordHistoryList = Arrays.asList(
                new KeywordHistory("검색어1", 100, LocalDateTime.now())
                , new KeywordHistory("검색어2", 100, LocalDateTime.now())
                , new KeywordHistory("검색어3", 50, LocalDateTime.now())
                , new KeywordHistory("검색어4", 50, LocalDateTime.now())
                , new KeywordHistory("검색어5", 1, LocalDateTime.now())
        );
        keywordHistoryRepository.saveAll(keywordHistoryList);

        keywordHistoryList.sort(Comparator.comparingInt(KeywordHistory::getCount).reversed());

        //when
        List<PopularKeywordRes> popularKeywordResList = keywordService.getPopular();

        //then
        for (int i = 1; i < popularKeywordResList.size(); i++) {
            if(Objects.equals(popularKeywordResList.get(i - 1).getCount(), popularKeywordResList.get(i).getCount())) {
                assertEquals(popularKeywordResList.get(i-1).getRank(), popularKeywordResList.get(i).getRank());
            }
        }
    }



    //OK case 테스트
    @Test
    void 최신_검색어_조회() {
        //given
        List<KeywordHistory> keywordHistoryList = Arrays.asList(
                new KeywordHistory("검색어1", 1, LocalDateTime.now())
                , new KeywordHistory("검색어2", 1, LocalDateTime.now())
                , new KeywordHistory("검색어3", 1, LocalDateTime.now())
                , new KeywordHistory("검색어4", 1, LocalDateTime.now())
                , new KeywordHistory("검색어5", 1, LocalDateTime.now())
        );
        keywordHistoryRepository.saveAll(keywordHistoryList);

        //when
        List<RecentKeywordRes> recentKeywordResList = keywordService.getRecent();

        //then
        assertEquals(keywordHistoryList.size(), recentKeywordResList.size());
    }

    //검색이력이 없을경우 테스트
    @Test
    void 최신_검색어_데이터없음_조회() {
        //given

        //when
        List<RecentKeywordRes> recentKeywordResList = keywordService.getRecent();

        //then
        assertEquals(0, recentKeywordResList.size());
    }

    //최신 검색어 10건만을 조호하는지 테스트
    @Test
    void 최신_검색어_조회_10건_체크() {
        //given
        List<KeywordHistory> keywordHistoryList = Arrays.asList(
                new KeywordHistory("검색어1", 1, LocalDateTime.now())
                , new KeywordHistory("검색어2", 1, LocalDateTime.now())
                , new KeywordHistory("검색어3", 1, LocalDateTime.now())
                , new KeywordHistory("검색어4", 1, LocalDateTime.now())
                , new KeywordHistory("검색어5", 1, LocalDateTime.now())
                , new KeywordHistory("검색어6", 1, LocalDateTime.now())
                , new KeywordHistory("검색어7", 1, LocalDateTime.now())
                , new KeywordHistory("검색어8", 1, LocalDateTime.now())
                , new KeywordHistory("검색어9", 1, LocalDateTime.now())
                , new KeywordHistory("검색어10", 1, LocalDateTime.now())
                , new KeywordHistory("검색어11", 1, LocalDateTime.now())
                , new KeywordHistory("검색어12", 1, LocalDateTime.now())
                , new KeywordHistory("검색어13", 1, LocalDateTime.now())
        );
        keywordHistoryRepository.saveAll(keywordHistoryList);

        //when
        List<RecentKeywordRes> recentKeywordResList = keywordService.getRecent();

        //then
        assertEquals(10, recentKeywordResList.size());
    }


    //최근날짜에 검색된 순대로 조회되는지 확인
    @Test
    void 최근_검색어_조회_순위_체크() {
        //given
        List<KeywordHistory> keywordHistoryList = Arrays.asList(
                new KeywordHistory("검색어1", 100, LocalDateTime.now().minusDays(1))
                , new KeywordHistory("검색어2", 90, LocalDateTime.now().minusDays(10))
                , new KeywordHistory("검색어3", 80, LocalDateTime.now().minusDays(13))
                , new KeywordHistory("검색어4", 200, LocalDateTime.now().minusDays(100))
                , new KeywordHistory("검색어5", 300, LocalDateTime.now().plusDays(1))
                , new KeywordHistory("검색어6", 400, LocalDateTime.now().plusDays(10))
                , new KeywordHistory("검색어7", 500, LocalDateTime.now().plusDays(100))
                , new KeywordHistory("검색어8", 900, LocalDateTime.now().plusDays(20))
                , new KeywordHistory("검색어9", 1, LocalDateTime.now().minusDays(20))
                , new KeywordHistory("검색어10", 2, LocalDateTime.now().minusDays(200))
        );
        keywordHistoryRepository.saveAll(keywordHistoryList);

        keywordHistoryList.sort(Comparator.comparing(KeywordHistory::getLastSearchedAt).reversed());

        //when
        List<RecentKeywordRes> recentKeywordResList = keywordService.getRecent();

        //then
        for (int i = 0; i < recentKeywordResList.size(); i++) {
            assertEquals(keywordHistoryList.get(i).getKeyword(), recentKeywordResList.get(i).getKeyword());
            assertEquals(keywordHistoryList.get(i).getLastSearchedAt(), recentKeywordResList.get(i).getDateTime());
        }
    }

    //검색날짜가 동일할 경우의 순위 테스트
    @Test
    void 최신_검색어_조회_동점_체크() {
        //given
        LocalDateTime now = LocalDateTime.now();
        List<KeywordHistory> keywordHistoryList = Arrays.asList(
                new KeywordHistory("검색어1", 100, now)
                , new KeywordHistory("검색어2", 100, now)
                , new KeywordHistory("검색어3", 100, now)
                , new KeywordHistory("검색어4", 100, now)
                , new KeywordHistory("검색어5", 100, now)
                , new KeywordHistory("검색어6", 100, now)
                , new KeywordHistory("검색어7", 100, now)
                , new KeywordHistory("검색어8", 100, now)
                , new KeywordHistory("검색어9", 100, now)
                , new KeywordHistory("검색어10", 100, now)
        );
        keywordHistoryRepository.saveAll(keywordHistoryList);

        //when
        List<RecentKeywordRes> recentKeywordResList = keywordService.getRecent();

        //then
        for (int i = 0; i < recentKeywordResList.size(); i++) {
            assertEquals(1, recentKeywordResList.get(i).getRank());
        }
    }

    //검색날짜가 동일할 경우의 순위 테스트2
    @Test
    void 최신_검색어_조회_동점_체크2() {
        //given
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime yesterday = LocalDateTime.now().minusDays(1);
        List<KeywordHistory> keywordHistoryList = Arrays.asList(
                new KeywordHistory("검색어1", 100, LocalDateTime.now())
                , new KeywordHistory("검색어2", 100, now)
                , new KeywordHistory("검색어3", 50, now)
                , new KeywordHistory("검색어4", 50, yesterday)
                , new KeywordHistory("검색어5", 1, yesterday)
        );
        keywordHistoryRepository.saveAll(keywordHistoryList);

        keywordHistoryList.sort(Comparator.comparing(KeywordHistory::getLastSearchedAt).reversed());

        //when
        List<RecentKeywordRes> recentKeywordResList = keywordService.getRecent();

        //then
        for (int i = 1; i < recentKeywordResList.size(); i++) {
            if(Objects.equals(recentKeywordResList.get(i - 1).getDateTime(), recentKeywordResList.get(i).getDateTime())) {
                assertEquals(recentKeywordResList.get(i-1).getRank(), recentKeywordResList.get(i).getRank());
            }
        }
    }


    //검색어를 저장하였는지 테스트
    @Test
    void 검색어_저장() {
        //given
        String keyword = "검색어";

        //when
        keywordService.saveKeyword(keyword);

        //then
        KeywordHistory keywordHistory = keywordHistoryRepository.findByKeyword(keyword);
        assertNotNull(keywordHistory);
        assertEquals(keyword, keywordHistory.getKeyword());
        assertEquals(1, keywordHistory.getCount());
    }

    //검색이력이 존재하는 경우 검색횟수를 갱신하였는지 테스트
    @Test
    void 검색어_갱신() {
        //given
        String keyword = "검색어";
        Integer originCount = 100;
        KeywordHistory originKeywordHistory = new KeywordHistory(keyword, originCount, LocalDateTime.now());
        keywordHistoryRepository.save(originKeywordHistory);

        //when
        keywordService.saveKeyword(keyword);

        //then
        KeywordHistory keywordHistory = keywordHistoryRepository.findByKeyword(keyword);
        assertNotNull(keywordHistory);
        assertEquals(keyword, keywordHistory.getKeyword());
        assertEquals(originCount+1, keywordHistory.getCount());
    }

}