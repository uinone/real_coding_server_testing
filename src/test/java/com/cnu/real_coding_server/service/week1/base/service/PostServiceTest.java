package com.cnu.real_coding_server.service.week1.base.service;


import com.cnu.real_coding_server.entity.Post;
import com.cnu.real_coding_server.service.PostService;
import java.util.Optional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Description;

import static org.assertj.core.api.Assertions.assertThat;

class PostServiceTest {

    @Description("프로젝트와 연관없이 독립적으로 실행")
    @DisplayName("테스트 코드 실행해보기")
    @Test
    void testDoCode() {
        System.out.println("테스트 코드 실행!");
    }

}