package com.daonguyen.blogcrudapp.controller;

import com.daonguyen.blogcrudapp.exception.ResourceNotFoundException;
import com.daonguyen.blogcrudapp.model.Topic;
import com.daonguyen.blogcrudapp.repository.CategoryRepository;
import com.daonguyen.blogcrudapp.repository.TopicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class TopicController {

    @Autowired
    private TopicRepository topicRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @GetMapping("/categories/{categoryId}/topics")
    public List<Topic> getTopicsByCategoryId(@PathVariable String categoryId) {
        return topicRepository.findByCategoryId(categoryId);
    }

    @PostMapping("/categories/{categoryId}/topics")
    public Topic createTopic(@PathVariable String categoryId,
                             @Valid @RequestBody Topic topic) {
        return categoryRepository.findById(categoryId)
                .map(category -> {
                    topic.setCategory(category);
                    return topicRepository.save(topic);
                }).orElseThrow(() -> new ResourceNotFoundException("Category not found with id " + categoryId));
    }

    @PutMapping("categories/{categoryId}/topics/{topicId}")
    public Topic updateTopic(@PathVariable String categoryId,
                             @PathVariable String topicId,
                             @Valid @RequestBody Topic topicRequest) {
        return topicRepository.findById(topicId)
                .map(topic -> {
                    topic.setTopicName(topicRequest.getTopicName());
                    topic.setCreatedAt(topicRequest.getCreatedAt());
                    topic.setUpdatedAt(topicRequest.getUpdatedAt());
                    topic.setCategory(topicRequest.getCategory());
                    return topicRepository.save(topic);
                }).orElseThrow(() -> new ResourceNotFoundException("Topic not found with id " + topicId));
    }

    @DeleteMapping("/categories/{categoryId}/topics/{topicId}")
    public ResponseEntity<?> deleteTopic(@PathVariable String categoryId,
                                         @PathVariable String topicId) {
        if (!categoryRepository.existsById(categoryId)) {
            throw new ResourceNotFoundException("Category not found with id " + categoryId);
        }

        return topicRepository.findById(topicId)
                .map(topic -> {
                    topicRepository.delete(topic);
                    return ResponseEntity.ok().build();
                }).orElseThrow(() -> new ResourceNotFoundException("Topic not found with id " + topicId));
    }
}
