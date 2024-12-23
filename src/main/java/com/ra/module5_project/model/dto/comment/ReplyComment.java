package com.ra.module5_project.model.dto.comment;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class ReplyComment {
    @NotBlank(message = "Trả lời bình luận không được để trống")
    private String replyComment;
}
