package com.plants.api.services;

import com.plants.api.dto.request.CommentRequest;
import com.plants.api.entities.Comment;
import com.plants.api.entities.User;
import com.plants.api.entities.enums.CommentType;
import com.plants.api.repositories.CommentRepository;
import com.plants.api.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final UserRepository userRepository;

    public Comment addComment(Long userId, CommentRequest request) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        CommentType type = CommentType.valueOf(request.getType().toUpperCase());

        Comment comment = Comment.builder()
                .user(user)
                .targetId(request.getTargetId())
                .type(type)
                .content(request.getContent())
                .visible(true)
                .build();

        return commentRepository.save(comment);
    }

    public List<Comment> getComments(Long targetId, CommentType type) {
        return commentRepository.findByTargetIdAndTypeAndVisibleTrue(targetId, type);
    }

    public void hideComment(Long commentId) {
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new RuntimeException("Comment not found"));
        comment.setVisible(false);
        commentRepository.save(comment);
    }
}

