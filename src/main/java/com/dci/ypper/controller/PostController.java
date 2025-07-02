package com.dci.ypper.controller;

import com.dci.ypper.dto.PaginatedResponse;
import com.dci.ypper.dto.PostRequest;
import com.dci.ypper.dto.PostResponse;
import com.dci.ypper.model.Post;
import com.dci.ypper.service.PostService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class PostController {
    @Autowired
    private PostService postService;

    @GetMapping("/posts")
    public ResponseEntity<PaginatedResponse<PostResponse>> getAllPosts(Pageable pageable) {
        Page<PostResponse> page = postService.getAllPosts(pageable);
        // this will extract the sorting order from the request
        String sort = pageable.getSort().stream()
                              .map(order -> order.getProperty() + "," + order.getDirection().name().toLowerCase())
                              .collect(Collectors.joining("; ")); // e.g., "createdAt,desc; title,asc"

        PaginatedResponse<PostResponse> response = new PaginatedResponse<PostResponse>(
                page.getContent(),
                page.getNumber(),
                page.getSize(),
                page.getTotalElements(),
                page.getTotalPages(),
                page.isLast(),
                page.isFirst(),
                sort
        );
        return ResponseEntity.ok(response);
    }

    @GetMapping("/posts/{id}")
    public ResponseEntity<PostResponse> getPostById(@PathVariable Long id) {
        PostResponse post = postService.getPostById(id);
        return  ResponseEntity.ok(post);
    }

    // this should be changed to a response entity
    @PostMapping("/posts")
    public Post createPost(@Valid @RequestBody PostRequest postRequest) {return postService.createPost(postRequest);}

    @PutMapping("posts/{id}")
    public  ResponseEntity<PostResponse> updateBook(@PathVariable Long id, @Valid @RequestBody PostRequest request) {
        PostResponse updatedPost = postService.updatePost(id, request);
        return ResponseEntity.ok(updatedPost);
    }

    @DeleteMapping("posts/{id}")
    public ResponseEntity<Post> deletePost(@PathVariable Long id) {
        postService.deletePost(id);
        return ResponseEntity.noContent().build(); // it is standard practice to send an empty response
    }

    @GetMapping("posts/yolo")
    public ResponseEntity<String> yolo(@RequestBody String request) {
        System.out.println(request);
        return  ResponseEntity.notFound().build();
    }
}
