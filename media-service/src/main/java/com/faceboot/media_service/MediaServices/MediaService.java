package com.faceboot.media_service.MediaServices;

import com.faceboot.media_service.MediaDTO.MediaRequestDTO;
import com.faceboot.media_service.MediaDTO.MediaResponseDTO;
import com.faceboot.media_service.MediaEntities.MediaEntity;
import com.faceboot.media_service.MediaEntities.MediaType;
import com.faceboot.media_service.MediaMapper.MediaMapperInterface;
import com.faceboot.media_service.MediaRepositories.MediaRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MediaService implements MediaServiceInterface {
    MediaRepository mediaRepository;
    MediaMapperInterface mediaMapper;
    public MediaService(MediaRepository mediaRepository, MediaMapperInterface mediaMapper) {
        this.mediaRepository = mediaRepository;
        this.mediaMapper = mediaMapper;
    }
    @Override
    public List<Optional<MediaResponseDTO>> findAll() {
        List<MediaEntity> mediaList = mediaRepository.findAll();
        List<Optional<MediaResponseDTO>> mediaResponseDTOList = new ArrayList<>();
        for (MediaEntity mediaEntity : mediaList) {
            mediaResponseDTOList.add(Optional.ofNullable(mediaMapper.toMediaResponseDTO(mediaEntity)));
        }
        return mediaResponseDTOList;
    }
    @Override
    public List<Optional<MediaResponseDTO>> findByPostId(Long post_id) {
        List<MediaEntity> mediaList = mediaRepository.findAllByPostId(post_id);
        List<Optional<MediaResponseDTO>> mediaResponseDTOList = new ArrayList<>();
        for (MediaEntity mediaEntity : mediaList) {
            mediaResponseDTOList.add(Optional.ofNullable(mediaMapper.toMediaResponseDTO(mediaEntity)));
        }
        return mediaResponseDTOList;
    }
    @Override
    public Optional<MediaResponseDTO> addMedia(String user_id,
                                               String postId,
                                               String media_type,
                                               String media_content,
                                               MultipartFile file) throws IOException {
        String uploadDir = new File("/media/storage").getAbsolutePath();
        File userFolder = new File(uploadDir+"/"+"User_"+user_id);
        File postFolder = new File(userFolder+"/"+"Post_"+postId);
        String filePath = postFolder + "/"+file.getOriginalFilename();
        File destinationFile = new File(filePath);
        if(userFolder.exists() && postFolder.exists()) {
            file.transferTo(destinationFile); // Save the file
            MediaRequestDTO mediaRequestDTO = MediaRequestDTO.builder()
                    .postId(Long.parseLong(postId))
                    .type(MediaType.valueOf((media_type)))
                    .path(filePath)
                    .content(media_content)
                    .build();
            return Optional.ofNullable(mediaMapper.toMediaResponseDTO(mediaRepository.save(mediaMapper.toMediaEntity(mediaRequestDTO))));
        }
        else {
            boolean post_folder_created = postFolder.mkdirs();
            if(post_folder_created) {
                file.transferTo(destinationFile); // Save the file
                MediaRequestDTO mediaRequestDTO = MediaRequestDTO.builder()
                        .postId(Long.parseLong(postId))
                        .type(MediaType.valueOf((media_type)))
                        .path(filePath)
                        .content(media_content)
                        .build();
                return Optional.ofNullable(mediaMapper.toMediaResponseDTO(mediaRepository.save(mediaMapper.toMediaEntity(mediaRequestDTO))));
            }else{

                return Optional.empty();
            }
        }
    }

    @Override
    public String deleteBypostId(Long postId, Long user_id) {
        String uploadDir = new File("/media/storage").getAbsolutePath();
        File userFolder = new File(uploadDir+"/"+"User_"+user_id);
        File postFolder = new File(userFolder+"/"+"Post_"+postId);
        File destinationFile = new File(postFolder.getAbsolutePath());
        if(postFolder.exists()) {
            try{
                String[] path = new String[]{"del","/f",userFolder+"\\"+"Post_"+postId+"\\user"+user_id+"_post"+postId+"_1.jpg"};
                System.out.println(path[2]);
                Runtime.getRuntime().exec(path);
                return "deleted";
            } catch (Exception e) {
                e.printStackTrace();
            }

            /*file.transferTo(destinationFile); // Save the file
            MediaRequestDTO mediaRequestDTO = MediaRequestDTO.builder()
                    .postId(Long.parseLong(postId))
                    .type(MediaType.valueOf((media_type)))
                    .path(filePath)
                    .content(media_content)
                    .build();
            return Optional.ofNullable(mediaMapper.toMediaResponseDTO(mediaRepository.save(mediaMapper.toMediaEntity(mediaRequestDTO))));

        }
             */
            return "post founded ";
        }
        else {
            return "post not found";
        }
    }
}
