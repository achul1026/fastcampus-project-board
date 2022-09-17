package com.fastcampusprojectboard.projectboard.repository;

import com.fastcampusprojectboard.projectboard.domain.ArticleComment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticleCommentRepository extends JpaRepository<ArticleComment, Long> {
}