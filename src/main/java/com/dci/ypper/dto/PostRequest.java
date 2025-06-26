package com.dci.ypper.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PostRequest {
    @NotBlank(message = "Title required!")
    private String title;
    @NotBlank(message = "Content required!")
    private String content;
    private String userName;
    private String[] tags;
}
