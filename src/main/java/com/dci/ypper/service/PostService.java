package com.dci.ypper.service;

import com.dci.ypper.dto.PostRequest;
import com.dci.ypper.dto.PostResponse;
import com.dci.ypper.model.Post;
import com.dci.ypper.model.Tag;
import com.dci.ypper.repository.CommentRepository;
import com.dci.ypper.repository.PostRepository;
import com.dci.ypper.repository.TagRepository;
import com.dci.ypper.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

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


    public List<PostResponse> getAllPosts() {
        return postRepository.findAll().stream()
                .map((post) -> {
                    String[] emptyList = new String[0]; // this is temporary until tags are implemented
                    return PostResponse.builder()
                                       .postId(post.getId())
                                       .createdAt(post.getCreatedAt())
                                       .updatedAt(post.getUpdatedAt())
                                       .title(post.getTitle())
                                       .content(post.getContent())
                                       .userName(post.getUser().getName())
                                       .tags(emptyList)
                                       .build();
                }).toList();

    }

    public Optional<Post> getPostById(Long id) {return postRepository.findById(id);}

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


}
