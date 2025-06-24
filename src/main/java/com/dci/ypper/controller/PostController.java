package com.dci.ypper.controller;

import com.dci.ypper.dto.PostRequest;
import com.dci.ypper.dto.PostResponse;
import com.dci.ypper.model.Post;
import com.dci.ypper.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PostController {
    @Autowired
    private PostService postService;

    @GetMapping("/posts")
    public List<PostResponse> getAllPosts() {return postService.getAllPosts();}

    @PostMapping("/posts")
    public Post createPost(@RequestBody PostRequest postRequest) {return postService.createPost(postRequest);}
}
