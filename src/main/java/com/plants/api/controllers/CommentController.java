package com.plants.api.controllers;

import com.plants.api.dto.request.CommentRequest;
import com.plants.api.entities.Comment;
import com.plants.api.entities.enums.CommentType;
import com.plants.api.services.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/comments")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @PostMapping("/{userId}")
    public ResponseEntity<Comment> addComment(
            @PathVariable Long userId,
            @RequestBody CommentRequest request
    ) {
        return ResponseEntity.ok(commentService.addComment(userId, request));
    }

    @GetMapping("/{type}/{targetId}")
    public ResponseEntity<List<Comment>> getComments(
            @PathVariable String type,
            @PathVariable Long targetId
    ) {
        return ResponseEntity.ok(
                commentService.getComments(targetId, CommentType.valueOf(type.toUpperCase()))
        );
    }

    @PatchMapping("/hide/{commentId}")
    public ResponseEntity<Void> hideComment(@PathVariable Long commentId) {
        commentService.hideComment(commentId);
        return ResponseEntity.noContent().build();
    }
}

