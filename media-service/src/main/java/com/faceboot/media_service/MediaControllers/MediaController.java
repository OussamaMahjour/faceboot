package com.faceboot.media_service.MediaControllers;
import com.faceboot.media_service.MediaDTO.MediaResponseDTO;
import com.faceboot.media_service.MediaEntities.MediaEntity;
import com.faceboot.media_service.MediaServices.MediaServiceInterface;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/media")
public class MediaController {
    MediaServiceInterface mediaService;
    public MediaController(MediaServiceInterface mediaService) {
        this.mediaService = mediaService;
    }
    @GetMapping("/all")
    public List<Optional<MediaResponseDTO>> getAllMedia() {
        return mediaService.findAll();
    }

    @GetMapping("/post/{post_id}")
    public List<Optional<MediaResponseDTO>> findPostMedia(@PathVariable String post_id) {
        return mediaService.findByPostId(post_id);
    }

    @PostMapping("/upload")
    public Optional<MediaEntity> uploadPost(@RequestParam("user_id") String user_id,
                                            @RequestParam("post_id") String postId,
                                            @RequestParam("type") String media_type,
                                            @RequestParam("content") String media_content,
                                            @RequestParam("media") MultipartFile file) throws IOException {
        return mediaService.addMedia(user_id, postId, media_type, media_content, file);
    }
    @DeleteMapping("/delete/postid/{post_id}/{user_id}")
    public String deletePostById(@PathVariable String post_id, @PathVariable String user_id) {
        return mediaService.deleteBypostId(post_id, user_id);
    }

    /*
    @DeleteMapping("/delete/userid/{userid}")
    */

}
