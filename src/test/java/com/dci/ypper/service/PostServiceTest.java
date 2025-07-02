package com.dci.ypper.service;

import com.dci.ypper.dto.PostResponse;
import com.dci.ypper.model.Post;
import com.dci.ypper.model.User;
import com.dci.ypper.repository.PostRepository;
import com.dci.ypper.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class PostServiceTest {
    @InjectMocks // this tells mockito to look for matching mocks (eg postRepository) and injects them
    private PostService postService;

    @Mock
    private PostRepository postRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private SecurityContext securityContext;

    @Mock
    private SecurityContextHolder securityContextHolder;

    private User testUser;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this); // this initializes the mocks before each test

        testUser = User.builder().id(1L).name("testUser").build(); // creates testUser

        SecurityContextHolder.setContext(mock(SecurityContext.class)); // this will create a mock security context
        // this will return the testuser when the context is called
        when(SecurityContextHolder.getContext().getAuthentication()).thenReturn(
                new UsernamePasswordAuthenticationToken("testUser", null));
    }

    @DisplayName("Should return a post by ID")
    @Test
    void testGetPostById_Success() {
        Post post = Post.builder()
                        .id(1L)
                        .title("Hello")
                        .content("Kai")
                        .createdAt(LocalDateTime.now())
                        .user(testUser)
                        .build();

        when(postRepository.findById(1L)).thenReturn(Optional.of(post));
        PostResponse response = postService.getPostById(1L);
        assertEquals("Hello", response.getTitle());
        assertEquals("Kai", response.getContent());
    }

}
