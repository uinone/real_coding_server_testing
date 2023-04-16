package com.cnu.real_coding_server.service;

import com.cnu.real_coding_server.entity.Post;
import com.cnu.real_coding_server.error.SlangBadRequestException;
import com.cnu.real_coding_server.model.request.PostRequest;
import com.cnu.real_coding_server.repository.PostRepository;
import com.cnu.real_coding_server.service.valid.PostValidService;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private static final List<String> slangList = List.of("비속어1", "비속어2");
    private final PostValidService postValidService;

    public Post createPost(PostRequest postRequest) {

        if (postValidService.isSlangInclude(slangList, postRequest.getTitle(), postRequest.getContents())) {
            throw new SlangBadRequestException();
        }
        return postRepository.save(postRequest.toEntity());
    }

    public List<Post> getPosts() {
        return postRepository.findAll();
    }

    public Optional<Post> getPost(Integer postId) {
        return postRepository.findById(postId);
    }

    public Optional<Post> updatePost(Integer postId, PostRequest postRequest) {
        if (postValidService.isSlangInclude(slangList, postRequest.getTitle(), postRequest.getContents())) {
            throw new SlangBadRequestException();
        }
        return postRepository.findById(postId)
                .map(post -> {
                    post.setTitle(postRequest.getTitle());
                    post.setContents(postRequest.getContents());
                    post.setTag(postRequest.getTag());
                    return postRepository.save(post);
                });
    }

    public void deletePost(Integer postId) {
        postRepository.findById(postId)
                .ifPresent(postRepository::delete);
    }
}
