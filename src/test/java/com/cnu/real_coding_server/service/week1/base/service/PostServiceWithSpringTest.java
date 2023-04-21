package com.cnu.real_coding_server.service.week1.base.service;

import static org.assertj.core.api.Assertions.assertThat;

import com.cnu.real_coding_server.entity.Post;
import com.cnu.real_coding_server.service.PostService;
import java.util.Optional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Description;

@SpringBootTest
public class PostServiceWithSpringTest {


    @Autowired
    PostService postService;

    @Description("프로젝트에서 테스트 해볼 모듈을 가져와서 독립적으로 실행")
    @DisplayName("테스트 코드에서 Spring Component 실행해보기")
    @Test
    void testDoCodeRun() {
        // given
        int testPostId = 1;
        // when
        Optional<Post> optPost = postService.getPost(testPostId);
        // then
        assertThat(optPost.isPresent()).isFalse();
    }
}
