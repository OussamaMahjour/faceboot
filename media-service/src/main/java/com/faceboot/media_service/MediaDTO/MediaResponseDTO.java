package com.faceboot.media_service.MediaDTO;

import com.faceboot.media_service.MediaEntities.MediaType;
import lombok.*;


@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
@ToString
public class MediaResponseDTO {
    private Long id;
    private Long postId;
    private MediaType type;
    private String content;
    private String path;
}