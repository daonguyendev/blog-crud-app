package com.daonguyen.blogcrudapp.repository;

import com.daonguyen.blogcrudapp.model.Topic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TopicRepository extends JpaRepository<Topic, String> {
    List<Topic> findByCategoryId(String categoryId);
}
