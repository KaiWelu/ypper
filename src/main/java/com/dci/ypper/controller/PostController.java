package com.dci.ypper.controller;

import com.dci.ypper.dto.PostRequest;
import com.dci.ypper.model.Post;
import com.dci.ypper.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PostController {
    @Autowired
    private PostService postService;

    @PostMapping("/posts")
    public Post createPost(@RequestBody PostRequest postRequest) {return postService.createPost(postRequest);}
}
