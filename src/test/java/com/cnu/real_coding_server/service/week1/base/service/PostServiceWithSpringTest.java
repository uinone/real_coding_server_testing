package com.cnu.real_coding_server.service.week1.base.service;

import static org.assertj.core.api.Assertions.assertThat;

import com.cnu.real_coding_server.entity.Post;
import com.cnu.real_coding_server.service.PostService;
import java.util.Optional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class PostServiceWithSpringTest {
    @Autowired
    PostService postService;

    @DisplayName("테스트 코드에서 Spring Component 실행해보기")
    @Test
    void testDoCodeRun() {
        Optional<Post> optPost = postService.getPost(1);
        assertThat(optPost.isPresent()).isFalse();
    }
}
