package com.dci.ypper.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PostResponse {
    private Long id;
    @NotBlank(message = "Title required!")
    private String title;
    @NotBlank(message = "Content required!")
    private String content;
    private String userName;
    private String[] tags;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
