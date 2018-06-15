package com.daonguyen.blogcrudapp.repository;

import com.daonguyen.blogcrudapp.model.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArticleRepository extends JpaRepository<Article, String> {
    List<Article> findByTopicId(String topicId);
}
