package com.fastcampusprojectboard.projectboard.repository;

import com.fastcampusprojectboard.projectboard.config.JpaConfig;
import com.fastcampusprojectboard.projectboard.domain.Article;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

@ActiveProfiles("testdb")
@DisplayName("Jpa 연결 테스트")
@Import(JpaConfig.class)
@DataJpaTest
class JpaRepositoryTest {

    private ArticleRepository articleRepository;
    private ArticleCommentRepository articleCommentRepository;

    public JpaRepositoryTest(
            @Autowired ArticleRepository articleRepository,
            @Autowired ArticleCommentRepository articleCommentRepository)
    {
        this.articleRepository = articleRepository;
        this.articleCommentRepository = articleCommentRepository;
    }

    @DisplayName("select 테스트")
    @Test
    void givenTastData_whenSelecting_thenWorkisFine(){
        //given
        //when
        List<Article> articles = articleRepository.findAll();
        //then
        assertThat(articles)
                .isNotNull()
                .hasSize(1000);
    }

    @DisplayName("insert 테스트")
    @Test
    void givenTastData_whenInserting_thenWorkisFine(){
        //given
        long previousCount = articleRepository.count();
        //when
        Article savedArticle = articleRepository.save(Article.of("new article", "new content", "#spring"));
        //then
        assertThat(articleRepository.count()).isEqualTo(previousCount+1);
    }

    @DisplayName("update 테스트")
    @Test
    void givenTastData_whenUpdating_thenWorkisFine(){
        //given
        Article article = articleRepository.findById(1L).orElseThrow();
        String updatetedHashtag = "#springboot";
        article.setHashtag(updatetedHashtag);
        //when
        Article savedArticle = articleRepository.saveAndFlush(article);

        //then
        assertThat(savedArticle).hasFieldOrPropertyWithValue("hashtag", updatetedHashtag);
    }

    @DisplayName("delete 테스트")
    @Test
    void givenTastData_whenDeleting_thenWorkisFine(){
        //given
        Article article = articleRepository.findById(1L).orElseThrow();
        long previousArticleCount = articleRepository.count();
        long previousArticleCommentCount = articleCommentRepository.count();
        int deletedCommentsSize = article.getArticleComments().size();
        //when
        articleRepository.delete(article);

        //then
        assertThat(articleRepository.count()).isEqualTo(previousArticleCount - 1);
        assertThat(articleCommentRepository.count()).isEqualTo(previousArticleCommentCount - deletedCommentsSize);
    }
}