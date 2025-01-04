package com.faceboot.media_service.MediaRepositories;

import com.faceboot.media_service.MediaEntities.MediaEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.List;

public interface MediaRepository extends JpaRepository<MediaEntity, Long> {
    List<MediaEntity> findAllByPostId(Long id);
    @Transactional
    int deleteAllByPostId(Long postId);

}
