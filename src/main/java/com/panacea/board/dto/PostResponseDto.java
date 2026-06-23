package com.panacea.board.dto;

import com.panacea.board.entity.Post;
import lombok.Getter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Getter
public class PostResponseDto {

    private final Long id;
    private final String title;
    private final String content;
    private final String author;
    private final String createdAt;
    private final String updatedAt;

    public PostResponseDto(Post post) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        this.id = post.getId();
        this.title = post.getTitle();
        this.content = post.getContent();
        this.author = post.getAuthor();
        this.createdAt = post.getCreatedAt() != null
                ? post.getCreatedAt().format(formatter) : "";
        this.updatedAt = post.getUpdatedAt() != null
                ? post.getUpdatedAt().format(formatter) : "";
    }
}
