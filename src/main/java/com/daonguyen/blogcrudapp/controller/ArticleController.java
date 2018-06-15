package com.daonguyen.blogcrudapp.controller;

import com.daonguyen.blogcrudapp.exception.ResourceNotFoundException;
import com.daonguyen.blogcrudapp.model.Article;
import com.daonguyen.blogcrudapp.repository.ArticleRepository;
import com.daonguyen.blogcrudapp.repository.TopicRepository;
import org.hibernate.ResourceClosedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class ArticleController {

    @Autowired
    ArticleRepository articleRepository;

    @Autowired
    TopicRepository topicRepository;

    @GetMapping("/topics/{topicId}/articles")
    public List<Article> getArticlesByTopicId(@PathVariable String topicId) {
        return articleRepository.findByTopicId(topicId);
    }

    @PostMapping("/topics/{topicId}/articles")
    public Article createArticle(@PathVariable String topicId,
                                 @Valid @RequestBody Article article) {
        return topicRepository.findById(topicId)
                .map(topic -> {
                    article.setTopic(topic);
                    return articleRepository.save(article);
                }).orElseThrow(() -> new ResourceNotFoundException("Topic not found with id " + topicId));
    }

    @PutMapping("/topics/{topicId}/articles/{articleId}")
    public Article updateArticle(@PathVariable String topicId,
                                 @PathVariable String articleId,
                                 @Valid @RequestBody Article articleRequest) {
        if (!topicRepository.existsById(topicId)) {
            throw new ResourceNotFoundException("Topic not found with id " + topicId);
        }

        return articleRepository.findById(articleId)
                .map(article -> {
                    article.setArticleName(articleRequest.getArticleName());
                    article.setTitleImage(articleRequest.getTitleImage());
                    article.setContentImage(articleRequest.getContentImage());
                    article.setDescription(articleRequest.getDescription());
                    article.setContents(articleRequest.getContents());
                    article.setViews(articleRequest.getViews());
                    article.setCreatedAt(articleRequest.getCreatedAt());
                    article.setUpdatedAt(article.getUpdatedAt());
                    article.setAvailable(articleRequest.getAvailable());
                    article.setTopic(articleRequest.getTopic());
                    return articleRepository.save(article);
                }).orElseThrow(() -> new ResourceNotFoundException("Article not found with id " + articleId));
    }

    @DeleteMapping("/topics/{topicId}/articles/{articleId}")
    public ResponseEntity<?> deleteArticle(@PathVariable String topicId,
                                           @PathVariable String articleId) {
        if (!topicRepository.existsById(articleId)) {
            throw new ResourceClosedException("Topic not found with id " + topicId);
        }

        return articleRepository.findById(articleId)
                .map(article -> {
                    articleRepository.delete(article);
                    return ResponseEntity.ok().build();
                }).orElseThrow(() -> new ResourceClosedException("Article not found with id " + articleId));
    }

}
