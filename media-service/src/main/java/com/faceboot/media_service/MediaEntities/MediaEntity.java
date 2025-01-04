package com.faceboot.media_service.MediaEntities;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
@ToString
@Document(value = "media")
public class MediaEntity {
    @Id
    private String id;
    private Long postId;
    private MediaType type;
    private String content;
    private String path;
}
