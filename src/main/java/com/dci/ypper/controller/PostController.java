package com.dci.ypper.controller;

import com.dci.ypper.dto.PostRequest;
import com.dci.ypper.dto.PostResponse;
import com.dci.ypper.model.Post;
import com.dci.ypper.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PostController {
    @Autowired
    private PostService postService;

    @GetMapping("/posts")
    public List<PostResponse> getAllPosts() {return postService.getAllPosts();}

    @GetMapping("/posts/{id}")
    public ResponseEntity<PostResponse> getPostById(@PathVariable Long id) {
        PostResponse post = postService.getPostById(id);
        return  ResponseEntity.ok(post);
    }

    @PostMapping("/posts")
    public Post createPost(@RequestBody PostRequest postRequest) {return postService.createPost(postRequest);}

    @DeleteMapping("posts/{id}")
    public ResponseEntity<Post> deletePost(@PathVariable Long id) {
        postService.deletePost(id);
        return ResponseEntity.noContent().build(); // it is standard practice to send an empty response
    }
}
