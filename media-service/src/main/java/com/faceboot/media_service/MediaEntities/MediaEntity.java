package com.faceboot.media_service.MediaEntities;

import jakarta.persistence.*;
import lombok.*;


@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
@Entity
@ToString
public class MediaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long postId;
    @Enumerated(EnumType.STRING)
    private MediaType type;
    private String content;
    private String path;
}
