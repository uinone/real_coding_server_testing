package com.cnu.real_coding_server.service.week1.practice.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import com.cnu.real_coding_server.entity.Post;
import com.cnu.real_coding_server.model.request.PostRequest;
import com.cnu.real_coding_server.model.type.Tag;
import com.cnu.real_coding_server.service.PostService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
public class PostServiceTest {

    @Autowired
    PostService postService;

    @Autowired
    ObjectMapper mapper;

    @BeforeEach
    void init() {

    }

    @DisplayName("비속어 제목에 포함된 저장 테스트")
    @Transactional
    @Test
    void createPost() throws JsonProcessingException {
        String title = "비속어";
        String content = "비속어";
        Tag tag = Tag.SPRINGBOOT;
        PostRequest postRequest = mapper.readValue(
                """
                        {
                            "title": "%s",
                            "contents": "%s",
                            "tag": "%s"
                        }
                        """.formatted(title, content, tag)
                , PostRequest.class);

        Post post = postService.createPost(postRequest);
        assertAll("verify object",
                () -> assertThat(post.getTitle()).isEqualTo(title),
                () -> assertThat(post.getContents()).isEqualTo(content),
                () -> assertThat(post.getTag()).isEqualTo(tag)
        );
    }

    @DisplayName("비속어 제목에 포함된 저장/업데이트 테스트")
    @Test
    void updatePost() throws JsonProcessingException {
        // given
        String title = "정상 제목";
        String content = "정상 본문";
        Tag tag = Tag.SPRINGBOOT;
        PostRequest postRequest = mapper.readValue(
                """
                        {
                            "title": "%s",
                            "contents": "%s",
                            "tag": "%s"
                        }
                        """.formatted(title, content, tag)
                , PostRequest.class);
        Post post = postService.createPost(postRequest);
        String updatedTitle = "정상 제목1";
        String updatedContent = "정상 본문2";
        Tag updatedTag = Tag.JAVA;
        PostRequest updatedPostRequest = mapper.readValue(
                """
                        {
                            "title": "%s",
                            "contents": "%s",
                            "tag": "%s"
                        }
                        """.formatted(updatedTitle, updatedContent, updatedTag)
                , PostRequest.class);
        // when
        Optional<Post> optPost = postService.updatePost(post.getId(), updatedPostRequest);

        // then
        Post updatedPost = optPost.get();
        assertAll("verify object",
                () -> assertThat(updatedPost.getTitle()).isEqualTo(updatedTitle),
                () -> assertThat(updatedPost.getContents()).isEqualTo(updatedContent),
                () -> assertThat(updatedPost.getTag()).isEqualTo(updatedTag)
        );
    }
}
