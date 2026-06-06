package com.plants.api.repositories;

import com.plants.api.entities.Comment;
import com.plants.api.entities.enums.CommentType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    List<Comment> findByTargetIdAndTypeAndVisibleTrue(Long targetId, CommentType type);

    List<Comment> findByUserId(Long userId);
}
