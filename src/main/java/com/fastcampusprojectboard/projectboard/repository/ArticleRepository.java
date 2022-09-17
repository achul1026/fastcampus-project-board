package com.fastcampusprojectboard.projectboard.repository;

import com.fastcampusprojectboard.projectboard.domain.Article;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticleRepository extends JpaRepository<Article, Long> {

}