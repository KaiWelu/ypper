package com.dci.ypper.service;

import com.dci.ypper.dto.PostRequest;
import com.dci.ypper.dto.PostResponse;
import com.dci.ypper.model.Post;
import com.dci.ypper.repository.CommentRepository;
import com.dci.ypper.repository.PostRepository;
import com.dci.ypper.repository.TagRepository;
import com.dci.ypper.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


import java.util.Collections;
import java.util.List;

@Service
public class PostService {
    @Autowired
    private PostRepository postRepository;

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TagRepository tagRepository;


    public Page<PostResponse> getAllPosts(Pageable pageable) {
        return postRepository.findAll(pageable)
                             .map((post) -> {
                                        String[] emptyList = new String[0]; // this is temporary until tags are implemented
                                        return PostResponse.builder()
                                       .id(post.getId())
                                       .createdAt(post.getCreatedAt())
                                       .updatedAt(post.getUpdatedAt())
                                       .title(post.getTitle())
                                       .content(post.getContent())
                                       .userName(post.getUser().getName())
                                       .tags(emptyList)
                                       .build();
                });


    }

    public PostResponse getPostById(Long id) {
        Post post = postRepository.findById(id).orElseThrow(() -> new RuntimeException(String.valueOf(id)));
        String[] emptyList = new String[0]; // this is temporary until tags are implemented
        return PostResponse.builder()
                           .id(post.getId())
                           .createdAt(post.getCreatedAt())
                           .updatedAt(post.getUpdatedAt())
                           .title(post.getTitle())
                           .content(post.getContent())
                           .userName(post.getUser().getName())
                           .tags(emptyList)
                           .build();
    }

    @Transactional
    public Post createPost(PostRequest request) {
        Post newPost = Post.builder()
                           .title(request.getTitle())
                           .content(request.getContent())
                           .user(userRepository.findByName(request.getUserName()).orElseThrow(
                        () -> new EntityNotFoundException("Username not found: " + request.getUserName())))
                           .tags(Collections.emptyList()) // set to null for now
                           .build();

        return postRepository.save(newPost);
    }

    @Transactional
    public PostResponse updatePost(Long id, PostRequest update) {
        // look up if the post exists
        Post post = postRepository.findById(id).orElseThrow(() -> new RuntimeException("Post not found!"));

        // update the post - tags are missing for now
        post.setTitle(update.getContent());
        post.setContent(update.getContent());

        // save the post
        postRepository.save(post);
        String[] emptyList = new String[0]; // this is temporary until tags are implemented
        return PostResponse.builder()
                           .id(post.getId())
                           .createdAt(post.getCreatedAt())
                           .updatedAt(post.getUpdatedAt())
                           .title(post.getTitle())
                           .content(post.getContent())
                           .userName(post.getUser().getName())
                           .tags(emptyList)
                           .build();
    }

    public void deletePost(Long id) {postRepository.deleteById(id);}

}
